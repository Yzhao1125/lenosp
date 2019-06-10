package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.PDevice;

import java.util.List;


public interface PDeviceMapper extends BaseMapper<PDevice,String> {

    int checkdevicename(String dname);

    int count();

    List<String> getallIp();

    String checkPW(String deviceId);

    void updateCon(String deviceId,String connect);

    List<PDevice> selectListByPage2(PDevice pDevice);


    List<PDevice> getalldevice();
}