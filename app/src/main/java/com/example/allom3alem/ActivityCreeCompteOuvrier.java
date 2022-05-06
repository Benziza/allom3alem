package com.example.allom3alem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;

public class ActivityCreeCompteOuvrier extends AppCompatActivity {

    Button btnCreeCompteOuvrier;
    TextInputLayout cin;
    TextInputLayout idmetier;
    TextInputLayout nomville;
    TextInputLayout idtravail;
    TextInputLayout tel;
    TextInputLayout adresse;
    TextInputLayout description;
    TextInputLayout nom;
    TextInputLayout motdepasse;
    MyDataBase db;
    Ouvrier ouvrier = new Ouvrier();
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cree_compte_ouvrier);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        MyDataBase db=new MyDataBase(ActivityCreeCompteOuvrier.this);

        btnCreeCompteOuvrier = findViewById(R.id.btnCreeCompteOuvrier);
        cin=findViewById(R.id.cinouvrier);
        idmetier=findViewById(R.id.idmetier);
        nomville=findViewById(R.id.nomville);
        idtravail=findViewById(R.id.idtravail);
        nom=findViewById(R.id.Nom);
        cin=findViewById(R.id.cinouvrier);
        tel=findViewById(R.id.telephone);
        description=findViewById(R.id.description);
        adresse=findViewById(R.id.adresse);
        motdepasse=findViewById(R.id.passwd);


        btnCreeCompteOuvrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();

                int Idmetier =  Integer.parseInt(idmetier.getEditText().getText().toString()==""?"0":idmetier.getEditText().getText().toString());
                String Nomville =  nomville.getEditText().getText().toString();
                String Cin =  cin.getEditText().getText().toString();
                String Nom =  nom.getEditText().getText().toString();
                String Tel =  tel.getEditText().getText().toString();
                String Description =  description.getEditText().getText().toString();
                String Adresse =  adresse.getEditText().getText().toString();
                String Idtravail =  idtravail.getEditText().getText().toString();
                String Motdepasse =  motdepasse.getEditText().getText().toString();

                ouvrier.setIDMETIER(Idmetier);
                ouvrier.setNOMVILLE(Nomville);
                ouvrier.setCINOUVRIER(Cin);
                ouvrier.setNOM(Nom);
                ouvrier.setMOBILE(Tel);
                ouvrier.setDESCRIPTION(Description);
                ouvrier.setADRESSE(Adresse);
                ouvrier.setIDTRAVAIL(Idtravail);
                ouvrier.setPASSWORD(Motdepasse);

                sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("CINOUVRIER",Cin);
                editor.putString("nomOuvrier",Nom);
                editor.putString("desc",Description);
                editor.putString("adresse",Adresse);
                editor.putInt("occurence",0);
                editor.putFloat("rating", (float) 0.0);
                editor.putString("dispo","oui");
                editor.putString("telephone",Tel);
                editor.putString("password",Motdepasse);
                editor.putString("photoProfil", encodeTobase64(BitmapFactory.decodeByteArray(db.getOuvriers().get(0).getPHOTO(),0, db.getOuvriers().get(0).getPHOTO().length)));
                editor.putString("photoDeTravail",encodeTobase64(BitmapFactory.decodeByteArray(db.getOuvriers().get(0).getPHOTO(),0, db.getOuvriers().get(0).getPHOTO().length)));
                editor.apply();

                db.ajouterouvrier(ouvrier,db.getOuvriers().get(0).getPHOTO());

                if(extras.getInt("codeListMetierNormale")==1)
                {
                    startActivity(new Intent(ActivityCreeCompteOuvrier.this, ActivityModeOuvrierPorfilOuvrier.class));
                }
                if(extras.getInt("codeOuvrier")==1)
                {
                    startActivity(new Intent(ActivityCreeCompteOuvrier.this, ActivityModeOuvrierPorfilOuvrier.class));
                }
            }

        });

    }
    // method for bitmap to base64
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

}