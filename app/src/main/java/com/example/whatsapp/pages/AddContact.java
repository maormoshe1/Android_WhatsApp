package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.whatsapp.AppDB;
import com.example.whatsapp.Contact;
import com.example.whatsapp.ContactDao;
import com.example.whatsapp.R;

public class AddContact extends AppCompatActivity {
    private AppDB db;
    private ContactDao postDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class,"bDB")
                .allowMainThreadQueries().build();
        postDao = db.postDao();

        Button addContact = findViewById(R.id.addContact);
        addContact.setOnClickListener(v->{
            EditText etItem = findViewById(R.id.editTextDN);
            Contact post = new Contact(etItem.getText().toString());
            postDao.insert(post);
            finish();
        });
    }
}