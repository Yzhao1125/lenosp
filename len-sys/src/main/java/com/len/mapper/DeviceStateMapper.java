package com.len.mapper;

import java.util.List;
import java.util.HashMap;
import java.util.List;

import com.len.entity.Device_state;
import com.len.entity.PDevice;
import org.apache.ibatis.annotations.*;

public interface DeviceStateMapper extends com.len.base.BaseMapper<PDevice,String> {

   // int createNewTable(String tablename);

    List<Device_state> selectDeviceStateByEId(String tablename, String eid);

   // int saveDeviceState(String tablename, Device_state device_state);

    int isTableExist(String tablename);  //存在表格返回1，不存在返回0

    int createNewTable(@Param("tablename") String tablename);

    int saveDeviceState(@Param("tablename") String tablename,@Param("device_state") Device_state device_state);

  /*  int isTableExist(String tableName);

    List<device_state>  selectDeviceStateByTimePart(@Param("tableName")String tableName,@Param("startTime")String startTime,@Param("endTime")String endTime);

   */
    @Override
    List<PDevice> selectListByPage(PDevice record);
}
