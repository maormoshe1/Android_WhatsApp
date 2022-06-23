package com.example.whatsapp.api;

import android.util.Log;

import com.example.whatsapp.Contact;
import com.example.whatsapp.Message;
import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;
import com.example.whatsapp.room.ContactDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddContactAPI {
    Retrofit retrofit;
    WebServerAPI webServerAPI;
    public AddContactAPI(){
        String url = "http://"+MyApplication.myServer+"/api/";
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServerAPI = retrofit.create(WebServerAPI.class);
    }
    public void addContact(String token, Contact contact, ContactDao contactDao){
        Call<Void> call = webServerAPI.addContact(token, contact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                response.body();
                contactDao.insert(contact);
                //TODO added contact msg
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.getCause();
                //TODO something wrong msg
            }
        });
    }
}
