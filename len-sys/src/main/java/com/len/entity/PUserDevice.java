package com.len.entity;

import javax.persistence.*;

@Table(name = "p_user_device")
public class PUserDevice {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer userid;

    private Integer deviceid;

    /**
     * @return userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return deviceid
     */
    public Integer getDeviceid() {
        return deviceid;
    }

    /**
     * @param deviceid
     */
    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }
}