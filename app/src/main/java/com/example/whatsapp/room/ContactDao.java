package com.example.whatsapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatsapp.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> index();

    @Query("SELECT * FROM Contact WHERE idName = :idName")
    Contact get(String idName);

    @Query("DELETE FROM Contact")
    void deleteAll();

    @Insert
    void insert(Contact... contacts);

    @Update
    void update(Contact... contacts);

    @Delete
    void delete(Contact... contacts);
}
