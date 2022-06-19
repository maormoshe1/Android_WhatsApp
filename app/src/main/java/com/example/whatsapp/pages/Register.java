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
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button signupToLogin = findViewById(R.id.signupToLogin);
        signupToLogin.setOnClickListener(v->{
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        });

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

        Button signup = findViewById(R.id.signup);
        signup.setOnClickListener(v->{
            EditText etRegisterUN = findViewById(R.id.etRegisterUN);
            EditText etRegisterPassword = findViewById(R.id.etRegisterPassword);
            EditText etRegisterPassword2 = findViewById(R.id.etRegisterPassword2);
            EditText etRegisterDN = findViewById(R.id.etRegisterDN);
            TextInputLayout tilRegisterUN = findViewById(R.id.tilRegisterUN);
            TextInputLayout tilRegisterPassword = findViewById(R.id.tilRegisterPassword);
            TextInputLayout tilRegisterPassword2 = findViewById(R.id.tilRegisterPassword2);
            TextInputLayout tilRegisterDN = findViewById(R.id.tilRegisterDN);

            if(etRegisterDN.getText().toString().equals("")){
                tilRegisterDN.setError("Please fill out this field");
            }
            if(etRegisterUN.getText().toString().equals("")){
                tilRegisterUN.setError("Please fill out this field");
            }
            if(etRegisterPassword.getText().toString().equals("")){
                tilRegisterPassword.setError("Please fill out this field");
            }
            if(etRegisterPassword2.getText().toString().equals("")){
                tilRegisterPassword2.setError("Please fill out this field");
            }

            User user = new User(etRegisterUN.getText().toString(), etRegisterPassword.getText().toString(), etRegisterDN.getText().toString());
            uvm.register(user);
        });
    }
}