package com.example.fullstackprojekt.Model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
public class Wishlist {

    @Id
    private int id;
    private int userId;
    private String name;
    private boolean isPrivate;

    public Wishlist(int id, int userId, String name, boolean isPrivate){
        this.id = id;
        this.userId=userId;
        this.name=name;
        this.isPrivate=isPrivate;
    }

    public Wishlist(String name, boolean isPrivate){
        this.name = name;
        this.isPrivate=isPrivate;
    }

    public Wishlist() {

    }

    public int getUserId(){
        return userId;
    }
    public String getName(){
        return name;
    }

    public void setUserId(int userId){
        this.userId=userId;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public boolean isPrivate(){
        return isPrivate;
    }
    public void setPrivate(boolean isPrivate){
        this.isPrivate = isPrivate;
    }
}