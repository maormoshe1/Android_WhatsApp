package com.example.whatsapp;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Message.class, Contact.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao postDao();
    public abstract MessageDao postMsgDao();
}
