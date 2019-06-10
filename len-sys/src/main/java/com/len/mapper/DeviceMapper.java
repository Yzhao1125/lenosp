package com.len.mapper;

import com.len.entity.PDevice;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeviceMapper {


    @Select("select id,deviceid,devicePW,dname,connect,IP from p_device")
    List<PDevice> selectAll();

}
