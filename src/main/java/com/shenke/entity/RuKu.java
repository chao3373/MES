package com.shenke.entity;

import javax.persistence.*;

/**
 * 入库单实体类（为准备入库的小图纸）
 */
@Entity
@Table(name = "t_ru_ku")
public class RuKu {

    @Id
    @GeneratedValue
    private Integer id; //id

    @JoinColumn
    @ManyToOne
    private BigDrawing bigDrawing; // 大图纸 对象

    @JoinColumn
    @ManyToOne
    private Drawing drawing; // 小图纸 对象

    @JoinColumn
    @ManyToOne
    private SaleList saleList;//订单对象

    private String datuCode; //大图编码

    private String xiaotuCode;//小图编码

    private Integer num; // 数量

    private Integer rukuNum;//入库数量

    private String state;//状态

    public Integer getRukuNum() {
        return rukuNum;
    }

    public void setRukuNum(Integer rukuNum) {
        this.rukuNum = rukuNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getXiaotuCode() {
        return xiaotuCode;
    }

    public void setXiaotuCode(String xiaotuCode) {
        this.xiaotuCode = xiaotuCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public SaleList getSaleList() {
        return saleList;
    }

    public void setSaleList(SaleList saleList) {
        this.saleList = saleList;
    }

    public String getDatuCode() {
        return datuCode;
    }

    public void setDatuCode(String datuCode) {
        this.datuCode = datuCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "RuKu{" +
                "id=" + id +
                ", bigDrawing=" + bigDrawing +
                ", drawing=" + drawing +
                ", saleList=" + saleList +
                ", datuCode='" + datuCode + '\'' +
                ", xiaotuCode='" + xiaotuCode + '\'' +
                ", num=" + num +
                ", rukuNum=" + rukuNum +
                ", state='" + state + '\'' +
                '}';
    }
}
