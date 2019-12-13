package com.shenke.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_show_gong_shi")
public class ShowGongShi {



//    private Integer id;//

    @Id
    @GeneratedValue
    private Integer isShow; // 是否显示（0：显示  1：不显示）

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return "ShowGongShi{" +
                "isShow=" + isShow +
                '}';
    }
}
