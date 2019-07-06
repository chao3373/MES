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
    private Process process;//图纸工序对象

    private String neiRong;//工作内容

    private Double manHour;//工时

    private String state; //完成状态

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

    public String getNeiRong() {
        return neiRong;
    }

    public void setNeiRong(String neiRong) {
        this.neiRong = neiRong;
    }

    public Double getManHour() {
        return manHour;
    }

    public void setManHour(Double manHour) {
        this.manHour = manHour;
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
                ", neiRong='" + neiRong + '\'' +
                ", manHour=" + manHour +
                ", state='" + state + '\'' +
                '}';
    }
}
