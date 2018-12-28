package com.testtask.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "productid")
    private int id;

    @Column(name = "nameProduct")
    private String nameProduct;

    @Column(name = "isNeeded")
    private byte isNeeded;

    @Column(name = "amount")
    private int amount;

    public Product() {
    }

    public Product(String nameProduct, byte isNeeded, int amount) {
        this.nameProduct = nameProduct;
        this.isNeeded = isNeeded;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public byte getIsNeeded() {
        return isNeeded;
    }

    public void setIsNeeded(byte isNeeded) {
        this.isNeeded = isNeeded;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
