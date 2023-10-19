package com.example.gestionapp.user.type;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.gestionapp.R;

public class CompteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        String[] profilArray = getResources().getStringArray(R.array.profil);
        String[] adresseArray = getResources().getStringArray(R.array.adresse);

        String[] infoArray = new String[profilArray.length];
        String[] adresseInfoArray = new String[adresseArray.length];

        // Ajouter une donnée pour le test
        infoArray[1] = "Simon"; // Prénom
        infoArray[2] = "Dubois"; // Nom
        infoArray[3] = "1992-12-12"; // Date de naissance
        infoArray[4] = "Étudiant"; // Profession
        infoArray[5] = "123@gmail.com"; // Adresse électronique

        adresseInfoArray[0] = "1411"; // Numéro
        adresseInfoArray[1] = "Du Fort"; // Rue
        adresseInfoArray[2] = "Montreal"; // Ville
        adresseInfoArray[3] = "Quebec"; // Province
        adresseInfoArray[4] = "H3H 2N7"; // Code postal
        adresseInfoArray[5] = "Canada"; // Pays

        CustomAdapter adapterInfo = new CustomAdapter(this, profilArray, infoArray, true);
        CustomAdapter adapterAdresse = new CustomAdapter(this, adresseArray, adresseInfoArray, true);

        ListView listViewInfo = findViewById(R.id.listView_info);
        ListView listViewAdresse = findViewById(R.id.listView_adresse);

        listViewInfo.setAdapter(adapterInfo);
        listViewAdresse.setAdapter(adapterAdresse);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private class CustomAdapter extends ArrayAdapter<String> {

        private String[] infoArray;
        private boolean isEditable;

        public CustomAdapter(Context context, String[] names, String[] infoArray, boolean isEditable) {
            super(context, R.layout.user_list_item, names);
            this.infoArray = infoArray;
            this.isEditable = isEditable;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.user_list_item, parent, false);

            TextView leftText = view.findViewById(R.id.left_text);
            final TextView rightText = view.findViewById(R.id.right_text);

            leftText.setText(getItem(position));
            rightText.setText(infoArray[position]);

            if (isEditable && position != 5) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showEditDialog(position, rightText);
                    }
                });
            }

            return view;
        }

        private void showEditDialog(final int position, final TextView textView) {
            final Dialog dialog = new Dialog(CompteActivity.this);
            dialog.setContentView(R.layout.user_info_edit);

            TextView dialogTitle = dialog.findViewById(R.id.dialog_title);
            final EditText editText = dialog.findViewById(R.id.edit_text);
            Button cancelButton = dialog.findViewById(R.id.button_cancel);
            Button saveButton = dialog.findViewById(R.id.button_save);

            String title = "Modifier votre " + getItem(position).toLowerCase();
            dialogTitle.setText(title);
            editText.setText(textView.getText().toString());

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newValue = editText.getText().toString();
                    textView.setText(newValue);
                    infoArray[position] = newValue;
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }
}
