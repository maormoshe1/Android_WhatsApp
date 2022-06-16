package com.example.whatsapp;

public class User {
    private String Id;
    private String password;

    public User(String id, String password) {
        this.Id = id;
        this.password = password;
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
