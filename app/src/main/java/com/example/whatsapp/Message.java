package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    private String created;
    private String content;
    private Boolean sent;

    public Message(String userName, String created, String content, Boolean sent) {
        this.userName = userName;
        this.created = created;
        this.content = content;
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String time) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String msg) {
        this.content = content;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

}
