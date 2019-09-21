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
                '}';
    }
}
