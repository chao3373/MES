package com.shenke.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user_product")
public class UserProduct {

    @Id
    @GeneratedValue
    private Integer id; //id

    @JoinColumn
    @ManyToOne
    private ShengChan shengChan; //生产 对象

    @JoinColumn
    @ManyToOne
    private User user; //用户 对象

    @JoinColumn
    @ManyToOne
    private BigDrawing bigDrawing; //大图 对象

    @JoinColumn
    @ManyToOne
    private Drawing drawing; //小图 对象

    @JoinColumn
    @ManyToOne
    private Process process; //工序 对象

    @JoinColumn
    @ManyToOne
    private SaleList saleList;//订单

    private String xiangmuId;  //项目编号

    private Integer num; //生产数量

    private Date dateInProduct;//生产日期

    private Double zbGongShi; // 准备工时

    private Double czGongShi; // 操作工时

    @Transient
    private String userName;// （查询用）用户名

    @Transient
    private Integer processId;//(查询用) 工序Id

    @Transient
    private Date bDate;//起始日期 （查询用）

    @Transient
    private Date eDate;//结束日期 （查询用）

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ShengChan getShengChan() {
        return shengChan;
    }

    public void setShengChan(ShengChan shengChan) {
        this.shengChan = shengChan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public SaleList getSaleList() {
        return saleList;
    }

    public void setSaleList(SaleList saleList) {
        this.saleList = saleList;
    }

    public String getXiangmuId() {
        return xiangmuId;
    }

    public void setXiangmuId(String xiangmuId) {
        this.xiangmuId = xiangmuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getDateInProduct() {
        return dateInProduct;
    }

    public void setDateInProduct(Date dateInProduct) {
        this.dateInProduct = dateInProduct;
    }

    @Override
    public String toString() {
        return "UserProduct{" +
                "id=" + id +
                ", shengChan=" + shengChan +
                ", user=" + user +
                ", bigDrawing=" + bigDrawing +
                ", drawing=" + drawing +
                ", process=" + process +
                ", saleList=" + saleList +
                ", xiangmuId='" + xiangmuId + '\'' +
                ", num=" + num +
                ", dateInProduct=" + dateInProduct +
                ", zbGongShi=" + zbGongShi +
                ", czGongShi=" + czGongShi +
                ", userName='" + userName + '\'' +
                ", processId=" + processId +
                ", bDate=" + bDate +
                ", eDate=" + eDate +
                '}';
    }
}
