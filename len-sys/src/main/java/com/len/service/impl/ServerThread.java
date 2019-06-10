package com.len.service.impl;

import java.io.*;
import java.net.Socket;

/**
 * 已修改为线程池，此类暂时无用
 */
public class ServerThread implements Runnable {
    Socket socket=null; //和本线程相关的Socket

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;

        try{
            is=socket.getInputStream();
            isr =new InputStreamReader(is,"UTF-8");
            br = new BufferedReader(isr);
            String data = null;
            while ((data=br.readLine())!=null){
                System.out.println("我的服务器，客户端提交信息为："+data);
            }
            socket.shutdownInput();

            os = socket.getOutputStream();
            pw= new PrintWriter(os);
            pw.write("服务器响应成功");
            pw.flush();

        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(pw!=null)
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

