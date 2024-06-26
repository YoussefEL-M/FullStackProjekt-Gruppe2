package com.example.fullstackprojekt.Model;

//import jakarta.persistence.Id;
//import jakarta.persistence.Entity;

//@Entity
public class User {


    //@Id
    private int id;
    private String name;
    private String username;
    private String password;
    private boolean owner;


    public User() {
    }

    public User(int id, String name, String username, String password, boolean owner) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.owner = owner;
    }

    public User(String name, String username, String password) {
        this.name=name;
        this.username = username;
        this.password = password;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }



}
