package com.shenke.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 工序设置实体类
 */

@Entity
@Table(name = "t_process")
public class Process {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer pNum;//工序代码

    private String name;//工序名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
        this.pNum = pNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", pNum=" + pNum +
                ", name='" + name + '\'' +
                '}';
    }
}
