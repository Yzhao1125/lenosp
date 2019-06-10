package com.len.controller;

import com.len.core.SocketThread.Handler;
import com.len.entity.PDevice;
import com.len.service.DeviceLocation;
import com.len.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class DeviceLController {

    @Autowired
    private DeviceLocation deviceLocation;

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value="locateDevice")
    public String MonitorDevice(){
        return "/system/deviceL/iplocation";
    }



    @RequestMapping(value ="ip")
    @ResponseBody
    public List<String> getLocation() {
        List<String> addressList = new ArrayList<>();
        //     List<String> IPlist = deviceService.getallIp();
        List<PDevice> list = deviceService.getAlldevice();
        for (PDevice pDevice : list) {
            System.out.println(pDevice);
            String dname=pDevice.getDname();
   //         System.out.println("IP的名称：" + dname);
            String IP=pDevice.getIp();
            System.out.println("数据库的IP:" + IP);
            String deviceID=pDevice.getDeviceid();
            String address = deviceLocation.getAddress("ip=" + IP, "utf-8");
//            System.out.println("IP的地址：" + address);

            StringBuilder a = new StringBuilder(address);
            int index1 = a.indexOf("}");
            a.insert(index1,",\"dname\":\"");
            int index2 = a.indexOf("}");
            a.insert(index2,dname);
            int index3 = a.indexOf("}");
            a.insert(index3,"\"");
            String ipstring = ""+a;
    //        System.out.println("ceshi:" + ipstring);

            StringBuilder b=new StringBuilder(ipstring);
            int index4 = b.indexOf("}");
            b.insert(index4,",\"deviceid\":\"");
            int index5 = b.indexOf("}");
            b.insert(index5,deviceID);
            int index6 = b.indexOf("}");
            b.insert(index6,"\"");
            String newstring=""+b;
  //          System.out.println("ceshi2:" + newstring);
            addressList.add(newstring);

    }
        return addressList;
    }






}
