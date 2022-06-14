package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String displayName;
    private String time;
    private String msg;
    private Boolean sent;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public Message(String displayName, String time, String msg, Boolean sent) {
        this.displayName = displayName;
        this.time = time;
        this.msg = msg;
        this.sent = sent;
    }


    @Override
    public String toString() {
        return msg + "  " + time.substring(11,16);
    }
}
