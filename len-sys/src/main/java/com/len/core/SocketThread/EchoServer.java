package com.len.core.SocketThread;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {

    private int port=8093;
    private ThreadPool threadPool;//线程池
    private ServerSocket serverSocket;
    private final int POOL_SIZE = 600;//单个Cpu时线程池中工作的数目


    public EchoServer()throws IOException {
        serverSocket = new ServerSocket(port);
        //创建线程池
        //Rumtime的availableProcessors()方法返回当前系统的cup数目
        //系统的cpu越多，线程池中的数目也越多
        threadPool = new ThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
        System.out.println("服务器启动！");
    }

    public void service(){
        while (true) {
            Socket socket=null;
            try {
                socket = serverSocket.accept();
                threadPool.execute(new Handler(socket));//把与客户通讯的任务交给线程
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
