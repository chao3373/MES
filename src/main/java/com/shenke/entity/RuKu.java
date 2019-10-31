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
    private SaleList saleList;//订单对象

    private String datuCode; //大图编码

    private Integer orderNum; // 订单数量

    private Integer num; // 数量

    private Integer rukuNum;//入库数量

    private String state;//状态

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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

    public String getDatuCode() {
        return datuCode;
    }

    public void setDatuCode(String datuCode) {
        this.datuCode = datuCode;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getRukuNum() {
        return rukuNum;
    }

    public void setRukuNum(Integer rukuNum) {
        this.rukuNum = rukuNum;
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
                ", saleList=" + saleList +
                ", datuCode='" + datuCode + '\'' +
                ", orderNum=" + orderNum +
                ", num=" + num +
                ", rukuNum=" + rukuNum +
                ", state='" + state + '\'' +
                '}';
    }
}
