package com.shenke.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 员工展开工时统计
 */
@Entity
@Table(name = "t_open_time_count")
public class OpenTimeCount {

    @Id
    @GeneratedValue
    private Integer id;

    private String wuliaoId; // 物料编号

    private String userName; // 员工名称

    private Double yuGuGongShi; //预估工时

    private Double shiJiGongShi; //实际展开工时

    private java.util.Date startDate; // 开始时间

    private java.util.Date stopDate; // 结束时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWuliaoId() {
        return wuliaoId;
    }

    public void setWuliaoId(String wuliaoId) {
        this.wuliaoId = wuliaoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getYuGuGongShi() {
        return yuGuGongShi;
    }

    public void setYuGuGongShi(Double yuGuGongShi) {
        this.yuGuGongShi = yuGuGongShi;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Double getShiJiGongShi() {
        return shiJiGongShi;
    }

    public void setShiJiGongShi(Double shiJiGongShi) {
        this.shiJiGongShi = shiJiGongShi;
    }

    @Override
    public String toString() {
        return "OpenTimeCount{" +
                "id=" + id +
                ", wuliaoId='" + wuliaoId + '\'' +
                ", userName='" + userName + '\'' +
                ", yuGuGongShi=" + yuGuGongShi +
                ", shiJiGongShi=" + shiJiGongShi +
                ", startDate=" + startDate +
                ", stopDate=" + stopDate +
                '}';
    }
}
