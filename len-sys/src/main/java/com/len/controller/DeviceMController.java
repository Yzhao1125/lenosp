package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.len.core.SocketThread.Handler;
import com.len.entity.Device_state;
import com.len.entity.PDevice;
import com.len.service.DeviceMService;
import com.len.service.DeviceService;
import com.len.util.ReType;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class DeviceMController {


    @Autowired
    private DeviceMService deviceMService;

    @Autowired
    private DeviceService deviceService;

    String deviceId = null;

    @RequestMapping(value="/monitorDevice")
    public String MonitorDevice(){
        return "/system/deviceM/deviceMList";
    }


    @RequestMapping(value="/help")
    public String Help(){
        return "/system/helpd/helpdcm";
    }



    @RequestMapping(value = "/showConDevice")
    @ResponseBody
    public ReType showDeviceList(PDevice pDevice, String page, String limit){
        ReType reType = deviceService.showCon(pDevice,Integer.valueOf(page),Integer.valueOf(limit));
        return reType;
    }

    @RequestMapping(value ="/checkdevice")
    public String checkRealstat(String eid){
        System.out.println("shebei:"+eid);
       // List<Device_state> statelist = deviceMService.getDeviceStateList(eid);
       deviceId = eid;
       return "system/deviceM/checkdata";
    }

    @RequestMapping(value ="/getStateData")
    @ResponseBody
    public String getstateData(){
        Socket s2 = Handler.list_socket_device.get(deviceId);
        String statmsg = null;
        JSONObject jsonstate = new JSONObject();
        JSONObject id = new JSONObject();
        JSONObject result = new JSONObject();
        id.put("Eid",deviceId);
      //  System.out.println("@@@@@@@"+id.toJSONString());

        try{
            InputStream socketin = s2.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(socketin);
            BufferedReader reader = new BufferedReader(streamReader);
            statmsg = reader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        if (statmsg.startsWith("STA")){
            statmsg = statmsg.substring(3,statmsg.length()-3);
            jsonstate = JSON.parseObject(statmsg);
            jsonstate = jsonstate.getJSONObject("DY");
        }
        result.putAll(id);
        result.putAll(jsonstate);
        System.out.println("controller****"+ result.toJSONString());
        return result.toJSONString();
    }


    @RequestMapping(value ="/start")
    @ResponseBody
    public void startSend(HttpServletRequest re) {
       // String Eid ="6437415503402621";
        String Eid = re.getParameter("ss");
        System.out.println("start:"+ Eid);
        Socket s2 = Handler.list_socket_device.get(Eid);
        if(s2!=null&&s2.isConnected()){
            try{
                OutputStream outputStream = s2.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.print("STA");
                printWriter.flush();
                System.out.println("发送成功");
            } catch (IOException e) {
                System.out.println("Socket 服务器向客户端发送数据异常");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("服务器无客户端连接");
        }
    }

    @RequestMapping(value ="/stop")
    @ResponseBody
    public void stopSend (String id) {
        System.out.println("data1111111:"+ id);
 //       String eid = re.getParameter("id");
        Socket s2 = Handler.list_socket_device.get(id);
        if(s2!=null&&s2.isConnected()){
            try{
                OutputStream outputStream = s2.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.print("STOP");
                printWriter.flush();
                System.out.println("发送成功");
            } catch (IOException e) {
                System.out.println("发送数据异常");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("该设备连接断开");
        }
    }

    @RequestMapping(value ="/recover")
    @ResponseBody
    public void Drecover (HttpServletRequest re) {
        System.out.println("data:"+ re.getParameter("ss"));
        String eid=re.getParameter("ss");
        Socket s2 = Handler.list_socket_device.get(eid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("EID",eid);
        jsonObject.put("I","M 24"); //M25暂停，M29停止，M24恢复打印
        System.out.println("指令数据"+jsonObject);
        if(s2!=null&&s2.isConnected()){
            try{
                //发送控制指令
                OutputStream outputStream = s2.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println(jsonObject);
                printWriter.flush();

            } catch (IOException e) {
                System.out.println("发送数据异常");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("该设备连接断开");
        }
    }

    @RequestMapping(value ="/pause")
    @ResponseBody
    public void Dpause (HttpServletRequest re) {
        System.out.println("data:"+ re.getParameter("ss"));
        String eid=re.getParameter("ss");
        Socket s2 = Handler.list_socket_device.get(eid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("EID",eid);
        jsonObject.put("I","M 25"); //M25暂停，M29停止，M24恢复打印
        System.out.println("指令数据"+jsonObject);
        if(s2!=null&&s2.isConnected()){
            try{
                //发送控制指令
                OutputStream outputStream = s2.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println(jsonObject);
                printWriter.flush();

            } catch (IOException e) {
                System.out.println("发送数据异常");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("该设备连接断开");
        }
    }

    @RequestMapping(value ="/stopdevice")
    @ResponseBody
    public void stopDevice (HttpServletRequest re) {
        System.out.println("data:"+ re.getParameter("ss"));
        String eid=re.getParameter("ss");
        Socket s2 = Handler.list_socket_device.get(eid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("EID",eid);
        jsonObject.put("I","M 29"); //M25暂停，M29停止，M24恢复打印
        System.out.println("指令数据"+jsonObject);
        if(s2!=null&&s2.isConnected()){
            try{
                //发送控制指令
                OutputStream outputStream = s2.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println(jsonObject);
                printWriter.flush();

            } catch (IOException e) {
                System.out.println("发送数据异常");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("该设备连接断开");
        }
    }
}
