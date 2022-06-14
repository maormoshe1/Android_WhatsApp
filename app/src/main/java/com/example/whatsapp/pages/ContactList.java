package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.whatsapp.AppDB;
import com.example.whatsapp.Contact;
import com.example.whatsapp.ContactDao;
import com.example.whatsapp.R;
import com.example.whatsapp.adapters.ContactAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {
    private AppDB db;
    private ContactDao postDao;
    private ArrayList<Contact> contacts;
    //private ArrayAdapter<Contact> adapter;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class,"bDB")
                .allowMainThreadQueries().build();
        postDao = db.postDao();

        FloatingActionButton toAddContact = findViewById(R.id.toAddContact);
        toAddContact.setOnClickListener(v->{
            Intent i = new Intent(this, AddContact.class);
            startActivity(i);
        });
        contacts = new ArrayList<>();
        ListView lvContacts = findViewById(R.id.lvContacts);
//        adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_expandable_list_item_1, contacts);
        adapter = new ContactAdapter(this, contacts);
        lvContacts.setAdapter(adapter);

        lvContacts.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, Chat.class);
            intent.putExtra("ID",contacts.get(i).getId());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(postDao.index());
        adapter.notifyDataSetChanged();
    }
}