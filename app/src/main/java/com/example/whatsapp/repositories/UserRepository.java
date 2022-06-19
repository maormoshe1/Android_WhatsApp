package com.example.whatsapp.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.User;
import com.example.whatsapp.api.LoginAPI;

public class UserRepository {
    private MutableLiveData<String> token;
    private LoginAPI logApi;

    public UserRepository(MutableLiveData<String> token){
        this.token = token;
        this.logApi = new LoginAPI();
    }

    public void login(User user)
    {
        this.logApi.login(user,this.token);
    }

    public void regiter(User user)
    {
        this.logApi.register(user, this.token);
    }
}
