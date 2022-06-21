package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Contact {
//    @PrimaryKey (autoGenerate = true)
//    private int id;
    @PrimaryKey @NonNull
    private String idName;
    private String nickName;
    private int pic;
    private String server;
    private String last;
    private String lastdate;

    public Contact(@NonNull String idName, String nickName, String server, String last, String lastdate) {
        this.idName = idName;
        this.nickName = nickName;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

//    public String getIdName() {
//        return idName;
//    }
//
//    public void setIdName(String idName) {
//        this.idName = idName;
//    }


    @NonNull
    public String getIdName() {
        return idName;
    }

    public void setIdName(@NonNull String idName) {
        this.idName = idName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }
}
