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
import com.google.android.material.textfield.TextInputLayout;

public class AddContact extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    private EditText etAddConUN;
    private EditText etAddConDN;
    private EditText etAddConServer;
    private TextInputLayout tilAddConUN;
    private TextInputLayout tilAddConDN;
    private TextInputLayout tilAddConServer;

    private boolean validation(){
        etAddConUN = findViewById(R.id.etAddConUN);
        etAddConDN = findViewById(R.id.etAddConDN);
        etAddConServer = findViewById(R.id.etAddConServer);
        tilAddConUN = findViewById(R.id.tilAddConUN);
        tilAddConDN = findViewById(R.id.tilAddConDN);
        tilAddConServer = findViewById(R.id.tilAddConServer);
        String UN = etAddConUN.getText().toString();
        String DN = etAddConDN.getText().toString();
        String server = etAddConServer.getText().toString();
        boolean valid_input = true;
        if(UN.equals("")){
            tilAddConUN.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilAddConUN.setError(null);
        }
        if(DN.equals("")){
            tilAddConDN.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilAddConDN.setError(null);
        }
        if(server.equals("")){
            tilAddConServer.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilAddConServer.setError(null);
        }
        return valid_input;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class,"fDB")
                .allowMainThreadQueries().build();
        contactDao = db.postDao();

        Button addContact = findViewById(R.id.addContact);
        addContact.setOnClickListener(v->{
            if (validation()) {
                Contact contact = new Contact(etAddConUN.getText().toString(), etAddConDN.getText().toString(),
                        etAddConServer.getText().toString());
                contactDao.insert(contact);
                //TO DO msg contact added
            }
        });
    }
}