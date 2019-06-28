package com.len.core.SocketThread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.len.entity.PDevice;

import com.len.service.DeviceMService;
import com.len.service.DeviceService;

import com.len.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class Handler implements Runnable{


    private Socket socket;

    public static List<Socket> socketList = new ArrayList<>();

    public static ConcurrentMap<String,Socket> list_socket_device = new ConcurrentHashMap<>();

    public JSONObject jsonObject;

    public JSONObject authjsonObject;


    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceMService deviceMService;

    public Handler(Socket socket) {
        this.socket=socket;
    }

    private PrintWriter getWriter(Socket socket)throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut,true);
    }

    private BufferedReader getReader(Socket socket)throws IOException{
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public String echo(String msg){
        return "echo:"+msg;
    }

    public void run(){
        try{
            //得到客户端的地址和端口号
            System.out.println("New connection accepted" + socket.getInetAddress() + ":" + socket.getPort());
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);
            socketList.add(socket);
            socket.setSoTimeout(60*1000);
            String msg = null;
            String authmsg = null;


            while(true){
                msg = br.readLine();
                if(msg!=null){
                    System.out.println(msg);
                    if(msg.startsWith("AUT")) {  //认证消息 AUT{"EID":"****","PW":"****"}END
                        msg = msg.substring(3, msg.length() - 3);
                        System.out.println("44444" + msg);
                        authjsonObject= new JSONObject();
                        authjsonObject =JSON.parseObject(msg);
                        deviceService = SpringUtil.getBean("deviceServiceImpl");
                        authmsg = deviceService.authDevice(msg);
                        if ("true".equals(authmsg)) {
                            pw.write("OK");
                            pw.flush();
                            add_socket_device(authjsonObject.getString("EID"),socket);  //设备编号与socket绑定
                            deviceService.updateDeviceIp(authjsonObject.getString("EID"), socket.getInetAddress().toString().substring(1)); //更新设备表的设备连接状态
                        } else {
                            socket.close();
                            System.out.println("认证失败");
                        }
                    }else if(msg.startsWith("STA")){
                        msg = msg.substring(3, msg.length() - 3);
                        System.out.println("状态数据："+ msg);
                        jsonObject = new JSONObject();
                        jsonObject = JSON.parseObject(msg);
                        deviceMService = SpringUtil.getBean("deviceStateServiceImpl");
                        int in = deviceMService.saveDeviceState(jsonObject);
                        System.out.println("存入结果："+ in);
                    }

                }else{  //readLine()只有在数据流发生异常或者另一端被close()掉时，才会返回null值
                   socket.close();
                }
            }

        }catch (SocketTimeoutException e){
            //TODO: handle exception
            e.printStackTrace();
            try {
                socket.close();
            }catch (IOException e2){
                e2.printStackTrace();
            }
        }catch (IOException e1){
            e1.printStackTrace();
        }
    }

    private void add_socket_device(String eid, Socket socket) {
        Socket oldSocket;
        if(list_socket_device.get(eid) == null){  //不存在已有的EID
            list_socket_device.putIfAbsent(eid,socket);
            System.out.println("已加入新device_socket" + list_socket_device.get(eid).toString());
        }else{
            oldSocket= list_socket_device.get(eid);
            list_socket_device.replace(eid,oldSocket,socket);
            System.out.println("已更新device_socket"+ list_socket_device.get(eid).toString());
        }

    }

}
