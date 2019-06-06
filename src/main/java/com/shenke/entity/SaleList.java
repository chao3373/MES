package com.shenke.entity;


import javax.persistence.*;

@Entity
@Table( name = "t_sale_list")
public class SaleList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//id

    @Column(length=100)
    private String  saleNumber; //订单编号

    @Column(length  = 100)
    private String wuliaoId;//物料编号

    @Column(length = 100)
    private String tuzhiName;//图纸名称

    @Column(length = 100)
    private String tuzhiID;//图纸编号

    @Column(length = 11)
    private Integer num ; //数量

    @Override
    public String toString() {
        return "SaleList{" +
                "id=" + id +
                ", saleNumber='" + saleNumber + '\'' +
                ", wuliaoId='" + wuliaoId + '\'' +
                ", tuzhiName='" + tuzhiName + '\'' +
                ", tuzhiID='" + tuzhiID + '\'' +
                ", num=" + num +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getWuliaoId() {
        return wuliaoId;
    }

    public void setWuliaoId(String wuliaoId) {
        this.wuliaoId = wuliaoId;
    }

    public String getTuzhiName() {
        return tuzhiName;
    }

    public void setTuzhiName(String tuzhiName) {
        this.tuzhiName = tuzhiName;
    }

    public String getTuzhiID() {
        return tuzhiID;
    }

    public void setTuzhiID(String tuzhiID) {
        this.tuzhiID = tuzhiID;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
