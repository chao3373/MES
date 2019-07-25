package com.shenke.entity;

import javax.persistence.*;


/**
 *
 * 图纸关联关系实体类
 *
 */
@Entity
@Table(name = "t_drawing_type")
public class DrawingType {

    @Id
    @GeneratedValue
    private Integer id; //id

    @ManyToOne
    @JoinColumn
    private Drawing drawing;//小图纸id

    @ManyToOne
    @JoinColumn
    private SaleList saleList; //订单

    private String state;//状态

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

    @Override
    public String toString() {
        return "DrawingType{" +
                "id=" + id +
                ", drawing=" + drawing +
                ", saleList=" + saleList +
                ", state='" + state + '\'' +
                '}';
    }
}
