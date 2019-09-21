package com.shenke.entity;


import javax.persistence.*;

/**
 * 大图物料明细单
 */
@Entity
@Table(name = "t_wuliao")
public class Wuliao {

    @Id
    @GeneratedValue
    private Integer id;  // id

    @JoinColumn
    @ManyToOne
    private BigDrawing bigDrawing;

    private String wuliao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDrawing getBigDrawing() {
        return bigDrawing;
    }

    public void setBigDrawing(BigDrawing bigDrawing) {
        this.bigDrawing = bigDrawing;
    }

    public String getWuliao() {
        return wuliao;
    }

    public void setWuliao(String wuliao) {
        this.wuliao = wuliao;
    }

    @Override
    public String toString() {
        return "Wuliao{" +
                "id=" + id +
                ", bigDrawing=" + bigDrawing +
                ", wuliao='" + wuliao + '\'' +
                '}';
    }
}
