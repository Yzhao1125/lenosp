package com.len.service;

import com.len.entity.PUserDevice;

public interface PUserDeviceService {

    void addUserDevice(PUserDevice pUserDevice);

    String getuidbyeid(String eid);
}
