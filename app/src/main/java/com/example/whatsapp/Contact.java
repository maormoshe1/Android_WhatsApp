package com.example.whatsapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String displayName;
    private int pic;
    private String lastMsg;
    private String lastMsgDate;

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

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getLastMsgDate() {
        return lastMsgDate;
    }

    public void setLastMsgDate(String lastMsgDate) {
        this.lastMsgDate = lastMsgDate;
    }

    public Contact(String displayName) {
        this.displayName = displayName;
        pic = 0;
        lastMsg = "";
        lastMsgDate = "";
    }

    @Override
    public String toString() {
        if(lastMsgDate.equals("")){
            return displayName;
        }
        return displayName + " " + lastMsg + " " + lastMsgDate.substring(11,16);

    }
}
