package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.whatsapp.api.AddContactAPI;
import com.example.whatsapp.room.AppDB;
import com.example.whatsapp.Connection;
import com.example.whatsapp.Contact;
import com.example.whatsapp.room.ContactDao;
import com.example.whatsapp.R;
import com.example.whatsapp.api.InvitationAPI;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddContact extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    //private AddContactAPI addContactAPI;
    private InvitationAPI invitationAPI;
    private String token;
    private String UN;
    private String conUN;
    private String conDN;
    private String conServer;
//    private EditText etAddConUN;
//    private EditText etAddConDN;
//    private EditText etAddConServer;
//    private TextInputLayout tilAddConUN;
//    private TextInputLayout tilAddConDN;
//    private TextInputLayout tilAddConServer;

    private boolean validation(){
        EditText etAddConUN = findViewById(R.id.etAddConUN);
        EditText etAddConDN = findViewById(R.id.etAddConDN);
        EditText etAddConServer = findViewById(R.id.etAddConServer);
        TextInputLayout tilAddConUN = findViewById(R.id.tilAddConUN);
        TextInputLayout tilAddConDN = findViewById(R.id.tilAddConDN);
        TextInputLayout tilAddConServer = findViewById(R.id.tilAddConServer);
        conUN = etAddConUN.getText().toString();
        conDN = etAddConDN.getText().toString();
        conServer = etAddConServer.getText().toString();
        boolean valid_input = true;
        if(conUN.equals("")){
            tilAddConUN.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilAddConUN.setError(null);
        }
        if(conDN.equals("")){
            tilAddConDN.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilAddConDN.setError(null);
        }
        if(conServer.equals("")){
            tilAddConServer.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilAddConServer.setError(null);
        }
        if (valid_input){
            List<Contact> contacts = contactDao.index();
            for (Contact contact : contacts){
                if(contact.getIdName().equals(conUN)){
                    tilAddConServer.setError("This user is already in your chats");
                    valid_input = false;
                    break;
                }
            }
        }
        return valid_input;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class,"edDB")
                .allowMainThreadQueries().build();
        contactDao = db.postDao();
        //addContactAPI = new AddContactAPI();
        token = getIntent().getExtras().getString("token");
        UN = getIntent().getExtras().getString("username");
        Button addContact = findViewById(R.id.addContact);

        addContact.setOnClickListener(v->{
            if (validation()) {
                Contact contact = new Contact(conUN, conDN, conServer, null, null);
                Connection connection = new Connection(UN, conUN, conServer, null);
                //addContactAPI.addContact(token, contact, contactDao);
                invitationAPI = new InvitationAPI(conServer);
                invitationAPI.inviteContact(connection, token, contact, contactDao);
                //contactDao.insert(contact);
                //TO DO msg contact added
            }
        });
    }
}