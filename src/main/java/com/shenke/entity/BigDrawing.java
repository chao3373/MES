package com.shenke.entity;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_bigDrawing")
public class BigDrawing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id

    @Column(length = 100)
    private String wuliaoId; //物料编号

    private String tuZhiName;//图纸名称

    @Column(length = 2000)
    private String url;//图纸路径

    @Transient
    private MultipartFile drawingURL;//图纸

    //预估展开工时
    private Double yuGuGongShi;

    //实际展开工时
    private Double shiJiGongShi;

    //开始展开时间
    private Date startDate;

    //结束展开时间
    private Date stopDate;

    public String getTuZhiName() {
        return tuZhiName;
    }

    public void setTuZhiName(String tuZhiName) {
        this.tuZhiName = tuZhiName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Double getShiJiGongShi() {
        return shiJiGongShi;
    }

    public void setShiJiGongShi(Double shiJiGongShi) {
        this.shiJiGongShi = shiJiGongShi;
    }

    public Double getYuGuGongShi() {
        return yuGuGongShi;
    }

    public void setYuGuGongShi(Double yuGuGongShi) {
        this.yuGuGongShi = yuGuGongShi;
    }

    public String getWuliaoId() {
        return wuliaoId;
    }

    public void setWuliaoId(String wuliaoId) {
        this.wuliaoId = wuliaoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                ", wuliaoId='" + wuliaoId + '\'' +
                ", tuZhiName='" + tuZhiName + '\'' +
                ", url='" + url + '\'' +
                ", drawingURL=" + drawingURL +
                ", yuGuGongShi=" + yuGuGongShi +
                ", shiJiGongShi=" + shiJiGongShi +
                ", startDate=" + startDate +
                ", stopDate=" + stopDate +
                '}';
    }
}
