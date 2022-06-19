package com.example.whatsapp.api;

import com.example.whatsapp.Message;
import com.example.whatsapp.Token;
import com.example.whatsapp.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WebServerAPI {
    @POST("Users/login")
    Call<Token> createPost(@Body User user);

    @POST("Users/signup")
    Call<Token> signUp(@Body User user);

    @GET("Users/displayname")
    Call<String> displayName(@Header("Authentication") String token);

    @GET("contacts/{id}/messages")
    Call<List<Message>> getMesaages(@Header("Authentication") String token);

    @POST("contacts/{id}/messages")
    Call<Void> addContact(@Header("Authentication") String token);
}
