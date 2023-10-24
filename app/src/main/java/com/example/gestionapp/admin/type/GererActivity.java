package com.example.gestionapp.admin.type;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gestionapp.R;
import com.example.gestionapp.admin.gerer.AjouterActivity;
import com.example.gestionapp.admin.gerer.ConsulterActivity;
import com.example.gestionapp.admin.gerer.EnvoyerActivity;
import com.example.gestionapp.admin.gerer.InformationUserActivity;
import com.example.gestionapp.admin.gerer.SupprimerActivity;

public class GererActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_admin);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ListView listView = findViewById(R.id.listView_info);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Determine which item was clicked
                switch (position) {
                    case 0:
                        // Consulter la liste des utilisateurs inscrits
                        Intent intent0 = new Intent(GererActivity.this, ConsulterActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        // Ajouter un utilisateur
                        Intent intent1 = new Intent(GererActivity.this, AjouterActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        // Supprimer un utilisateur
                        Intent intent2 = new Intent(GererActivity.this, SupprimerActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        // Consulter les informations d’un utilisateur
                        Intent intent3 = new Intent(GererActivity.this, InformationUserActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        // Envoyer un courriel à un utilisateur
                        Intent intent4 = new Intent(GererActivity.this, EnvoyerActivity.class);
                        startActivity(intent4);
                        break;
                }
            }
        });
    }
}
