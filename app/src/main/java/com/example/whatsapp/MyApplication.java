package com.example.whatsapp;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context context;
    public static String myServer = "10.0.2.2:5132";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
