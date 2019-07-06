package com.shenke.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 采购单实体类
 * @Param:
 * @return:
 * @Date:
 */
@Entity
@Table(name = "t_purchase")
public class Purchase {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50)
    private String purchaseNumber;//采购单号

    @Column(length = 50)
    private String purchaseAgent;//采购人

    private Date purchaseDate;//采购日期

    @Column(nullable = true)
    private Double sumWeight; //订单合计重量

    @Column(nullable = true)
    private Double sumMoney;//订单合计金额

    @Column(length = 500)
    private String remark;//备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getPurchaseAgent() {
        return purchaseAgent;
    }

    public void setPurchaseAgent(String purchaseAgent) {
        this.purchaseAgent = purchaseAgent;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getSumWeight() {
        return sumWeight;
    }

    public void setSumWeight(Double sumWeight) {
        this.sumWeight = sumWeight;
    }

    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", purchaseNumber='" + purchaseNumber + '\'' +
                ", purchaseAgent='" + purchaseAgent + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", sumWeight=" + sumWeight +
                ", sumMoney=" + sumMoney +
                ", remark='" + remark + '\'' +
                '}';
    }
}
