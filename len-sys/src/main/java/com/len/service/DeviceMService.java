package com.len.service;

import com.alibaba.fastjson.JSONObject;
import com.len.base.BaseService;
import com.len.entity.Device_state;
import com.len.entity.PDevice;
import com.len.util.ReType;


import java.util.List;

public interface DeviceMService extends BaseService <PDevice,String> {

    int saveDeviceState(JSONObject jsonObject);

    List<Device_state> selectDeviceStateByEId(String tablename, String eid);

    void savedevicestate(String tablename,Device_state device_state);

    int isTableExist(String tableName);


    List<Device_state> getDeviceStateList(String eid);
}
