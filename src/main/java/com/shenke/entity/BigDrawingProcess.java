package com.shenke.entity;


import javax.persistence.*;

@Entity
@Table(name = "t_big_drawing_process")
public class BigDrawingProcess {

    @Id
    @GeneratedValue
    private Integer id;

    @JoinColumn
    @ManyToOne
    private BigDrawing bigDrawing;

    @JoinColumn
    @ManyToOne
    private Process process;

    private Integer code; //工序生产顺序

    private Double zbGongShi; // 准备工时

    private Double czGongShi; // 操作工时

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public BigDrawing getBigDrawing() {
        return bigDrawing;
    }

    public void setBigDrawing(BigDrawing bigDrawing) {
        this.bigDrawing = bigDrawing;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "BigDrawingProcess{" +
                "id=" + id +
                ", bigDrawing=" + bigDrawing +
                ", process=" + process +
                ", code=" + code +
                ", zbGongShi=" + zbGongShi +
                ", czGongShi=" + czGongShi +
                '}';
    }
}
