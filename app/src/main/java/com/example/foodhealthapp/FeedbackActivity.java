package com.example.foodhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class FeedbackActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    //Two variables for edit text field to facilitate details
    private EditText subjectText;
    private EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        drawerLayout = findViewById(R.id.drawer_layout);

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int id = menuItem.getItemId();

                        if (id == R.id.nav_home) {
                            finish();
                        } else if (id == R.id.nav_dashboard) {
                            Intent intent = new Intent(getApplicationContext(), UserAccountActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (id == R.id.nav_about) {
                            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (id == R.id.nav_feedback) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }

                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });

        navigationView.setCheckedItem(R.id.nav_feedback);

        //linking the variable to the edit text id's
        subjectText = findViewById(R.id.subject);
        messageText = findViewById(R.id.message);


        Button sendBtn = findViewById(R.id.send_button);
        //action performed on click event through a listener
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Converts user input to string
                String subject = subjectText.getText().toString();
                String message = messageText.getText().toString();

                //To fill the fields in the email client
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"x16146301@student.ncirl.ie"});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                //Filters only the email clients
                intent.setType("message/rfc822");
                //Shows the options menu
                startActivity(Intent.createChooser(intent, "Choose email client!"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
