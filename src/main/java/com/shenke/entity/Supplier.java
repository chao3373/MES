package com.shenke.entity;


import javax.persistence.*;

/***
 * 供应商实体类
 *
 */
@Entity
@Table(name = "t_supplier")
public class Supplier {

    @Id
    @GeneratedValue
    private Integer id; //id

    @Column(length = 50)
    private String name;  //姓名

    @Column(length = 500)
    private String remark; //备注

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

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
