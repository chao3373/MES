package com.shenke.entity;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "t_bigDrawing")
public class BigDrawing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id

    @Column(length = 100)
    private String drawingId; //图纸编号

    @Column(length = 100)
    private String name;//图纸名称

    @Column(length = 2000)
    private String url;//图纸路径

    @Transient
    private MultipartFile drawingURL;//图纸

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MultipartFile getDrawingURL() {
        return drawingURL;
    }

    public void setDrawingURL(MultipartFile drawingURL) {
        this.drawingURL = drawingURL;
    }

    @Override
    public String toString() {
        return "BigDrawing{" +
                "id=" + id +
                ", drawingId='" + drawingId + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", drawingURL=" + drawingURL +
                '}';
    }
}
