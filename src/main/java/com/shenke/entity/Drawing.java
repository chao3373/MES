package com.shenke.entity;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name="t_drawing")
public class Drawing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//id

    @Column(length = 100)
    private String wuliaoId;//物料编号

    @Column(length = 2000)
    private String url;//图纸路径

    @Transient
    private MultipartFile drawingURL;//图纸

    @Override
    public String toString() {
        return "Drawing{" +
                "id=" + id +
                ", wuliaoId='" + wuliaoId + '\'' +
                ", url='" + url + '\'' +
                ", drawingURL=" + drawingURL +
                '}';
    }

    public String getWuliaoId() {
        return wuliaoId;
    }

    public void setWuliaoId(String wuliaoId) {
        this.wuliaoId = wuliaoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MultipartFile getDrawingURL() {
        return drawingURL;
    }

    public void setDrawingURL(MultipartFile drawingURL) {
        this.drawingURL = drawingURL;
    }

}
