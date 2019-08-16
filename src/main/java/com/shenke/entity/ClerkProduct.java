package com.shenke.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 员工工作情况监控
 */
@Entity
@Table(name = "t_clerkProduct")
public class ClerkProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//id

    @ManyToOne
    @JoinColumn
    private Clerk clerk; //员工

    @ManyToOne
    @JoinColumn
    private DrawingProcess drawingProcess;//图纸工序

    private Integer num; //完成数量

    private Date dateInProduct;//生产日期

    public Date getDateInProduct() {
        return dateInProduct;
    }

    public void setDateInProduct(Date dateInProduct) {
        this.dateInProduct = dateInProduct;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
    }

    public DrawingProcess getDrawingProcess() {
        return drawingProcess;
    }

    public void setDrawingProcess(DrawingProcess drawingProcess) {
        this.drawingProcess = drawingProcess;
    }

    @Override
    public String toString() {
        return "ClerkProduct{" +
                "id=" + id +
                ", clerk=" + clerk +
                ", drawingProcess=" + drawingProcess +
                ", num=" + num +
                ", dateInProduct=" + dateInProduct +
                '}';
    }
}
