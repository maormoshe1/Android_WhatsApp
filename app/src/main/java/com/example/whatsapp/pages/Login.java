package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.whatsapp.R;
import com.example.whatsapp.User;
import com.example.whatsapp.api.LoginAPI;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginAPI loginAPI = new LoginAPI();
        Button loginToSignup = findViewById(R.id.loginToSignup);
        loginToSignup.setOnClickListener(v->{
            Intent i = new Intent(this, Register.class);
            startActivity(i);
        });

        Button login = findViewById(R.id.login);
        login.setOnClickListener(v->{
            EditText etLoginUsername = findViewById(R.id.etLoginUN);
            EditText etLoginPassword = findViewById(R.id.etLoginPassword);
            User user = new User(etLoginUsername.getText().toString(), etLoginPassword.getText().toString());
            loginAPI.post(user);
            Intent i = new Intent(this, ContactList.class);
            startActivity(i);
        });
    }
}