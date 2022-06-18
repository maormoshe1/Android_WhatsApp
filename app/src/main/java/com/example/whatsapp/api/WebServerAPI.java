package com.example.whatsapp.api;

import com.example.whatsapp.Token;
import com.example.whatsapp.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebServerAPI {
    @POST("Users/login")
    Call<Token> createPost(@Body User user);

    @POST("Users/signup")
    Call<Token> signUp(@Body User user);
}
