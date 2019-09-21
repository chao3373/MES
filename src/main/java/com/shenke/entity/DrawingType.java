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
    private BigDrawing bigDrawing; //大图纸id

    @Transient
    private String gongxus; //所属工序

    public String getGongxus() {
        return gongxus;
    }

    public void setGongxus(String gongxus) {
        this.gongxus = gongxus;
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

    public BigDrawing getBigDrawing() {
        return bigDrawing;
    }

    public void setBigDrawing(BigDrawing bigDrawing) {
        this.bigDrawing = bigDrawing;
    }

    @Override
    public String toString() {
        return "DrawingType{" +
                "id=" + id +
                ", drawing=" + drawing +
                ", bigDrawing=" + bigDrawing +
                ", gongxus='" + gongxus + '\'' +
                '}';
    }
}
