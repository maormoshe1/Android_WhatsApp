package com.example.whatsapp.api;

import android.util.Log;

import com.example.whatsapp.Connection;
import com.example.whatsapp.Contact;
import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;
import com.example.whatsapp.Token;
import com.example.whatsapp.room.ContactDao;
import com.google.android.material.textfield.TextInputLayout;

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
    public void inviteContact(Connection connection, String token, Contact contact, ContactDao contactDao,
                              TextInputLayout tilAddConServer){
        Call<Void> call = webServerAPI.inviteContact(connection);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                response.body();
                if (response.code() != 400) {
                    addContactAPI.addContact(token, contact, contactDao, tilAddConServer);
                }
                else{
                    tilAddConServer.setError("This username does not exist on the server");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                tilAddConServer.setError("This username does not exist on the server");
            }
        });
    }
}
