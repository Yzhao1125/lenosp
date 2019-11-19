package com.len.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.Device_state;
import com.len.entity.PDevice;
import com.len.mapper.DeviceStateMapper;
import com.len.service.DeviceMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceStateServiceImpl extends BaseServiceImpl<PDevice,String> implements DeviceMService {


    @Autowired
    private DeviceStateMapper deviceStateMapper;


    @Override
    public BaseMapper<PDevice, String> getMappser() {
        return deviceStateMapper;
    }

    @Override
    public int saveDeviceState(JSONObject jsonObject) {
        String tablename = jsonObject.getJSONObject("ST").getString("EID");
        tablename = "p_"+tablename;
        if(deviceStateMapper.isTableExist(tablename)==0){ //不存在该设备状态表
            deviceStateMapper.createNewTable(tablename);
        }

        Device_state device_state = new Device_state();
        device_state.setEid(jsonObject.getJSONObject("ST").getString("EID"));
        device_state.setIp(jsonObject.getJSONObject("ST").getString("IP"));
        device_state.setT1(jsonObject.getJSONObject("DY").getJSONObject("S").getString("T1"));
        device_state.setT2(jsonObject.getJSONObject("DY").getJSONObject("S").getString("T2"));
        device_state.setT3(jsonObject.getJSONObject("DY").getJSONObject("S").getString("T3"));
        device_state.setX(jsonObject.getJSONObject("DY").getJSONObject("P").getString("X"));
        device_state.setY(jsonObject.getJSONObject("DY").getJSONObject("P").getString("Y"));
        device_state.setZ(jsonObject.getJSONObject("DY").getJSONObject("P").getString("Z"));
        device_state.setE(jsonObject.getJSONObject("DY").getJSONObject("P").getString("E"));
        device_state.setT(jsonObject.getJSONObject("DY").getString("T"));

        deviceStateMapper.saveDeviceState(tablename,device_state);


     //   System.out.println("servicImple层："+tablename);
        return 0;
    }

    @Override
    public List<Device_state> selectDeviceStateByEId(String tablename, String eid) {
        return deviceStateMapper.selectDeviceStateByEId(tablename,eid);
    }

    @Override
    public void savedevicestate(String tablename, Device_state device_state) {
           deviceStateMapper.saveDeviceState(tablename,device_state);
    }

    @Override
    public int isTableExist(String tableName) {
        return deviceStateMapper.isTableExist(tableName);
    }


    @Override
    public List<Device_state> getDeviceStateList(String eid) {
        String tablename = "p_" + eid;
        return deviceStateMapper.selectDeviceStateByEId(tablename,eid);
    }

    @Override
    public List<PDevice> selectListByPage(PDevice record) {
        return deviceStateMapper.selectListByPage(record);
    }
}
