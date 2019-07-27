package com.shenke.entity;

import javax.persistence.*;

/***
 * 供应商
 */
@Entity
@Table(name = "t_supplier")
public class Supplier {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String remark;

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
