package com.len.core.quartz.CustomQuartz;

import com.len.core.SocketThread.Handler;
import com.len.service.DeviceService;
import io.swagger.models.auth.In;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class JobSocket implements Job {

    @Autowired
    private DeviceService deviceService;




 //   Map<String,Socket> socketMap = new ConcurrentHashMap<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("JobSocket：启动任务=======================");
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JobSocket：下次执行时间====="+
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .format(context.getNextFireTime())+"==============");
    }

    public void run() throws IOException {
         for (Map.Entry<String,Socket> entry : Handler.list_socket_device.entrySet()){
                 Socket insocket = entry.getValue();
                     if(insocket.isClosed()){
                         String disconEId = entry.getKey();
                         Handler.list_socket_device.remove(entry.getKey());
                         System.out.println(disconEId + "已断开连接");
                         deviceService.updateDeviceCon(disconEId,"未连接");
                     }
           System.out.println(entry.getKey() + entry.getValue());
       }
    }

}
