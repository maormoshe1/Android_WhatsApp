package com.example.whatsapp.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.Connection;
import com.example.whatsapp.Contact;
import com.example.whatsapp.Message;
import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;
import com.example.whatsapp.room.MessageDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MsgAPI {
    Retrofit retrofit;
    WebServerAPI webServerAPI;
    public MsgAPI(){
        String url = "http://"+MyApplication.myServer+"/api/";
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServerAPI =retrofit.create(WebServerAPI.class);
    }
    public void getMessages(String token, String id, MessageDao messageDao){
        Call<List<Message>> call = webServerAPI.getMesaages(token, id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                response.body();
                messageDao.deleteAll();
                for (Message m: response.body()) {
                    Message message = new Message(id, m.getCreated(), m.getContent(), m.getSent());
                    messageDao.insert(message);
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }

    public void postMessage(String token, String id, Message message, MessageDao messageDao){
        Call<Void> call = webServerAPI.postMesaage(token, id, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Message m = new Message(id, message.getCreated(), message.getContent(), true);
                messageDao.insert(m);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
