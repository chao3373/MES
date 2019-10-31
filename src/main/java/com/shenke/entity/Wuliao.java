package com.shenke.entity;



import javax.persistence.*;
import java.util.Date;

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
    private BigDrawing bigDrawing; //大图 对象

    private String name; // 名称

    private String guiGe; //规格

    private Integer num; //数量

    private String changJia; //厂家

    private String remark; //备注

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuiGe() {
        return guiGe;
    }

    public void setGuiGe(String guiGe) {
        this.guiGe = guiGe;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getChangJia() {
        return changJia;
    }

    public void setChangJia(String changJia) {
        this.changJia = changJia;
    }

    @Override
    public String toString() {
        return "Wuliao{" +
                "id=" + id +
                ", bigDrawing=" + bigDrawing +
                ", name='" + name + '\'' +
                ", guiGe='" + guiGe + '\'' +
                ", num=" + num +
                ", changJia='" + changJia + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
