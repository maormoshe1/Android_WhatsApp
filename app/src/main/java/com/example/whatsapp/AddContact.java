package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddContact extends AppCompatActivity {
    private AppDB db;
    private PostDao postDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class,"PostsDB")
                .allowMainThreadQueries().build();
        postDao = db.postDao();

        Button addContact = findViewById(R.id.addContact);
        addContact.setOnClickListener(v->{
            EditText etItem = findViewById(R.id.editTextDN);
            Post post = new Post(etItem.getText().toString());
            postDao.insert(post);
            finish();
        });
    }
}