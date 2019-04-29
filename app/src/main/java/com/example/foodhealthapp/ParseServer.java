package com.example.foodhealthapp;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;

public class ParseServer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("YOUR_APP_ID")
                // if defined
                .clientKey("YOUR_CLIENT_KEY")
                .server("http://localhost:1337/parse/")
                .build()
        );

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
