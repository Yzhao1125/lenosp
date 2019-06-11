package com.len.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "p_device")
@Data
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


    private String userId;



}