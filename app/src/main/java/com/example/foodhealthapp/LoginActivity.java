package com.example.foodhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public void showMapActivity() {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void logInClicked(View view) {
        EditText emailEditText = findViewById(R.id.emailET);
        EditText passwordEditText = findViewById(R.id.passwordET);

        if (emailEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")) {
            Toast.makeText(this, "Empty Fields!", Toast.LENGTH_SHORT).show();
        } else {
            ParseUser.logInInBackground(emailEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                        showMapActivity();
                    } else {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView loginTextView = findViewById(R.id.signupTV);
        loginTextView.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            showMapActivity();
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
