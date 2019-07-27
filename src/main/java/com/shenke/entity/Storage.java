package com.shenke.entity;


import javax.persistence.*;

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
    private SaleList saleList; //订单Id

    @JoinColumn
    @ManyToOne
    private BigDrawing bigDrawing; //大图纸

    @JoinColumn
    @ManyToOne
    private Drawing drawing; //小图纸

    private String state;//状态

    private String standard; //产品标准

    private String remark;//备注

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", saleList=" + saleList +
                ", bigDrawing=" + bigDrawing +
                ", drawing=" + drawing +
                ", state='" + state + '\'' +
                ", standard='" + standard + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
