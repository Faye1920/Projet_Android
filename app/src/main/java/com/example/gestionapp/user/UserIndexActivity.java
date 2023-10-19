package com.example.gestionapp.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.example.gestionapp.R;
import com.example.gestionapp.user.login.UserLoginActivity;
import com.example.gestionapp.user.type.CompteActivity;
import com.example.gestionapp.user.type.EnvoyerActivity;
import com.example.gestionapp.user.type.MessageActivity;
import com.google.android.material.navigation.NavigationView;

public class UserIndexActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_index);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.nav_accueil) {
            Intent intent = new Intent(UserIndexActivity.this, UserIndexActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_message) {
            Intent intent = new Intent(UserIndexActivity.this, MessageActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_envoyer) {
            Intent intent = new Intent(UserIndexActivity.this, EnvoyerActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_compte) {
            Intent intent = new Intent(UserIndexActivity.this, CompteActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_quitter) {
            Intent intent = new Intent(UserIndexActivity.this, UserLoginActivity.class);
            startActivity(intent);
        }

        return true;
    }

}
