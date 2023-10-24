package com.example.gestionapp.admin.gerer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AjouterActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        // Request WRITE_EXTERNAL_STORAGE permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        }

        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText adresse_courriel = findViewById(R.id.adresse_courriel);
                EditText mot_passe = findViewById(R.id.mot_passe);
                EditText prenom = findViewById(R.id.prenom);
                EditText nom = findViewById(R.id.nom);

                String email = adresse_courriel.getText().toString();
                String password = mot_passe.getText().toString();
                String firstName = prenom.getText().toString();
                String lastName = nom.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(AjouterActivity.this, "Email and Password are mandatory fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Construct the data to be written to the file
                String userData = "Email: " + email + "\n" +
                        "Password: " + password + "\n" +
                        "First Name: " + firstName + "\n" +
                        "Last Name: " + lastName + "\n";

                // Define the file path
                String filePath = "/sdcard/user.txt"; // Change the path as per your requirements

                // Write to the file
                try {
                    File file = new File(filePath);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(userData.getBytes());
                    fos.close();
                    Toast.makeText(AjouterActivity.this, "Data saved to user.txt", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Bouton pour revenir à la page Acceuil
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can now write to external storage
            } else {
                // Permission denied
                // Handle accordingly (e.g., show a message)
            }
        }
    }
}
