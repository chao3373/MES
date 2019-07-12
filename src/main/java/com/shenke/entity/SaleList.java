package com.shenke.entity;


import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table( name = "t_sale_list")
public class SaleList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//id

    @Column(length=100)
    private String  saleNumber; //订单编号

    @Column(length = 20)
    private String hangHao;//行号

    private Integer wuliaoId;//物料编号

    @Column(length = 100)
    private String tuzhiName;//图纸名称

    @Column(length = 100)
    private String tuzhiId;//图纸编号

    private Integer num ; //数量

    private Integer accomplishNum;//完成数量

    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;//销售日期

    @Temporal(TemporalType.TIMESTAMP)
    private Date referDate;//需要提交的日期

    @Column(length = 20)
    private String xiangmuId;//项目编号

    @Column(length = 20)
    private String shenqingNumber;//申请编号

    @Column(length = 20)
    private  String kucunzuzhi;//库存组织

    @Column(length = 50)
    private String state;//订单状态

    private String cunzai;//判断导入的订单是否已存在

    public String getCunzai() {
        return cunzai;
    }

    public void setCunzai(String cunzai) {
        this.cunzai = cunzai;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getHangHao() {
        return hangHao;
    }

    public void setHangHao(String hangHao) {
        this.hangHao = hangHao;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getReferDate() {
        return referDate;
    }

    public void setReferDate(Date referDate) {
        this.referDate = referDate;
    }

    public String getXiangmuId() {
        return xiangmuId;
    }

    public void setXiangmuId(String xiangmuId) {
        this.xiangmuId = xiangmuId;
    }

    public String getShenqingNumber() {
        return shenqingNumber;
    }

    public void setShenqingNumber(String shenqingNumber) {
        this.shenqingNumber = shenqingNumber;
    }

    public String getKucunzuzhi() {
        return kucunzuzhi;
    }

    public void setKucunzuzhi(String kucunzuzhi) {
        this.kucunzuzhi = kucunzuzhi;
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

    public Integer getWuliaoId() {
        return wuliaoId;
    }

    public void setWuliaoId(Integer wuliaoId) {
        this.wuliaoId = wuliaoId;
    }

    public String getTuzhiName() {
        return tuzhiName;
    }

    public void setTuzhiName(String tuzhiName) {
        this.tuzhiName = tuzhiName;
    }

    public String getTuzhiId() {
        return tuzhiId;
    }

    public void setTuzhiId(String tuzhiId) {
        this.tuzhiId = tuzhiId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SaleList{" +
                "id=" + id +
                ", saleNumber='" + saleNumber + '\'' +
                ", hangHao='" + hangHao + '\'' +
                ", wuliaoId=" + wuliaoId +
                ", tuzhiName='" + tuzhiName + '\'' +
                ", tuzhiId='" + tuzhiId + '\'' +
                ", num=" + num +
                ", saleDate=" + saleDate +
                ", referDate=" + referDate +
                ", xiangmuId='" + xiangmuId + '\'' +
                ", shenqingNumber='" + shenqingNumber + '\'' +
                ", kucunzuzhi='" + kucunzuzhi + '\'' +
                ", state='" + state + '\'' +
                ", cunzai='" + cunzai + '\'' +
                '}';
    }
}
