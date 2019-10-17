package com.shenke.entity;

import org.springframework.transaction.annotation.Transactional;

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

    private Integer num; // 数量

    @Transient
    private String gongxus; //所属工序 (临时接收数据用)

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

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
                ", num=" + num +
                ", gongxus='" + gongxus + '\'' +
                '}';
    }
}
