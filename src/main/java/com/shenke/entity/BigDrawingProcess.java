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
                '}';
    }
}
