package com.shenke.entity;


import javax.persistence.*;

@Entity
@Table(name="t_drawing")
public class Drawing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//id

    @Column(length = 100)
    private String drawingId;//图纸编号

    @Column(length = 100)
    private String name;//图纸名称

    @Column(length = 500)
    private String drawingURL;//图纸路径

    @Column(length = 20)
    private Integer  pid;//所属图纸ID


    public String getDrawingId() {
        return drawingId;
    }

    public void setDrawingId(String drawingId) {
        this.drawingId = drawingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrawingURL() {
        return drawingURL;
    }

    public void setDrawingURL(String drawingURL) {
        this.drawingURL = drawingURL;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Drawing{" +
                "id=" + id +
                ", drawingId='" + drawingId + '\'' +
                ", name='" + name + '\'' +
                ", drawingURL='" + drawingURL + '\'' +
                ", pid=" + pid +
                '}';
    }
}
