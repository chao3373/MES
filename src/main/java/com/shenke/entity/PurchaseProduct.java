package com.shenke.entity;


import javax.persistence.*;

@Entity
@Table(name = "t_purchase_product")
public class PurchaseProduct {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50)
    private String name;//商品名称

    @Column(nullable = true)
    private Double weight;//单件重量

    @Column(nullable = true)
    private Double price;//单价

    @Column(nullable = true)
    private Double sumPrice;//商品总价

    @ManyToOne
    @JoinColumn
    private Purchase purchase;//采购订单

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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public String toString() {
        return "PurchaseProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", sumPrice=" + sumPrice +
                ", purchase=" + purchase +
                '}';
    }
}
