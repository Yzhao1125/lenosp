package com.len.service;

import com.len.base.BaseService;
import com.len.entity.PDevice;
import com.len.util.ReType;

import java.net.InetAddress;
import java.util.List;
/**
 * created by Yuan
 * 2019.4.15
 */

public interface DeviceService extends BaseService<PDevice,String> {

    int checkDeviceName(String dname);
    List<String> getallIp();
    int count();
    String authDevice(String msg1);
    void updateDeviceCon(String eid,String conStatue);
    ReType showCon(PDevice pDevice, int page, int limit);


    List<PDevice> getAlldevice();

    void addDevice(PDevice pDevice);

    void updateDeviceIp(String eid, String inetAddress);

    PDevice selectDevicebyeid(String eid);
}
