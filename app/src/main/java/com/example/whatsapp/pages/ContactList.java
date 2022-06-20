package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.whatsapp.api.ContactListAPI;
import com.example.whatsapp.room.AppDB;
import com.example.whatsapp.Contact;
import com.example.whatsapp.room.ContactDao;
import com.example.whatsapp.R;
import com.example.whatsapp.adapters.ContactAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    private ContactListAPI contactListAPI;
    private String token;
    private String UN;
    private ArrayList<Contact> contacts;
    //private ArrayAdapter<Contact> adapter;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class,"rDB")
                .allowMainThreadQueries().build();
        contactDao = db.postDao();
        token = getIntent().getStringExtra("token");
        UN = getIntent().getStringExtra("username");
        contactListAPI = new ContactListAPI();
        contacts = new ArrayList<>();
        contacts.addAll(contactDao.index());
        ListView lvContacts = findViewById(R.id.lvContacts);
        adapter = new ContactAdapter(this, contacts);
        lvContacts.setAdapter(adapter);


        FloatingActionButton toAddContact = findViewById(R.id.toAddContact);
        toAddContact.setOnClickListener(v->{
            Intent i = new Intent(this, AddContact.class);
            i.putExtra("token", token);
            i.putExtra("username", UN);
            startActivity(i);
        });

        lvContacts.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, Chat.class);
            intent.putExtra("UN", contacts.get(i).getIdName());
            intent.putExtra("token", getIntent().getExtras().getString("token"));
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactListAPI.getContacts(token, contactDao);
        contacts.clear();
        contacts.addAll(contactDao.index());
        adapter.notifyDataSetChanged();
    }
}