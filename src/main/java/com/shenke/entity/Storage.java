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
    private SaleList saleList; //订单  对象

    private Date fahuoDate; //发货时间

    private Integer num; //发货数量

    private String fahuoNumber; // 发货单号

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

    public Date getFahuoDate() {
        return fahuoDate;
    }

    public void setFahuoDate(Date fahuoDate) {
        this.fahuoDate = fahuoDate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getFahuoNumber() {
        return fahuoNumber;
    }

    public void setFahuoNumber(String fahuoNumber) {
        this.fahuoNumber = fahuoNumber;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", saleList=" + saleList +
                ", fahuoDate=" + fahuoDate +
                ", num=" + num +
                ", fahuoNumber=" + fahuoNumber +
                '}';
    }
}
