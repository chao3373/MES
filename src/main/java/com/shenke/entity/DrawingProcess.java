package com.shenke.entity;


import javax.persistence.*;

@Entity
@Table(name = "t_drawing_process")
public class DrawingProcess {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn
    private BigDrawing bigDrawing;

    @ManyToOne
    @JoinColumn
    private Drawing drawing; //小图纸对象

    @ManyToOne
    @JoinColumn
    private Process process;//工序对象

    @ManyToOne
    @JoinColumn
    private SaleList saleList;

    private String state; //完成状态

    private Integer num;//数量

    private Integer accomplishNum;//完成数量

    private String saleNumber;//销售单号

    private String informNum;//生产通知单号

    public String getInformNum() {
        return informNum;
    }

    public void setInformNum(String informNum) {
        this.informNum = informNum;
    }

    public SaleList getSaleList() {
        return saleList;
    }

    public void setSaleList(SaleList saleList) {
        this.saleList = saleList;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getAccomplishNum() {
        return accomplishNum;
    }

    public void setAccomplishNum(Integer accomplishNum) {
        this.accomplishNum = accomplishNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDrawing getBigDrawing() {
        return bigDrawing;
    }

    public void setBigDrawing(BigDrawing bigDrawing) {
        this.bigDrawing = bigDrawing;
    }

    @Override
    public String toString() {
        return "DrawingProcess{" +
                "id=" + id +
                ", bigDrawing=" + bigDrawing +
                ", drawing=" + drawing +
                ", process=" + process +
                ", saleList=" + saleList +
                ", state='" + state + '\'' +
                ", num=" + num +
                ", accomplishNum=" + accomplishNum +
                ", saleNumber='" + saleNumber + '\'' +
                ", informNum='" + informNum + '\'' +
                '}';
    }
}
