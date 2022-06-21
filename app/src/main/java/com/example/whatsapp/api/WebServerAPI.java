package com.example.whatsapp.api;

import com.example.whatsapp.Connection;
import com.example.whatsapp.Contact;
import com.example.whatsapp.Message;
import com.example.whatsapp.Token;
import com.example.whatsapp.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServerAPI {
    @POST("Users/login")
    Call<Token> createPost(@Body User user);

    @POST("Users/signup")
    Call<Token> signUp(@Body User user);

    @GET("Users/displayname")
    Call<String> displayName(@Header("Authorization") String token);

    @GET("contacts/{id}/messages")
    Call<List<Message>> getMesaages(@Header("Authorization") String token, @Path("id") String id);

    @POST("contacts/{id}/messages")
    Call<Void> postMesaage(@Header("Authorization") String token, @Path("id") String id, @Body Message message);

    @GET("contacts")
    Call<List<Contact>> getContacts(@Header("Authorization") String token);

    @POST("contacts")
    Call<Void> addContact(@Header("Authorization") String token,@Body Contact contact);

    @POST("invitations")
    Call<Void> inviteContact(@Body Connection connection);

    @POST("transfer")
    Call<Void> transferMessage(@Body Connection connection);

}
