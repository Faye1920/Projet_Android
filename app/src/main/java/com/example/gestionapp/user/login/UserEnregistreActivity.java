package com.example.gestionapp.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gestionapp.R;
import com.example.gestionapp.user.UserIndexActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserEnregistreActivity extends AppCompatActivity {

    private EditText courrielEditText, motDePasseEditText, confirmMotDePasseEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_enregistre);

        courrielEditText = findViewById(R.id.courriel);
        motDePasseEditText = findViewById(R.id.Motdepasse);
        confirmMotDePasseEditText = findViewById(R.id.confirm_Motdepasse);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserEnregistreActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });

        Button creerButton = findViewById(R.id.userButton);
        creerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    String courriel = courrielEditText.getText().toString().trim();
                    String motDePasse = motDePasseEditText.getText().toString().trim();
                    saveToUserFile(courriel, motDePasse);

                    Intent intent = new Intent(UserEnregistreActivity.this, UserLoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        TextView connectCompte = findViewById(R.id.connectCompte);
        connectCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserEnregistreActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInput() {
        String courriel = courrielEditText.getText().toString().trim();
        String motDePasse = motDePasseEditText.getText().toString().trim();
        String confirmMotDePasse = confirmMotDePasseEditText.getText().toString().trim();

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

        if (!motDePasse.equals(confirmMotDePasse)) {
            confirmMotDePasseEditText.setError("La saisie du mot de passe est différente");
            confirmMotDePasseEditText.requestFocus();
            return false;
        }

        return true;
    }

    private void saveToUserFile(String courriel, String motDePasse) {
        String userData = courriel + "," + motDePasse + "\n";
        try {
            File file = new File(getFilesDir() + "/data/user.txt");
            FileWriter writer = new FileWriter(file, true);
            writer.append(userData);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
