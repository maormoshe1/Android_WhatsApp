package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.whatsapp.AppDB;
import com.example.whatsapp.Contact;
import com.example.whatsapp.ContactDao;
import com.example.whatsapp.Message;
import com.example.whatsapp.MessageDao;
import com.example.whatsapp.R;
import com.example.whatsapp.adapters.MessageAdapter;
import com.example.whatsapp.api.MsgAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Chat extends AppCompatActivity {
    private AppDB dbContact, dbChat;
    private Contact contact;
    private ContactDao contactDao;
    private MessageDao messageDao;
    private String UN;
    private String DN;
    private String time;
    private String msg;
    private SimpleDateFormat sdf;
    private ArrayList<Message> messages;
    //private ArrayAdapter<Message> adapter;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        dbContact = Room.databaseBuilder(getApplicationContext(), AppDB.class,"fDB")
                .allowMainThreadQueries().build();
        contactDao = dbContact.postDao();
        dbChat = Room.databaseBuilder(getApplicationContext(), AppDB.class,"aiDB")
                .allowMainThreadQueries().build();
        messageDao = dbChat.postMsgDao();
        messages = new ArrayList<>();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        TextView tvDN = findViewById(R.id.tvDN);
        ListView lvChat = findViewById(R.id.lvChat);
//        adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, messages);
        adapter = new MessageAdapter(this, messages);
        lvChat.setAdapter(adapter);
        MsgAPI msgAPI = new MsgAPI();
        msgAPI.getMessages(getIntent().getExtras().getString("token"));

        if(getIntent().getExtras() != null){
            UN = getIntent().getExtras().getString("UN");
            contact = contactDao.get(UN);
            DN = contact.getDisplayName();
            messages.addAll(messageDao.getByUN(UN));
            tvDN.setText(DN);
        }

        FloatingActionButton sendMsg = findViewById(R.id.sendMsg);
        sendMsg.setOnClickListener(view -> {
            EditText etSend = findViewById(R.id.etSend);
            msg = etSend.getText().toString();
            if (!msg.equals("")) {
                etSend.setText("");
                time = sdf.format(new Date());
                Message postMsg = new Message(UN, time, msg, true);
                messageDao.insert(postMsg);
                contact.setLastMsg(msg);
                contact.setLastMsgDate(time);
                contactDao.update(contact);
                messages.clear();
                messages.addAll(messageDao.getByUN(UN));
                adapter.notifyDataSetChanged();
            }
        });
    }

}