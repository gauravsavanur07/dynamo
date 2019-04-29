package com.example.foodhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    public void onClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void showLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void signUpClicked(View view) {
        EditText emailEditText = findViewById(R.id.emailET);
        EditText usernameEditText = findViewById(R.id.usernameET);
        EditText passwordEditText = findViewById(R.id.passwordET);

        if (emailEditText.getText().toString().matches("") || usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")) {
            Toast.makeText(this, "Empty Fields!", Toast.LENGTH_SHORT).show();
        } else {
            ParseUser user = new ParseUser();
            user.setEmail(emailEditText.getText().toString());
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i("SignUp", "Success!");
                        Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        showLogin();
                    } else {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView loginTextView = findViewById(R.id.loginTV);
        loginTextView.setOnClickListener(this);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
