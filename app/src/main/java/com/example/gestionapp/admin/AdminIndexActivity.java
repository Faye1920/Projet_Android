package com.example.gestionapp.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.gestionapp.R;
import com.example.gestionapp.admin.login.AdminLoginActivity;
import com.example.gestionapp.admin.type.CompteActivity;
import com.example.gestionapp.admin.type.GererActivity;
import com.example.gestionapp.admin.type.MessageActivity;
import com.google.android.material.navigation.NavigationView;

public class AdminIndexActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_index);

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
            Intent intent = new Intent(AdminIndexActivity.this, AdminIndexActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gerer) {
            Intent intent = new Intent(AdminIndexActivity.this, GererActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_compte) {
            Intent intent = new Intent(AdminIndexActivity.this, CompteActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_message) {
            Intent intent = new Intent(AdminIndexActivity.this, MessageActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_support) {
//            Intent intent = new Intent(AdminIndexActivity.this, SupportActivity.class);
//            startActivity(intent);
        } else if (id == R.id.nav_contact) {
//            Intent intent = new Intent(AdminIndexActivity.this, ContactActivity.class);
//            startActivity(intent);
        } else if (id == R.id.nav_quitter) {
            Intent intent = new Intent(AdminIndexActivity.this, AdminLoginActivity.class);
            startActivity(intent);
        }

        return true;
    }

}