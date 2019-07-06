package com.shenke.entity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;

/**
 * 客户实体类
 *
 */

@Entity
@Table(name = "t_client")
public class Client {

    @Id
    @GeneratedValue
    private Integer id;  //id

    @Column(length = 50)
    private String name; //客户名称

    @Column(length = 50)
    private String tel; //客户联系方式

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
