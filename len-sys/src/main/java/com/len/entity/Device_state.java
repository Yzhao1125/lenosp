package com.len.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author pc
 *implements Serializable
 */
@Data
@ToString
public class Device_state {

    private int  id;  //主键ID
    private String eid;   //设备ID
    private String ip;    //设备的IP
    private String t1;	  // T1喷头温度
    private String t2;	  // T2喷头温度
    private String t3;	  // 热床温度
    private String x;     //打印喷头的X坐标
    private String y;     //打印喷头的Y坐标
    private String z;	  //打印喷头的Z坐标
    private String e;    //挤料数据
    private String t;	  //打印进度值
    private Timestamp createTime;    //存储的时间

    public Device_state(){
        super();
    }

    public Device_state(String eid, String ip, String t1, String t2,
                        String t3, String x, String y, String z, String e,String t) {
        super();
        this.eid = eid;
        this.ip = ip;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.x = x;
        this.y = y;
        this.z = z;
        this.e = e;
        this.t = t;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getEid() {
        return eid;
    }
    public void setEid(String eid) {
        this.eid = eid;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getT1() {
        return t1;
    }
    public void setT1(String t1) {
        this.t1 = t1;
    }
    public String getT2() {
        return t2;
    }
    public void setT2(String t2) {
        this.t2 = t2;
    }
    public String getT3() {
        return t3;
    }
    public void setT3(String t3) {
        this.t3 = t3;
    }
    public String getX() {
        return x;
    }
    public void setX(String x) {
        this.x = x;
    }
    public String getY() {
        return y;
    }
    public void setY(String y) {
        this.y = y;
    }
    public String getZ() {
        return z;
    }
    public void setZ(String z) {
        this.z = z;
    }
    public String getT() {
        return t;
    }
    public void setT(String t) {
        this.t = t;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

//    @Override
//    public String toString() {
//        return "device_state [id=" + id +", eid="+ eid + ", ip=" + ip
//                + ", t1=" + t1 + ", t2=" + t2 + ", t3=" + t3 + ", x=" + x
//                + ", y=" + y + ", z=" + z +", e="+ e + ", t=" + t + ", createTime=" + createTime + "]";
//    }
}
