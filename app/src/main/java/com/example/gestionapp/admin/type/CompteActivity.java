package com.example.gestionapp.admin.type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.gestionapp.R;

public class CompteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_admin);

        String[] profilArray = getResources().getStringArray(R.array.Information);

        String[] infoArray = new String[profilArray.length];

        // Ajouter une donnée pour le test
        infoArray[1] = "Simon"; // Prénom
        infoArray[2] = "Dubois"; // Nom
        infoArray[3] = "123@gmail.com"; // Adresse électronique

        com.example.gestionapp.admin.type.CompteActivity.CustomAdapter adapterInfo = new com.example.gestionapp.admin.type.CompteActivity.CustomAdapter(this, profilArray, infoArray, true);

        ListView listViewInfo = findViewById(R.id.listView_info);

        listViewInfo.setAdapter(adapterInfo);

        // Bouton pour revenir à la page Acceuil
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
            super(context, R.layout.list_item, names);
            this.infoArray = infoArray;
            this.isEditable = isEditable;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.list_item, parent, false);

            TextView leftText = view.findViewById(R.id.left_text);
            final TextView rightText = view.findViewById(R.id.right_text);

            leftText.setText(getItem(position));
            rightText.setText(infoArray[position]);

            if (isEditable && position != 3) {
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
            final Dialog dialog = new Dialog(com.example.gestionapp.admin.type.CompteActivity.this);
            dialog.setContentView(R.layout.info_edit);

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