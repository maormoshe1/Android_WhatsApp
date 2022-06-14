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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Chat extends AppCompatActivity {
    private AppDB dbContact, dbChat;
    private Contact post;
    private ContactDao postDaoContact;
    private MessageDao postDaoChat;
    private int id;
    private String name;
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

        dbContact = Room.databaseBuilder(getApplicationContext(), AppDB.class,"bDB")
                .allowMainThreadQueries().build();
        postDaoContact = dbContact.postDao();
        dbChat = Room.databaseBuilder(getApplicationContext(), AppDB.class,"adDB")
                .allowMainThreadQueries().build();
        postDaoChat = dbChat.postMsgDao();
        messages = new ArrayList<>();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        TextView tvDN = findViewById(R.id.tvDN);
        ListView lvChat = findViewById(R.id.lvChat);
//        adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, messages);
        adapter = new MessageAdapter(this, messages);
        lvChat.setAdapter(adapter);

        if(getIntent().getExtras() != null){
            id = getIntent().getExtras().getInt("ID");
            name = postDaoContact.get(id).getDisplayName();
            post = postDaoContact.get(id);
            messages.addAll(postDaoChat.getByDN(name));
            tvDN.setText(name);
        }

        FloatingActionButton sendMsg = findViewById(R.id.sendMsg);
        sendMsg.setOnClickListener(view -> {
            EditText etItem = findViewById(R.id.editTextMsg);
            msg = etItem.getText().toString();
            if (!msg.equals("")) {
                etItem.setText("");
                time = sdf.format(new Date());
                Message postMsg = new Message(name, time, msg, true);
                postDaoChat.insert(postMsg);
                post.setLastMsg(msg);
                post.setLastMsgDate(time);
                postDaoContact.update(post);
                messages.clear();
                messages.addAll(postDaoChat.getByDN(name));
                adapter.notifyDataSetChanged();
            }
        });
    }

}