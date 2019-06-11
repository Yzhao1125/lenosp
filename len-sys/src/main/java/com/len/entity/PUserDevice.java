package com.len.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "p_user_device")
@Data
public class PUserDevice {

    @Id
    private String userid;

    @Id
    private String deviceid;


}