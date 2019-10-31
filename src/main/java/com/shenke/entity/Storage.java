package com.shenke.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * 商品入库
 */
@Entity
@Table(name = "t_storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn
    @ManyToOne
    private RuKu ruKu; // 入库 对象

    @JoinColumn
    @ManyToOne
    private SaleList saleList; //订单  对象

    private Date ruKuDate; // 入库时间

    private Date chuKuDate; //  出库时间

    private String state; //状态

    public SaleList getSaleList() {
        return saleList;
    }

    public void setSaleList(SaleList saleList) {
        this.saleList = saleList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RuKu getRuKu() {
        return ruKu;
    }

    public void setRuKu(RuKu ruKu) {
        this.ruKu = ruKu;
    }

    public Date getRuKuDate() {
        return ruKuDate;
    }

    public void setRuKuDate(Date ruKuDate) {
        this.ruKuDate = ruKuDate;
    }

    public Date getChuKuDate() {
        return chuKuDate;
    }

    public void setChuKuDate(Date chuKuDate) {
        this.chuKuDate = chuKuDate;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", ruKu=" + ruKu +
                ", saleList=" + saleList +
                ", ruKuDate=" + ruKuDate +
                ", chuKuDate=" + chuKuDate +
                ", state='" + state + '\'' +
                '}';
    }
}
