package com.example.deni.labmobile.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Deni on 06/11/2017.
 */

@Entity(tableName = "product")
public class Product {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name="name")
    public String name;
    @ColumnInfo(name="price")
    public Integer price;
    @ColumnInfo(name="quantity")
    public Integer quantity;

    public Product(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Name " + name + ", price:  " + price +
                ", quantity=" + quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}