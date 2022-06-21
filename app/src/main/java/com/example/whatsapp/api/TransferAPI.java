package com.example.whatsapp.api;

import com.example.whatsapp.Connection;
import com.example.whatsapp.Contact;
import com.example.whatsapp.Message;
import com.example.whatsapp.room.ContactDao;
import com.example.whatsapp.room.MessageDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransferAPI {
    Retrofit retrofit;
    WebServerAPI webServerAPI;
    MsgAPI msgAPI;

    public TransferAPI(String server){
        String url = "http://"+server+"/api/";
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServerAPI = retrofit.create(WebServerAPI.class);
        msgAPI = new MsgAPI();
    }
    public void transferMessage(Connection connection,String token, String id, Message message, MessageDao messageDao){
        Call<Void> call = webServerAPI.transferMessage(connection);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                response.body();
                msgAPI.postMessage(token, id, message, messageDao);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //TODO something wrong msg
            }
        });
    }
}
