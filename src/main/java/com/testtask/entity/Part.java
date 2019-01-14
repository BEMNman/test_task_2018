package com.testtask.entity;

import javax.persistence.*;

@Entity
@Table(name = "part")
public class Part {

    @Id
    @Column(name = "idPart")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idPart")
    private Integer id;

    @Column(name = "part")
    private String namePart;

    @Column(name = "isNeeded")
    private boolean isNeeded;

    @Column(name = "amount")
    private int amount;

    public Part() {
    }

    public Part(Integer id, String namePart, boolean isNeeded, int amount) {
        this.id = id;
        this.namePart = namePart;
        this.isNeeded = isNeeded;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePart() {
        return namePart;
    }

    public void setNamePart(String namePart) {
        this.namePart = namePart;
    }

    public boolean getIsNeeded() {
        return isNeeded;
    }

    public void setIsNeeded(boolean isNeeded) {
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
                + "product: " + getNamePart() + " "
                + "isNeeded: " + getIsNeeded() + " "
                + "amount: " + getAmount();
    }
}
