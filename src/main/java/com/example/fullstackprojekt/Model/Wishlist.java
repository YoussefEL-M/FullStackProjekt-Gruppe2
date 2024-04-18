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

    public Wishlist(int id, int userId, String name){
        this.id = id;
        this.userId=userId;
        this.name=name;
    }

    public Wishlist(String name){
        this.name = name;
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
}