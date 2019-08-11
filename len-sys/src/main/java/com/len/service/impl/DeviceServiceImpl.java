package com.len.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.len.base.BaseMapper;
import com.len.base.CurrentRole;
import com.len.base.CurrentUser;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.PDevice;

import com.len.entity.PDeviceS;
import com.len.exception.MyException;
import com.len.mapper.PDeviceMapper;
import com.len.service.DeviceService;
import com.len.util.CommonUtil;
import com.len.util.JsonUtil;
import com.len.util.ReType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl extends BaseServiceImpl<PDevice,String> implements DeviceService {


    @Autowired
    PDeviceMapper deviceMapper;

    @Value("${adminRole}")
    private String adminRole;

    @Override
    public List<String> getallIp() {
        return deviceMapper.getallIp();
    }
    @Override
    public int checkDeviceName(String dname) {
        return deviceMapper.checkdevicename(dname);
    }
    @Override
    public BaseMapper<PDevice, String> getMappser() {
        return deviceMapper;
    }
    @Override
    public List<PDevice> selectListByPage(PDevice record) {
        System.out.println("读数据库前pDevice"+ record.getDname());
        List<PDevice> list1 = deviceMapper.selectListByPage(record);
        for(PDevice p: list1){
            String s= p.getDname();
            System.out.println("读数据库后"+s);
        }
        return list1;

    }
    @Override
    public int count() {
        return deviceMapper.count();
    }
    @Override
    public String authDevice(String msg1) {
        String authmsg ="";
        JSONObject authObject = JSON.parseObject(msg1);
        String deviceId = authObject.getString("EID");
        String PW = authObject.getString("PW");
        authmsg = deviceMapper.checkPW(deviceId);
        if(authmsg.isEmpty()){
            return "false"; //没有该设备名，认证失败
        } else if(!authmsg.equals(PW)){
         return "false"; //设备密码不符，认证失败
        }else{
            System.out.println("认证成功");
            String connect ="已连接";
            deviceMapper.updateCon(deviceId,connect);
            return "true";
        }
    }

    @Override
    public void updateDeviceCon(String eid, String conStatue) {
           deviceMapper.updateCon(eid,conStatue);
    }

    @Override
    public void updateDevice(PDevice pDevice) {
        System.out.println("进入service层");
        deviceMapper.updateDevice(pDevice);
    }

    @Override
    public List<HashMap<String,String>> getDeviceConnect(List<String> deviceids) {

       List<HashMap<String,String>> results= new ArrayList<>();
        List<PDevice> devices = deviceMapper.getConnects(deviceids);
        for(PDevice d:devices){
           // System.out.println("//////////"+d.getDeviceid() + d.getConnect());
            HashMap<String,String> result= new HashMap<>();
            result.put("deviceid", d.getDeviceid());
            result.put("connect", d.getConnect());
            results.add(result);
        }
//        for(String key: result.keySet()){
//            System.out.println("key="+key+" and value "+result.get(key));
//        }
        return results;
    }

//    @Override
//    public List<String> getDeviceConnect() {
//        List<String> deviceconnect= new ArrayList<>();
//        List<PDevice> pDevices = deviceMapper.getalldevice();
//        for (PDevice p: pDevices){
//            String deviceid = p.getDeviceid();
//            String connect = p.getConnect();
//            JSONObject de= new JSONObject();
//            de.put("deviceid", deviceid);
//            de.put("connect",connect);
//            deviceconnect.add(de.toJSONString());
//          //  System.out.println(de.getString("deviceid")+de.getString("connect"));
//            System.out.println(de.toJSONString());
//        }
//        return deviceconnect;
//    }

    @Override
    public JsonUtil deletebydeviceId(String eid, boolean flag) {

        JsonUtil j=new JsonUtil();
        deviceMapper.deletebydeviceId(eid);
        j.setMsg("删除成功");
        return j;
    }

    @Override
    public ReType showCon(PDevice pDevice, int page, int limit) {

        List<PDevice> tList = null;
        Page<PDevice> tPage = PageHelper.startPage(page, limit);
        List<PDevice> fList = new ArrayList<>();

        CurrentUser user = CommonUtil.getUser();
        List<CurrentRole> currentRoleList = user.getCurrentRoleList();
        long count = currentRoleList.stream().filter(s -> adminRole.equals(s.getRoleName())).count();
        if(count==0)
            pDevice.setUserId(user.getId());

        try {
            tList = deviceMapper.selectListByPage(pDevice);
            for(PDevice p:tList){
                if(p.getConnect().equals("已连接")){
                    System.out.println("&&&&&&&&&&&&&&"+p.getDname());
                    fList.add(p);
                    System.out.println("@@@@@@@@@@@@@@@@@@@@"+fList.get(0).getDname());
                }

            }
        } catch (MyException e) {
          //  log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), fList);
    }

    @Override
    public List<PDevice> getAlldevice() {

        return deviceMapper.getalldevice();
    }

    @Override
    public void addDevice(PDevice pDevice) {
       deviceMapper.addDevice(pDevice);
    }

    @Override
    public void updateDeviceIp(String eid, String inetAddress) {
        deviceMapper.updateIp(eid,inetAddress);

    }

    @Override
    public PDevice selectDevicebyeid(String eid) {
        return deviceMapper.selectbyeid(eid);
    }
}
