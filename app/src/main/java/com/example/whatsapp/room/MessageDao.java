package com.example.whatsapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatsapp.Message;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM Message")
    List<Message> index();

    @Query("SELECT * FROM Message WHERE id = :id")
    Message get(String id);

    @Query("SELECT * FROM Message WHERE userName = :UN")
    List<Message> getByUN(String UN);

    @Query("DELETE FROM Message")
    void deleteAll();

    @Insert
    void insert(Message... posts);

    @Update
    void update(Message... posts);

    @Delete
    void delete(Message... posts);
}

