package com.example.whatsapp.api;

import android.util.Log;

import com.example.whatsapp.Token;
import com.example.whatsapp.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAPI {
    Retrofit retrofit;
    WebServerAPI webServerAPI;

    public LoginAPI(){
        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5132/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServerAPI =retrofit.create(WebServerAPI.class);
    }
    public void post(User user){
        Log.i("response","enter post");
        Call<Token> call = webServerAPI.createPost(user);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Log.i("response",response.body().getToken());
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.i("response", "fail");
            }
        });
    }
}
