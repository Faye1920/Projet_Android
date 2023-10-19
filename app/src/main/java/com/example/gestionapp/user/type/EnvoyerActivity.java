package com.example.gestionapp.user.type;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionapp.R;
import com.example.gestionapp.databinding.ActivityEnvoyerBinding;

public class EnvoyerActivity extends AppCompatActivity {

    EditText editTextToEmail, editTextSujet, editTextContent;
    Button button;

    // Bouton pour revenir à la page Acceuil
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoyer);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setContentView(R.layout.activity_envoyer);
        button = findViewById(R.id.btnSend);
        editTextSujet = findViewById(R.id.sujet);
        editTextContent = findViewById(R.id.content);
        editTextToEmail = findViewById(R.id.to_email);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sujet, content, to_email;
                sujet = editTextSujet.getText().toString();
                content = editTextContent.getText().toString();
                to_email = editTextToEmail.getText().toString();
                if (sujet.equals("") && content.equals("") && to_email.equals("")) {
                    Toast.makeText(EnvoyerActivity.this, "Tous les champs sont requis", Toast.LENGTH_SHORT).show();
                }
                else {
                    sendEmail(sujet, content, to_email);
                }
            }
        });
    }

    public void sendEmail(String sujet, String content, String to_email) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to_email});
        intent.putExtra(Intent.EXTRA_SUBJECT, sujet);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choisissez un client de courriel:"));
    }
}