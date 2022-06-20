package com.example.whatsapp.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.whatsapp.Contact;
import com.example.whatsapp.Message;

@Database(entities = {Message.class, Contact.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao postDao();
    public abstract MessageDao postMsgDao();
}
