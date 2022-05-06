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
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ActivitySingIn extends AppCompatActivity {
    Button btnConnecter;
    TextInputLayout Mobile;
    TextInputLayout Password;
    MyDataBase db;
    ArrayList<Ouvrier> dbListOuvrier = new ArrayList<>();
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_in);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        db = new MyDataBase(ActivitySingIn.this);

        btnConnecter = findViewById(R.id.btnConnecter);
        Mobile = findViewById(R.id.mobile);
        Password = findViewById(R.id.passwd);

        dbListOuvrier = db.getOuvriers();

        btnConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();

                System.out.println(Mobile.getEditText().getText().toString());

                boolean isExist = db.checkUserExist(Mobile.getEditText().getText().toString(), Password.getEditText().getText().toString());
                System.out.println(Mobile.getEditText().getText().toString());
                if(isExist) {
                    if (extras.getInt("codeListMetierNormale") == 1) {
                        startActivity(new Intent(ActivitySingIn.this, ActivityListMetiersClient.class));
                    }
                    if (extras.getInt("codeListOuvrierNormale") == 1) {
                        startActivity(new Intent(ActivitySingIn.this, ActivityListOuvrierClient.class));
                    }
                    if (extras.getInt("codeCompteNormale") == 1) {
                        startActivity(new Intent(ActivitySingIn.this, ActivityModeClientPorfilOuvrier.class));
                    }
                    if (extras.getInt("codeOuvrier") == 1) {
                        //chercher sur l'ouvrier qui a le numero de telephone entrer et l'envoyer a son profil
                        for(int i = 0;i<dbListOuvrier.size();i++)
                        {
                            if(dbListOuvrier.get(i).getMOBILE().equals(Mobile.getEditText().getText().toString()))
                            {
                                sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("CINOUVRIER",dbListOuvrier.get(i).getCINOUVRIER());
                                editor.putString("nomOuvrier",dbListOuvrier.get(i).getNOM());
                                editor.putString("desc",dbListOuvrier.get(i).getDESCRIPTION());
                                editor.putString("adresse",dbListOuvrier.get(i).getADRESSE());
                                editor.putInt("occurence",dbListOuvrier.get(i).getOCCURENCE());
                                editor.putFloat("rating", (float) dbListOuvrier.get(i).getRATING());
                                editor.putString("dispo",dbListOuvrier.get(i).getDISPONIBILITE());
                                editor.putString("telephone",dbListOuvrier.get(i).getMOBILE());
                                editor.putString("password",dbListOuvrier.get(i).getPASSWORD());
                                editor.putString("photoProfil", encodeTobase64(BitmapFactory.decodeByteArray(dbListOuvrier.get(i).getPHOTO(),0, dbListOuvrier.get(i).getPHOTO().length)));
                                editor.putString("photoDeTravail",encodeTobase64(BitmapFactory.decodeByteArray(dbListOuvrier.get(i).getPHOTOTRAVAIL(),0,dbListOuvrier.get(i).getPHOTOTRAVAIL().length)));
                                editor.apply();
                                Intent intent = new Intent(ActivitySingIn.this,ActivityModeOuvrierPorfilOuvrier.class);
                                startActivity(intent);
                            }
                        }
                    }
                } else {
                    //Password.setEditText().setText(null);
                    Toast.makeText(ActivitySingIn.this, "Connexion échoué. Invalid mobile or password.", Toast.LENGTH_SHORT).show();
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