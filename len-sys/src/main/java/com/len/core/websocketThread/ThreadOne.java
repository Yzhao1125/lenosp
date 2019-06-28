package com.len.core.websocketThread;

import com.len.entity.PDevice;
import com.len.service.DeviceService;
import com.len.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.List;

//@Component
public class ThreadOne extends Thread {

       private Session session;

       private List<PDevice> pDevicesList;

       @Autowired
       private DeviceService deviceService;


    public ThreadOne(Session session) {
        this.session = session;
        deviceService = SpringUtil.getBean("deviceServiceImpl");
    }

    @Override
    public void run() {
        while(true){
            List<PDevice> list =null;

            super.run();
        }

    }
}
