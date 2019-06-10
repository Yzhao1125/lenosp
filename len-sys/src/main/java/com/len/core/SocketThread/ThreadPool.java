package com.len.core.SocketThread;

import java.util.LinkedList;

public class ThreadPool extends ThreadGroup{

    private boolean isClosed = false;  //线程是否关闭
    private LinkedList<Runnable> workQueue; //表示工作队列
    private static int threadPoolID; //表示线程池ID
    private int threadID; //表示工作线程ID

    /**
     * constructor
     * @param poolsize
     */
    public ThreadPool (int poolsize){
        super("ThreadPool-"+(threadPoolID ++)); //调用ThreadGroup中的构造函数，创建一个以当前线程所在线程池为父类的新的线程池，
        setDaemon(true); //设置为守护线程
        workQueue = new LinkedList<Runnable>(); //创建工作队列
        for(int i=0; i<poolsize; i++){
            new WorkThread().start();
        }
    }

    /**
     * 向工作队列中加入一个新任务，由工作线程去执行
     * @param task
     */
    public synchronized  void execute(Runnable task){
        if(isClosed){
            System.out.println("线程池被关闭");
            throw new IllegalStateException();
        }
        if(task!= null){
            workQueue.add(task);
            notify(); //唤醒正在getTask()方法中等待任务的工作线程
        }
    }

    /**
     * 从工作队列中取出一个任务，工作线程会调用此方法
     * @return
     * @throws InterruptedException
     */
    public synchronized Runnable getTask()throws InterruptedException{
        while(workQueue.size()==0){
            if(isClosed)return null;
            wait(); //如果工作队列中没有任务，就等待任务
        }
        return workQueue.removeFirst();
    }

    /**
     * 关闭线程池
     */
    public synchronized void close(){
        if(!isClosed){
            isClosed = true;
            workQueue.clear(); //clean a workqueue
            interrupt();//stop all of thread pool ID
        }
    }

    /**
     * 等待工作线程吧所有任务执行完
     */
    public void join(){
        synchronized (this) {
            isClosed=true;
            notifyAll();//唤醒还在getTask()方法中等待任务的工作线程
        }

        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);//获得当前所有活着的工作线程
        for(int i =0; i<count; i++){
            try {
                threads[i].join();//等待所有工作线程运行结束
            }catch (InterruptedException e){}
            //TODO: handle exception
        }

    }

    /**
     * 工作线程
     */
    private class WorkThread extends Thread {
        public WorkThread(){
            super(ThreadPool.this,"WorkThread-"+(threadID++));
        }

        public void run(){
            while (!isInterrupted()){
                Runnable task = null;
                try {
                    task = getTask();
                }catch (InterruptedException e){
                    //TODO: handle exception
                }
                if (task==null) return;
                try{
                    task.run();
                }catch (Throwable e){
                    //TODO: handle exception
                    e.printStackTrace();
                }
            }
        }
    }


}
