package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.whatsapp.R;
import com.example.whatsapp.User;
import com.example.whatsapp.api.LoginAPI;
import com.example.whatsapp.viewModels.UserViewModel;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserViewModel uvm = new ViewModelProvider(this).get(UserViewModel.class);
        uvm.getToken().observe(this,token->{
            if(token!=null)
            {
                Intent i = new Intent(this, ContactList.class);
                i.putExtra("token", token);
                startActivity(i);
            }
            else
                Log.i("R", "no user");
            //TODO: notify there is no user

        });

        Button loginToSignup = findViewById(R.id.loginToSignup);
        loginToSignup.setOnClickListener(v->{
            Intent i = new Intent(this, Register.class);
            startActivity(i);
        });

        Button login = findViewById(R.id.login);
        login.setOnClickListener(v->{
            //EditText etLoginUsername = findViewById(R.id.etLoginUN);
            //EditText etLoginPassword = findViewById(R.id.etLoginPassword);
            String etLoginUsername = "Nikol";
            String etLoginPassword = "Nn1";
            //User user = new User(etLoginUsername.getText().toString(), etLoginPassword.getText().toString());
            User user = new User(etLoginUsername, etLoginPassword);

            uvm.login(user);
        });
    }
}