package com.example.fullstackprojekt.Model;

import jakarta.persistence.*;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Wish {

    @Id
    private int id;
    private String name;
    private double price;
    private int amount;
    private String description;
    private String url;
    private boolean reserved;

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    public Wish(){

    }

    public Wish(int id, String name, double price, int amount, String description) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
    }

    public Wish(String name, double price, int amount, String description, String url) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.url=url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

}
