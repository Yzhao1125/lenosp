package com.len.core.quartz.CustomQuartz;

import com.len.core.SocketThread.Handler;
import com.len.service.DeviceService;
import io.swagger.models.auth.In;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Jobbroadcast implements Job {

    @Autowired
    private DeviceService deviceService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
           System.out.println("Jobbroadcast：启动任务=======================");
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("JobSocket：下次执行时间====="+
//                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//                        .format(context.getNextFireTime())+"==============");
    }

    public void run() throws IOException {
        for (Map.Entry<String,Socket> entry : Handler.list_socket_device.entrySet()){
            Socket insocket = entry.getValue();

            if(insocket.isConnected()){
                PrintWriter heart = new PrintWriter(insocket.getOutputStream());
                heart.println("{}");
                heart.flush();
                System.out.println(entry.getValue()+"心跳");
            }
           // System.out.println(entry.getKey() + entry.getValue());
        }
    }
}
