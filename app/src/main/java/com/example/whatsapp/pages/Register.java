package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;
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
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText etRegisterUN;
    EditText etRegisterPassword;
    EditText etRegisterPassword2;
    EditText etRegisterDN;
    TextInputLayout tilRegisterUN;
    TextInputLayout tilRegisterPassword;
    TextInputLayout tilRegisterPassword2;
    TextInputLayout tilRegisterDN;


    private boolean validation(){
        etRegisterUN = findViewById(R.id.etRegisterUN);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterPassword2 = findViewById(R.id.etRegisterPassword2);
        etRegisterDN = findViewById(R.id.etRegisterDN);
        tilRegisterUN = findViewById(R.id.tilRegisterUN);
        tilRegisterPassword = findViewById(R.id.tilRegisterPassword);
        tilRegisterPassword2 = findViewById(R.id.tilRegisterPassword2);
        tilRegisterDN = findViewById(R.id.tilRegisterDN);
        String UN = etRegisterUN.getText().toString();
        String password = etRegisterPassword.getText().toString();
        String password2 = etRegisterPassword2.getText().toString();
        String DN = etRegisterDN.getText().toString();
        String pattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{3,}";
        boolean valid_input = true;
        if(UN.equals("")){
            tilRegisterUN.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilRegisterUN.setError(null);
        }
        if(password.equals("")){
            tilRegisterPassword.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilRegisterPassword.setError(null);
        }
        if(password2.equals("")){
            tilRegisterPassword2.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilRegisterPassword2.setError(null);
        }
        if(DN.equals("")){
            tilRegisterDN.setError("Please fill out this field");
            valid_input = false;
        }
        else{
            tilRegisterDN.setError(null);
        }
        if(valid_input){
            if(!password.equals(password2)){
                tilRegisterPassword.setError(" ");
                tilRegisterPassword2.setError("The passwords do not match:(");
                valid_input = false;
            }
        }
        if(valid_input){
            if(!Pattern.matches(pattern,password)){
                tilRegisterPassword.setError("Password need to contain at least one numeric digit, one uppercase and one lowercase letter");
                valid_input = false;
            }
        }
        return valid_input;
    }

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
            {
                tilRegisterUN.setError("This username is taken, try another one");
            }

        });

        Button signup = findViewById(R.id.signup);
        signup.setOnClickListener(v->{
            if(validation()) {
                User user = new User(etRegisterUN.getText().toString(), etRegisterPassword.getText().toString(), etRegisterDN.getText().toString());
                uvm.register(user);
            }
        });

        FloatingActionButton toSettings = findViewById(R.id.toSettings);
        toSettings.setOnClickListener(v->{
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
        });
    }
}