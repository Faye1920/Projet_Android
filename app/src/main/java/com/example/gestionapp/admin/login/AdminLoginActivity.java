package com.example.gestionapp.admin.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.gestionapp.MainActivity;
import com.example.gestionapp.R;
import com.example.gestionapp.admin.AdminIndexActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText courrielEditText, motDePasseEditText;
    private CheckBox souvenirCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        courrielEditText = findViewById(R.id.courriel);
        motDePasseEditText = findViewById(R.id.Motdepasse);
        souvenirCheckBox = findViewById(R.id.souvenir);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button connexionButton = findViewById(R.id.adminButton);
        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    Intent intent = new Intent(AdminLoginActivity.this, AdminIndexActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateInput() {
        String courriel = courrielEditText.getText().toString().trim();
        String motDePasse = motDePasseEditText.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(courriel).matches()) {
            courrielEditText.setError("Veuillez-vous entrer une adresse email valide");
            courrielEditText.requestFocus();
            return false;
        }

        if (motDePasse.isEmpty()) {
            motDePasseEditText.setError("Veuillez-vous entrer un mot de passe");
            motDePasseEditText.requestFocus();
            return false;
        }

        return true;
    }
}