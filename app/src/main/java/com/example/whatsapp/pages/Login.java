package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;
import com.example.whatsapp.User;
import com.example.whatsapp.api.LoginAPI;
import com.example.whatsapp.viewModels.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    private EditText etLoginUN;
    private TextInputLayout tilLoginUN;
    private EditText etLoginPassword;
    private TextInputLayout tilLoginPassword;
    private UserViewModel uvm;

    private boolean validation(){
        etLoginUN = findViewById(R.id.etLoginUN);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        tilLoginUN = findViewById(R.id.tilLoginUN);
        tilLoginPassword = findViewById(R.id.tilLoginPassword);
        String UN = etLoginUN.getText().toString();
        String password = etLoginPassword.getText().toString();
        boolean valid_input = true;
        if(UN.equals("")){
            tilLoginUN.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilLoginUN.setError(null);
        }
        if(password.equals("")){
            tilLoginPassword.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilLoginPassword.setError(null);
        }
        return valid_input;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginToSignup = findViewById(R.id.loginToSignup);
        loginToSignup.setOnClickListener(v->{
            Intent i = new Intent(this, Register.class);
            startActivity(i);
        });

        Button login = findViewById(R.id.login);
        login.setOnClickListener(v->{
            uvm = new UserViewModel();
            uvm.getToken().observe(this,token->{
                if(token!=null)
                {
                    Intent i = new Intent(this, ContactList.class);
                    i.putExtra("token", token);
                    i.putExtra("username", etLoginUN.getText().toString());
                    startActivity(i);
                }
                else {
                    tilLoginUN.setError(" ");
                    tilLoginPassword.setError("Incorrect username or password.");
                }
            });
            if(validation()) {
                User user = new User(etLoginUN.getText().toString(), etLoginPassword.getText().toString());
                uvm.login(user);
            }
        });

        FloatingActionButton toSettings = findViewById(R.id.toSettings);
        toSettings.setOnClickListener(v->{
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
        });
    }
}