package com.shenke.entity;

import javax.persistence.*;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 09:38
 * @Description:
 */
@Entity
@Table(name = "t_keShi")
public class KeShi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;//科室名称

    @Override
    public String toString() {
        return "KeShi{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}
