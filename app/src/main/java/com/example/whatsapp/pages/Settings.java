package com.example.whatsapp.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnSetServer = findViewById(R.id.btnSettingsSS);
        btnSetServer.setOnClickListener(v->{
            TextInputLayout til =findViewById(R.id.tilSettingsSS);
            EditText etSetServer = findViewById(R.id.etSettingsSS);
            MyApplication.myServer = etSetServer.getText().toString();
            Log.i("set",MyApplication.myServer);
        });
    }
}