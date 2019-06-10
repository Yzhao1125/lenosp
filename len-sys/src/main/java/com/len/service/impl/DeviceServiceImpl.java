package com.len.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.PDevice;

import com.len.exception.MyException;
import com.len.mapper.PDeviceMapper;
import com.len.service.DeviceService;
import com.len.util.ReType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl extends BaseServiceImpl<PDevice,String> implements DeviceService {

    @Autowired
    PDeviceMapper deviceMapper;
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
    public ReType showCon(PDevice pDevice, int page, int limit) {

        List<PDevice> tList = null;
        Page<PDevice> tPage = PageHelper.startPage(page, limit);

        try {
            tList = deviceMapper.selectListByPage2(pDevice);
        } catch (MyException e) {
          //  log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }

    @Override
    public List<PDevice> getAlldevice() {
        return deviceMapper.getalldevice();
    }
}
