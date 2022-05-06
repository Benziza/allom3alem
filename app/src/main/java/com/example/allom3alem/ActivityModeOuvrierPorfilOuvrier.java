package com.example.allom3alem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityModeOuvrierPorfilOuvrier extends AppCompatActivity {
    Button btnModifier;
    TextView tv_nom_ouvr_normale,tv_desc_ouvrier_normale2,tv_desc_ouvrier_normale3,occurence,evaluation,disponibilite;
    ImageView photoProfil,imageTravail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_ouvrier_profil_ouvrier);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        tv_nom_ouvr_normale = findViewById(R.id.tv_nom_ouvr_normale);
        tv_desc_ouvrier_normale2 = findViewById(R.id.tv_desc_ouvrier_normale2);
        tv_desc_ouvrier_normale3 = findViewById(R.id.tv_desc_ouvrier_normale3);
        occurence = findViewById(R.id.occurence);
        evaluation = findViewById(R.id.evaluation);
        disponibilite = findViewById(R.id.disponibilite);
        photoProfil = findViewById(R.id.photoProfil);
        imageTravail = findViewById(R.id.imageTravail);


        btnModifier = findViewById(R.id.btnModifier);

        tv_nom_ouvr_normale.setText(sp.getString("nomOuvrier","Ayoub Hajji"));
        tv_desc_ouvrier_normale2.setText(sp.getString("desc","Hello World"));
        tv_desc_ouvrier_normale3.setText(sp.getString("adresse","Oujda Oujda"));
        occurence.setText(sp.getInt("occurence",0)+"");
        evaluation.setText(sp.getFloat("rating",0.0f)+"");
        disponibilite.setText(sp.getString("dispo","So so"));
        photoProfil.setImageBitmap(decodeBase64(sp.getString("photoProfil","ThePhoto")));
        imageTravail.setImageBitmap(decodeBase64(sp.getString("photoDeTravail","ThePhotoTravail")));

        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityModeOuvrierPorfilOuvrier.this, ActivityModificationOuvrier.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ActivityModeOuvrierPorfilOuvrier.this, ActivityMain.class));
        //super.onBackPressed();
    }

    // method for base64 to bitmap
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}