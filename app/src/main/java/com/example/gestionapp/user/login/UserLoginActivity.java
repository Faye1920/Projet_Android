package com.example.gestionapp.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gestionapp.MainActivity;
import com.example.gestionapp.R;
import com.example.gestionapp.user.UserIndexActivity;

public class UserLoginActivity extends AppCompatActivity {

    private EditText courrielEditText, motDePasseEditText;
    private CheckBox souvenirCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        courrielEditText = findViewById(R.id.courriel);
        motDePasseEditText = findViewById(R.id.Motdepasse);
        souvenirCheckBox = findViewById(R.id.souvenir);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button connexionButton = findViewById(R.id.userButton);
        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    Intent intent = new Intent(UserLoginActivity.this, UserIndexActivity.class);
                    startActivity(intent);
                }
            }
        });

        TextView creerCompte = findViewById(R.id.creerCompte);
        creerCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, UserEnregistreActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInput() {
        String courriel = courrielEditText.getText().toString().trim();
        String motDePasse = motDePasseEditText.getText().toString().trim();

        if (courriel.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(courriel).matches()) {
            courrielEditText.setError("Veuillez entrer une adresse email valide");
            courrielEditText.requestFocus();
            return false;
        }

        if (motDePasse.isEmpty()) {
            motDePasseEditText.setError("Veuillez entrer un mot de passe");
            motDePasseEditText.requestFocus();
            return false;
        }

        return true;
    }
}