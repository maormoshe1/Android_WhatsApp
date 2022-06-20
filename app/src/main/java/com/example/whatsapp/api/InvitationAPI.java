package com.example.whatsapp.api;

import android.util.Log;

import com.example.whatsapp.Connection;
import com.example.whatsapp.Contact;
import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;
import com.example.whatsapp.Token;
import com.example.whatsapp.room.ContactDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvitationAPI {
    Retrofit retrofit;
    WebServerAPI webServerAPI;
    AddContactAPI addContactAPI;

    public InvitationAPI(String server){
        String url = "http://"+server+"/api/";
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServerAPI = retrofit.create(WebServerAPI.class);
        addContactAPI = new AddContactAPI();
    }
    public void inviteContact(String token, Connection connection, Contact contact, ContactDao contactDao){
        Call<Void> call = webServerAPI.inviteContact(connection);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                response.body();
                addContactAPI.addContact(token, contact, contactDao);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //TODO something wrong msg
            }
        });
    }
}
