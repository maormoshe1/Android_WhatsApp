package com.example.whatsapp;

public class User {
    private String Id;
    private String password;
    private String displayName;

    public User(String id, String password) {
        this.Id = id;
        this.password = password;
        this.displayName = "";
    }

    public User(String id, String password, String displayName) {
        this.Id = id;
        this.password = password;
        this.displayName = displayName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
