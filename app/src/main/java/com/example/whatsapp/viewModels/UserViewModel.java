package com.example.whatsapp.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.User;
import com.example.whatsapp.repositories.UserRepository;

public class UserViewModel extends ViewModel {
    private MutableLiveData<String> token;
    private UserRepository repo;

    public UserViewModel() {
        this.token = new MutableLiveData<>();
        this.repo = new UserRepository(this.token);
    }

    public void login(User user)
    {
        this.repo.login(user);
    }

    public void register(User user)
    {
        this.repo.regiter(user);
    }

    public MutableLiveData<String> getToken() {
        return token;
    }


}
