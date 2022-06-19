package com.example.whatsapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> index();

    @Query("SELECT * FROM Contact WHERE id = :id")
    Contact get(String id);

    @Insert
    void insert(Contact... posts);

    @Update
    void update(Contact... posts);

    @Delete
    void delete(Contact... posts);
}
