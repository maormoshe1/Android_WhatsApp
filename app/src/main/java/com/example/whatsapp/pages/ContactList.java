package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.whatsapp.api.ContactListAPI;
import com.example.whatsapp.api.MsgAPI;
import com.example.whatsapp.room.AppDB;
import com.example.whatsapp.Contact;
import com.example.whatsapp.room.ContactDao;
import com.example.whatsapp.R;
import com.example.whatsapp.adapters.ContactAdapter;
import com.example.whatsapp.room.MessageDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {
    private AppDB dbChat;
    private AppDB dbContact;
    private ContactDao contactDao;
    private MessageDao messageDao;
    private ContactListAPI contactListAPI;
    private MsgAPI msgAPI;
    private String token;
    private String UN;
    private ArrayList<Contact> contacts;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        UN = getIntent().getStringExtra("username");
        dbContact = Room.databaseBuilder(getApplicationContext(), AppDB.class,UN)
                .allowMainThreadQueries().build();
        contactDao = dbContact.postDao();
        dbChat = Room.databaseBuilder(getApplicationContext(), AppDB.class,UN)
                .allowMainThreadQueries().build();
        messageDao = dbChat.postMsgDao();
        token = getIntent().getStringExtra("token");
        contactListAPI = new ContactListAPI();
        msgAPI = new MsgAPI();
        contacts = new ArrayList<>();
        adapter = new ContactAdapter(this, contacts);

        contacts.addAll(contactDao.index());
        adapter.notifyDataSetChanged();


        FloatingActionButton toAddContact = findViewById(R.id.toAddContact);
        toAddContact.setOnClickListener(v->{
            Intent i = new Intent(this, AddContact.class);
            i.putExtra("token", token);
            i.putExtra("username", UN);
            startActivity(i);
        });

        ListView lvContacts = findViewById(R.id.lvContacts);
        lvContacts.setAdapter(adapter);
        lvContacts.setOnItemClickListener((adapterView, view, i, l) -> {
            String conUN = contacts.get(i).getIdName();
            msgAPI.getMessages(token, conUN, messageDao);
            Intent intent = new Intent(this, Chat.class);
            intent.putExtra("conUN", conUN );
            intent.putExtra("username", UN);
            intent.putExtra("token", token);
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