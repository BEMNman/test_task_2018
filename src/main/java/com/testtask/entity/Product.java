package com.testtask.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "idProduct")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idProduct")
    private Integer id;

    @Column(name = "product")
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

    public Integer getId() {
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

    @Override
    public String toString() {
        return "id: " + getId() + " "
                + "product: " + getNameProduct() + " "
                + "isNeeded: " + getIsNeeded() + " "
                + "amount: " + getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getIsNeeded() == product.getIsNeeded() &&
                getAmount() == product.getAmount() &&
//                Objects.equals(getId(), product.getId()) &&
                Objects.equals(getNameProduct(), product.getNameProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(/*getId(),*/ getNameProduct(), getIsNeeded(), getAmount());
    }
}
