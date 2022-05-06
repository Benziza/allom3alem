package com.example.allom3alem;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ActivityModeNormalPorfilOuvrier extends AppCompatActivity {

    Button btnAuthentifier,mobile;
    ImageView photoProfil,imageTravail;
    boolean test;
    TextView tv_nom,tv_desc,tv_adr,tv_occurence,tv_rating,tv_dispo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_normal_profil_ouvrier);

        photoProfil = findViewById(R.id.photoProfil);
        tv_nom = findViewById(R.id.tv_nom_ouvrier_normale);
        tv_desc = findViewById(R.id.tv_desc_ouvrier_normale);
        tv_adr = findViewById(R.id.tv_adr_ouvrier_normale);
        tv_occurence = findViewById(R.id.tv_occurence_ouvrier_normale);
        tv_rating = findViewById(R.id.tv_rating_ouvrier_normale);
        tv_dispo = findViewById(R.id.tv_dispo_ouvrier_normale);
        mobile = findViewById(R.id.mobile);
        imageTravail = findViewById(R.id.imageTravail);

        SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        photoProfil.setImageBitmap(decodeBase64(sp1.getString("photoProfil","ThePhoto")));
        tv_nom.setText(sp1.getString("nom","XXXX"));
        tv_desc.setText(sp1.getString("desc","XXXX"));
        tv_adr.setText(sp1.getString("adr","XXXX"));
        tv_occurence.setText(sp1.getInt("occurence",0)+"");
        tv_rating.setText(sp1.getFloat("rating",0.0f)+"");
        tv_dispo.setText(sp1.getString("dispo","XXXX"));
        imageTravail.setImageBitmap(decodeBase64(sp1.getString("photoDeTravail","ThePhoto")));


        btnAuthentifier = findViewById(R.id.btnAuthentifier);

        btnAuthentifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopUpAthentification popUpAthentification = new PopUpAthentification();

                popUpAthentification.show(getSupportFragmentManager(),"PopUpAuthentification");
                test=true;
            }
        });

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CALL_PHONE);
                String s = "tel:"+ sp1.getString("myPhone","12345678901");

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ActivityModeNormalPorfilOuvrier.this, new String[]{Manifest.permission.CALL_PHONE}, Integer.parseInt("123"));
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse(s)));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();


        if(item_id==R.id.client){
            PopUpAthentification popUpAthentification = new PopUpAthentification();

            popUpAthentification.show(getSupportFragmentManager(),"PopUpAuthentification");
            test=true;
        }
        if(item_id==R.id.ouvrier){
            PopUpAthentification popUpAthentification = new PopUpAthentification();

            popUpAthentification.show(getSupportFragmentManager(),"PopUpAuthentification");
            test=false;
        }
        return true;
    }

    public void connecter(View view)
    {
        if(test==true)
        {
            Intent intent = new Intent(ActivityModeNormalPorfilOuvrier.this, ActivitySingIn.class);
            intent.putExtra("codeCompteNormale",1);
            startActivity(intent);
        }
        else if(test==false)
        {
            Intent intent = new Intent(ActivityModeNormalPorfilOuvrier.this, ActivitySingIn.class);
            intent.putExtra("codeOuvrier",1);
            startActivity(intent);
        }
    }
    public void compte(View view)
    {
        if(test==true)
        {
            Intent intent = new Intent(ActivityModeNormalPorfilOuvrier.this, ActivityCreeCompteClient.class);
            intent.putExtra("codeCompteNormale",1);
            startActivity(intent);
        }
        if(test==false)
        {
            Intent intent = new Intent(ActivityModeNormalPorfilOuvrier.this, ActivityCreeCompteOuvrier.class);
            intent.putExtra("codeOuvrier",1);
            startActivity(intent);
        }
    }
    // method for base64 to bitmap
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}