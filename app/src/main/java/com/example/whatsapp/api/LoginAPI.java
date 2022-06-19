package com.example.whatsapp.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;
import com.example.whatsapp.Token;
import com.example.whatsapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAPI {
    Retrofit retrofit;
    WebServerAPI webServerAPI;

    public LoginAPI(){
        retrofit = new Retrofit.Builder().baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServerAPI =retrofit.create(WebServerAPI.class);
    }
    public void login(User user, MutableLiveData<String> token){
        Call<Token> call = webServerAPI.createPost(user);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if(response.body() != null) {
                    token.setValue("Bearer " + response.body().getToken());
                }
                else
                    token.setValue(null);
            }
            @Override
            public void onFailure(Call<Token> call, Throwable t) {
            }
        });
    }

    public void register(User user, MutableLiveData<String> token){
        Call<Token> call = webServerAPI.signUp(user);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if(response.body() != null)
                    token.setValue("Bearer " + response.body().getToken());
                else
                    token.setValue(null);

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
