package com.len.service.impl;

import com.len.entity.PUserDevice;
import com.len.mapper.PUserDeviceMapper;
import com.len.service.PUserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PUserDeviceServiceImpl implements PUserDeviceService {

    @Autowired
    PUserDeviceMapper pUserDeviceMapper;

    @Override
    public void addUserDevice(PUserDevice pUserDevice) {

        pUserDeviceMapper.addUserDevice(pUserDevice);
    }

    @Override
    public String getuidbyeid(String eid) {
        return pUserDeviceMapper.getuidbyeid(eid);
    }
}
