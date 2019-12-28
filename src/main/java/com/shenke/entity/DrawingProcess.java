package com.shenke.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_drawing_process")
public class DrawingProcess {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Drawing drawing; //小图纸对象

    @ManyToOne
    @JoinColumn
    private Process process;//工序对象

    private Integer code; //工序生产顺序

    private Double zbGongShi; // 准备工时

    private Double czGongShi; // 操作工时

    private Integer num; // num来源于 drawingType中一个大图对应几个小图

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "DrawingProcess{" +
                "id=" + id +
                ", drawing=" + drawing +
                ", process=" + process +
                ", code=" + code +
                ", zbGongShi=" + zbGongShi +
                ", czGongShi=" + czGongShi +
                ", num=" + num +
                '}';
    }
}
