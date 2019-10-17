package com.shenke.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_yuan_liao_require")
public class YuanLiaoRequire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // id

    @ManyToOne
    @JoinColumn
    private SaleList saleList; //订单 对象

    @ManyToOne
    @JoinColumn
    private User user; //用户 对象 （申请人）

    @ManyToOne
    @JoinColumn
    private Wuliao wuliao; //物料 对象

    private Date shenQingDate;//申请日期

    private Integer tao; // （几）套（原材料）

    private String state; //状态

    private Integer sumNum;//总件数

    public Integer getSumNum() {
        return sumNum;
    }

    public void setSumNum(Integer sumNum) {
        this.sumNum = sumNum;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wuliao getWuliao() {
        return wuliao;
    }

    public void setWuliao(Wuliao wuliao) {
        this.wuliao = wuliao;
    }

    public Date getShenQingDate() {
        return shenQingDate;
    }

    public void setShenQingDate(Date shenQingDate) {
        this.shenQingDate = shenQingDate;
    }

    public Integer getTao() {
        return tao;
    }

    public void setTao(Integer tao) {
        this.tao = tao;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "YuanLiaoRequire{" +
                "id=" + id +
                ", saleList=" + saleList +
                ", user=" + user +
                ", wuliao=" + wuliao +
                ", shenQingDate=" + shenQingDate +
                ", tao=" + tao +
                ", state='" + state + '\'' +
                ", sumNum=" + sumNum +
                '}';
    }
}
