package com.shenke.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_fanXiu")
public class FanXiu {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "SaleListId")
    private SaleList saleList;

    @ManyToMany
    @JoinTable(name = "fanXiuProcess", joinColumns = @JoinColumn(name = "fanXiuId"), inverseJoinColumns = @JoinColumn(name = "GongXuId"))
    private Process process;

    @Override
    public String toString() {
        return "FanXiu{" +
                "id=" + id +
                ", saleList=" + saleList +
                ", process=" + process +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SaleList getSaleList() {
        return saleList;
    }

    public void setSaleList(SaleList saleList) {
        this.saleList = saleList;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}
