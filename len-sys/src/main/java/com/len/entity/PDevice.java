package com.len.entity;

import javax.persistence.*;

@Table(name = "p_device")
public class PDevice {
    /**
     * 序号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 设备编号
     */
    @Column(name = "deviceId")
    private String deviceid;

    /**
     * 设备密码
     */
    @Column(name = "devicePW")
    private String devicepw;

    /**
     * 设备名称
     */
    private String dname;

    /**
     * 连接状态
     */
    private String connect;

    /**
     * 设备IP
     */
    @Column(name = "IP")
    private String ip;

    /**
     * 获取序号
     *
     * @return id - 序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置序号
     *
     * @param id 序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取设备编号
     *
     * @return deviceId - 设备编号
     */
    public String getDeviceid() {
        return deviceid;
    }

    /**
     * 设置设备编号
     *
     * @param deviceid 设备编号
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    /**
     * 获取设备密码
     *
     * @return devicePW - 设备密码
     */
    public String getDevicepw() {
        return devicepw;
    }

    /**
     * 设置设备密码
     *
     * @param devicepw 设备密码
     */
    public void setDevicepw(String devicepw) {
        this.devicepw = devicepw == null ? null : devicepw.trim();
    }

    /**
     * 获取设备名称
     *
     * @return dname - 设备名称
     */
    public String getDname() {
        return dname;
    }

    /**
     * 设置设备名称
     *
     * @param dname 设备名称
     */
    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    /**
     * 获取连接状态
     *
     * @return connect - 连接状态
     */
    public String getConnect() {
        return connect;
    }

    /**
     * 设置连接状态
     *
     * @param connect 连接状态
     */
    public void setConnect(String connect) {
        this.connect = connect == null ? null : connect.trim();
    }

    /**
     * 获取设备IP
     *
     * @return IP - 设备IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置设备IP
     *
     * @param ip 设备IP
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    @Override
    public String toString() {
        return "PDevice{" +
                "id=" + id +
                ", deviceid='" + deviceid + '\'' +
                ", devicepw='" + devicepw + '\'' +
                ", dname='" + dname + '\'' +
                ", connect='" + connect + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}