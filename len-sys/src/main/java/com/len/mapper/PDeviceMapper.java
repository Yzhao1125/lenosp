package com.len.mapper;

import com.alibaba.fastjson.JSONObject;
import com.len.base.BaseMapper;
import com.len.entity.PDevice;
import com.len.util.JsonUtil;

import java.util.List;


public interface PDeviceMapper extends BaseMapper<PDevice,String> {

    int checkdevicename(String dname);

    int count();

    List<String> getallIp();

    String checkPW(String deviceId);

    void updateCon(String deviceId,String connect);

    List<PDevice> selectListByPage2(PDevice pDevice);


    List<PDevice> getalldevice();

    void addDevice(PDevice pDevice);

    void updateIp(String eid, String inetAddress);

    PDevice selectbyeid(String eid);

    int deletebydeviceId(String eid);

    List<PDevice> getConnects(List<String> deviceids);

    void updateDevice(PDevice pDevice);

    //  List<PDevice> getdeviceConnect();
}