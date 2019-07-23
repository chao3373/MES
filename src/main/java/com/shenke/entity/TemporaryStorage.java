package com.shenke.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_temporary_storage")
public class TemporaryStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id

    @ManyToOne
    @JoinColumn
    private BigDrawing bigDrawing; //大图纸

    @ManyToOne
    @JoinColumn
    private Drawing drawing;  //小图纸

    private String state;  //状态

    private String  standard;  //执行标准

    private String remark; // 备注

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

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TemporaryStorage{" +
                "id=" + id +
                ", bigDrawing=" + bigDrawing +
                ", drawing=" + drawing +
                ", state='" + state + '\'' +
                ", standard='" + standard + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
