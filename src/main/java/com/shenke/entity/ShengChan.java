package com.shenke.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_sheng_chan")
public class ShengChan {

    @Id
    @GeneratedValue
    private Integer id;

    @JoinColumn
    @ManyToOne
    private SaleList saleList; //订单对象

    @JoinColumn
    @ManyToOne
    private BigDrawing bigDrawing; //大图纸对象

    @JoinColumn
    @ManyToOne
    private Drawing drawing;//小图纸对象

    @JoinColumn
    @ManyToOne
    private Process process; //工序对象

//    private String datuCode; //大图标签编码
//
//    private String xiaotuCode;//小图标签编码

    private String biaoqianCode; // 标签编码

    private String state; // 状态

    private Double zbGongShi; // 准备工时

    private Double czGongShi; // 操作工时

    private Integer accomplishNum; //完成数量

    private Integer num;// 总数量

    private Integer allowNum; //允许生产数量（上一工序生产完成数量）

    private Date referDate;//需交日期

    private Integer code;//工序生产顺序

    private Integer isDatu; //判断是否为大图工序的标志（0 ：是  1：不是）

    private Date startDate; //工序的开始生产的时间  用于了解员工生产一个工序的起止时间

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getIsDatu() {
        return isDatu;
    }

    public void setIsDatu(Integer isDatu) {
        this.isDatu = isDatu;
    }

    public Integer getAllowNum() {
        return allowNum;
    }

    public void setAllowNum(Integer allowNum) {
        this.allowNum = allowNum;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getAccomplishNum() {
        return accomplishNum;
    }

    public void setAccomplishNum(Integer accomplishNum) {
        this.accomplishNum = accomplishNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getReferDate() {
        return referDate;
    }

    public void setReferDate(Date referDate) {
        this.referDate = referDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SaleList getSaleList() {
        return saleList;
    }

    public void setSaleList(SaleList saleList) {
        this.saleList = saleList;
    }

    public BigDrawing getBigDrawing() {
        return bigDrawing;
    }

    public void setBigDrawing(BigDrawing bigDrawing) {
        this.bigDrawing = bigDrawing;
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getZbGongShi() {
        return zbGongShi;
    }

    public void setZbGongShi(Double zbGongShi) {
        this.zbGongShi = zbGongShi;
    }

    public Double getCzGongShi() {
        return czGongShi;
    }

    public void setCzGongShi(Double czGongShi) {
        this.czGongShi = czGongShi;
    }

    public String getBiaoqianCode() {
        return biaoqianCode;
    }

    public void setBiaoqianCode(String biaoqianCode) {
        this.biaoqianCode = biaoqianCode;
    }

    @Override
    public String toString() {
        return "ShengChan{" +
                "id=" + id +
                ", saleList=" + saleList +
                ", bigDrawing=" + bigDrawing +
                ", drawing=" + drawing +
                ", process=" + process +
                ", biaoqianCode='" + biaoqianCode + '\'' +
                ", state='" + state + '\'' +
                ", zbGongShi=" + zbGongShi +
                ", czGongShi=" + czGongShi +
                ", accomplishNum=" + accomplishNum +
                ", num=" + num +
                ", allowNum=" + allowNum +
                ", referDate=" + referDate +
                ", code=" + code +
                ", isDatu=" + isDatu +
                ", startDate=" + startDate +
                '}';
    }
}
