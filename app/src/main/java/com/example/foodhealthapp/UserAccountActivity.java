package com.example.foodhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class UserAccountActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    public void logOutClicked(View view) {
        ParseUser.getCurrentUser().logOut();
        Toast.makeText(this, "Sign out Success!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        ParseUser currentUser = ParseUser.getCurrentUser();

        TextView displayUser = findViewById(R.id.usernameTV);
        String userNameString = (String) currentUser.get("username");
        displayUser.setText(userNameString);

        TextView displayEmail = findViewById(R.id.emailTV);
        String emailString = (String) currentUser.get("email");
        displayEmail.setText(emailString);

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
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else if (id == R.id.nav_about) {
                            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (id == R.id.nav_feedback) {
                            Intent intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });
        navigationView.setCheckedItem(R.id.nav_dashboard);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
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
