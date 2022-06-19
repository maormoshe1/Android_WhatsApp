package com.example.whatsapp.api;

import com.example.whatsapp.Message;
import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;

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
        retrofit = new Retrofit.Builder().baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServerAPI =retrofit.create(WebServerAPI.class);
    }
    public void getMessages(String token){
        Call<List<Message>> call = webServerAPI.getMesaages(token);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }
}
