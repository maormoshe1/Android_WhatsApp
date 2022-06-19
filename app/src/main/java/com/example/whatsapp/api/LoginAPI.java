package com.example.whatsapp.api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;
import com.example.whatsapp.Token;
import com.example.whatsapp.User;
import com.example.whatsapp.pages.ContactList;
import com.example.whatsapp.pages.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

public class LoginAPI {
    Retrofit retrofit;
    WebServerAPI webServerAPI;

    public LoginAPI(){
        retrofit = new Retrofit.Builder().baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServerAPI =retrofit.create(WebServerAPI.class);
    }
    public void login(User user){
        Call<Token> call = webServerAPI.createPost(user);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if(response.body() != null) {
                    String t = "Bearer " + response.body().getToken();
                }
            }
            @Override
            public void onFailure(Call<Token> call, Throwable t) {
            }
        });
    }

    public void register(User user){
        Call<Token> call = webServerAPI.signUp(user);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if(response.body() != null) {
                    String t = "Bearer " + response.body().getToken();
                }
            }
            @Override
            public void onFailure(Call<Token> call, Throwable t) {
            }
        });
    }

    public void getDisplayName(String token)
    {
        Call<String> call = webServerAPI.displayName(token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.body() != null) {
                    String t = response.body();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
