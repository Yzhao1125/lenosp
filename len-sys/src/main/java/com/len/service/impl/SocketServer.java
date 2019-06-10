package com.len.service.impl;


//暂时无用

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;



public class SocketServer {

    private final ServerSocket mServerSocket;

    public SocketServer(int port) throws IOException{
        mServerSocket = new ServerSocket(port);
    }

    public void run() throws IOException{
        Socket client = mServerSocket.accept();
        handleClient(client);
    }

    private void handleClient(Socket client)throws IOException {
        InputStream inputStream = client.getInputStream();
        OutputStream outputStream =client.getOutputStream();
        String response="Responsed data:";
        int m=response.length();
        String responseMsg ="";
        byte[] buffer =new byte[1024];
        int n;
        while((n=inputStream.read(buffer))>0){
            String content = new String(buffer);
            System.out.println("recieved data:"+ content);
            responseMsg="Responsed data:"+content;
            buffer= responseMsg.getBytes();
            outputStream.write(buffer,0,m+n);
            buffer=new byte[1024];
        }
    }

   /* //解码buffer
    private Charset cs = Charset.forName("UTF-8"); //接收数据的编码要与发送端的数据编码保持一致
    //发送数据缓冲区
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    //接收数据缓冲区
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    //选择器（叫监听器更准确些吧应该）
    private static Selector selector;

    *//**
     * 启动socket服务，开启监听
     * @param port
     * @throws IOException
     *//*
    public void startSocketServer(int port){
        try {
            //打开通信信道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //获取套接字
            ServerSocket serverSocket = serverSocketChannel.socket();
            //绑定端口号
            serverSocket.bind(new InetSocketAddress(port));
            //打开监听器
            selector = Selector.open();
            //将通信信道注册到监听器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            //监听器会一直监听，如果客户端有请求就会进入相应的事件处理
            while (true){
                selector.select();//select方法会一直阻塞直到有相关事件发生或超时
                Set<SelectionKey> selectionKeys = selector.selectedKeys();//监听到的事件
                for (SelectionKey key : selectionKeys) {
                    handle(key);
                }
                selectionKeys.clear();//清除处理过的事件
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    *//**
     * 处理不同的事件
     * @param selectionKey
     * @throws IOException
     *//*
    private void handle(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        String requestMsg = "";
        int count = 0;
        if (selectionKey.isAcceptable()) {
            //每有客户端连接，即注册通信信道为可读
            serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
        else if (selectionKey.isReadable()) {
            socketChannel = (SocketChannel)selectionKey.channel();
            rBuffer.clear(); //清空缓冲区上次接收的数据
            count = socketChannel.read(rBuffer);
            //读取数据
            if (count > 0) {
                rBuffer.flip();
                requestMsg = String.valueOf(cs.decode(rBuffer).array());
            }
            String responseMsg = "已收到客户端的消息:"+requestMsg;
           System.out.println("++++++"+requestMsg);
            //返回数据
            sBuffer = ByteBuffer.allocate(responseMsg.getBytes("gbk").length);
            sBuffer.put(responseMsg.getBytes("gbk"));
            sBuffer.flip();
            socketChannel.write(sBuffer);
          //  socketChannel.close();
        }

    }
*/
}
