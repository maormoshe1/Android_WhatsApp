package com.example.whatsapp.api;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.Contact;
import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;
import com.example.whatsapp.Token;
import com.example.whatsapp.User;
import com.example.whatsapp.room.ContactDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactListAPI {
    Retrofit retrofit;
    WebServerAPI webServerAPI;

    public ContactListAPI() {
        String url = "http://"+MyApplication.myServer+"/api/";
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServerAPI = retrofit.create(WebServerAPI.class);
    }

    public void getContacts(String token, ContactDao contactDao) {
        Call<List<Contact>> call = webServerAPI.getContacts(token);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                response.body();
                contactDao.deleteAll();
                for (Contact c: response.body()) {
                    Contact contact = new Contact(c.getIdName(),c.getNickName(),c.getServer(),c.getLast(),c.getLastdate());
                    contactDao.insert(contact);
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
            }
        });
    }
}
