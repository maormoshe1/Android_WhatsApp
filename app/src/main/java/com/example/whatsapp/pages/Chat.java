package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.whatsapp.Connection;
import com.example.whatsapp.api.TransferAPI;
import com.example.whatsapp.room.AppDB;
import com.example.whatsapp.Contact;
import com.example.whatsapp.room.ContactDao;
import com.example.whatsapp.Message;
import com.example.whatsapp.room.MessageDao;
import com.example.whatsapp.R;
import com.example.whatsapp.adapters.MessageAdapter;
import com.example.whatsapp.api.MsgAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Chat extends AppCompatActivity {
    private AppDB dbContact, dbChat;
    private Contact contact;
    private ContactDao contactDao;
    private MessageDao messageDao;
    private MsgAPI msgAPI;
    private TransferAPI transferAPI;
    private String token;
    private String UN;
    private String conUN;
    private String DN;
    private String time;
    private String msg;
    private SimpleDateFormat sdf;
    private ArrayList<Message> messages;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        dbContact = Room.databaseBuilder(getApplicationContext(), AppDB.class,"edDB")
                .allowMainThreadQueries().build();
        contactDao = dbContact.postDao();
        dbChat = Room.databaseBuilder(getApplicationContext(), AppDB.class,"edDB")
                .allowMainThreadQueries().build();
        messageDao = dbChat.postMsgDao();
        messages = new ArrayList<>();
        msgAPI = new MsgAPI();
        token = getIntent().getStringExtra("token");
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        TextView tvDN = findViewById(R.id.tvDN);
        ListView lvChat = findViewById(R.id.lvChat);

        conUN = getIntent().getStringExtra("conUN");
        UN = getIntent().getStringExtra("username");
        contact = contactDao.get(conUN);
        transferAPI = new TransferAPI(contact.getServer());
        DN = contact.getNickName();
        tvDN.setText(DN);
        messages.clear();
        msgAPI.getMessages(token, conUN, messageDao);
        messages.addAll(messageDao.getByUN(conUN));

        adapter = new MessageAdapter(this, messages);
        lvChat.setAdapter(adapter);

        FloatingActionButton sendMsg = findViewById(R.id.sendMsg);
        sendMsg.setOnClickListener(view -> {
            EditText etSend = findViewById(R.id.etSend);
            msg = etSend.getText().toString();
            if (!msg.equals("")) {
                etSend.setText("");
                time = sdf.format(new Date());
                Message postMsg = new Message(null, time, msg, null);
                Connection connection = new Connection(UN, conUN, null, msg);
                transferAPI.transferMessage(connection, token, conUN, postMsg, messageDao);
                msgAPI.getMessages(token, conUN, messageDao);
                messages.clear();
                messages.addAll(messageDao.getByUN(conUN));
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        msgAPI.getMessages(token, conUN, messageDao);
        messages.clear();
        messages.addAll(messageDao.index());
        adapter.notifyDataSetChanged();
    }
}