package com.example.pironeer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String name;
    public int price;
    public int amount;

    protected Product(){}
    public Product(String name, int price, int amount){
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
         return name;
    }

    public int getPrice(){
        return price;
    }
    public int getStockQuantity(){
        return amount;
    }

    public void removeAmount(int amount){
        if (this.amount < amount){
            throw new IllegalStateException();
        }
        this.amount -= amount;
    }

    public Long getId() {
        return id;
    }

    public void addAmount(int amount){
        this.amount += amount;
    }
}
