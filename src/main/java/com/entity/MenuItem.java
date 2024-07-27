package com.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "menu")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @Column(name = "dish_price")
    private String name;
    private Double price;
    private Double weight;
    private Boolean discount;

    public MenuItem(String name, Double price, Double weight, Boolean discount) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }
}
