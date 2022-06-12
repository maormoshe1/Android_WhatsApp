package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {
    private AppDB db;
    private PostDao postDao;
    private List<Post> posts;
    private ArrayAdapter<Post> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class,"PostsDB")
                .allowMainThreadQueries().build();
        postDao = db.postDao();

        FloatingActionButton toAddContact = findViewById(R.id.toAddContact);
        toAddContact.setOnClickListener(v->{
            Intent i = new Intent(this, AddContact.class);
            startActivity(i);
        });
        posts = new ArrayList<>();
        ListView lvPosts = findViewById(R.id.lvPosts);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, posts);
        lvPosts.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        posts.clear();
        posts.addAll(postDao.index());
        adapter.notifyDataSetChanged();
    }
}