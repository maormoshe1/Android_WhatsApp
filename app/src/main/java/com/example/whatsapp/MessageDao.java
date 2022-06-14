package com.example.whatsapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM Message")
    List<Message> index();

    @Query("SELECT * FROM Message WHERE id = :id")
    Message get(String id);

    @Query("SELECT * FROM Message WHERE displayName = :displayName")
    List<Message> getByDN(String displayName);

    @Insert
    void insert(Message... posts);

    @Update
    void update(Message... posts);

    @Delete
    void delete(Message... posts);
}

