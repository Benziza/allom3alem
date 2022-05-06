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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;

public class ActivityModeClientPorfilOuvrier extends AppCompatActivity {

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    Button btnEvaluer,mobileClient,btnEvaluerPop;
    ImageView photoProfil,imageTravail;
    boolean test;
    TextView tv_nom,tv_desc,tv_adr,tv_occurence,tv_rating,tv_dispo,tv_occurence_ouvrier_client;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5;

    int cmpEvaluation=0,cmpOccurence=0;
    float oldValue=0.0f,newValue=0.0f;
    float value = 0.0f,valueAffichage=0.0f;
    RadioButton newRadio;

    MyDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_client_profil_ouvrier);

        photoProfil = findViewById(R.id.photoProfil);
        tv_nom = findViewById(R.id.tv_nom_ouvrier_client);
        tv_desc = findViewById(R.id.tv_desc_ouvrier_client);
        tv_adr = findViewById(R.id.tv_adr_ouvrier_client);
        tv_occurence = findViewById(R.id.tv_occurence_ouvrier_client);
        tv_rating = findViewById(R.id.tv_rating_ouvrier_client);
        tv_dispo = findViewById(R.id.tv_dispo_ouvrier_client);
        mobileClient = findViewById(R.id.mobileClient);
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

        db = new MyDataBase(ActivityModeClientPorfilOuvrier.this);

        btnEvaluer = findViewById(R.id.btnEvaluer);

        btnEvaluer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PopUpEvaluation popUpEvaluation = new PopUpEvaluation();
//                popUpEvaluation.show(getSupportFragmentManager(),"PopUpEvaluation");

                View popUpEvaluation = getLayoutInflater().inflate(R.layout.popupevaluation
                        ,null);

                //La déclaration des composants de la popup
                btnEvaluerPop = popUpEvaluation.findViewById(R.id.btnEvaluerPop);
                radioGroup = popUpEvaluation.findViewById(R.id.radioGroup);

                radioButton1 = popUpEvaluation.findViewById(R.id.radio1);
                radioButton2 = popUpEvaluation.findViewById(R.id.radio2);
                radioButton3 = popUpEvaluation.findViewById(R.id.radio3);
                radioButton4 = popUpEvaluation.findViewById(R.id.radio4);
                radioButton5 = popUpEvaluation.findViewById(R.id.radio5);

                //La formatation du Rating
                DecimalFormat d= new DecimalFormat();
                d.setMaximumFractionDigits(1);


                btnEvaluerPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        oldValue = value;

                        if(radioButton1.isChecked()) {

                            cmpOccurence++;
                            cmpEvaluation++;
                            newRadio = radioButton1;

                        }
                        if(radioButton2.isChecked()) {

                            cmpOccurence++;
                            cmpEvaluation++;
                            newRadio = radioButton2;

                        }
                        if(radioButton3.isChecked()) {

                            cmpOccurence++;
                            cmpEvaluation++;
                            newRadio = radioButton3;

                        }
                        if(radioButton4.isChecked()) {

                            cmpOccurence++;
                            cmpEvaluation++;
                            newRadio = radioButton4;

                        }

                        if(radioButton5.isChecked()) {

                            cmpOccurence++;
                            cmpEvaluation++;
                            newRadio = radioButton5;
                        }

                        newValue = Float.parseFloat(newRadio.getText().toString());

                        //Le calcul
                        value = oldValue + newValue;
                        valueAffichage = value/cmpEvaluation;
                        tv_rating.setText("" + d.format(valueAffichage));
                        tv_occurence.setText("" + cmpOccurence);

                        //L'appel de la méthode de modification dans la base de données
                        boolean checkUpdate = db.updateRecordRating(sp1.getString("CINOUVRIER","C111112"),valueAffichage,cmpOccurence);

                        if(checkUpdate == true) {

                            Toast.makeText(ActivityModeClientPorfilOuvrier.this, "Entry Rating and Occurence Updated", Toast.LENGTH_SHORT).show();
                        }else {

                            Toast.makeText(ActivityModeClientPorfilOuvrier.this, "Entry Rating and Occurence Not Updated", Toast.LENGTH_SHORT).show();
                        }

                        alertDialog.dismiss();
                    }
                });

                builder = new AlertDialog.Builder(ActivityModeClientPorfilOuvrier.this);
                builder.setView(popUpEvaluation);
                alertDialog = builder.create();
                alertDialog.show();

            }
        });



        mobileClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CALL_PHONE);
                String s = "tel:"+ sp1.getString("myPhone","12345678901");

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ActivityModeClientPorfilOuvrier.this, new String[]{Manifest.permission.CALL_PHONE}, Integer.parseInt("123"));
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse(s)));
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();

        if(item_id==R.id.ouvrier){
            PopUpAthentification popUpAthentification = new PopUpAthentification();

            popUpAthentification.show(getSupportFragmentManager(),"PopUpAuthentification");
            test=false;
        }
        return true;
    }

    public void connecter(View view)
    {
        Intent intent = new Intent(ActivityModeClientPorfilOuvrier.this, ActivitySingIn.class);
        intent.putExtra("codeOuvrier",1);
        startActivity(intent);
    }
    public void compte(View view)
    {
        if(test==true)
        {
            Intent intent = new Intent(ActivityModeClientPorfilOuvrier.this, ActivityCreeCompteClient.class);
            intent.putExtra("codeCompteNormale",1);
            startActivity(intent);
        }
        if(test==false)
        {
            startActivity(new Intent(ActivityModeClientPorfilOuvrier.this, ActivityCreeCompteOuvrier.class));
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ActivityModeClientPorfilOuvrier.this, ActivityListOuvrierClient.class));
        //super.onBackPressed();
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}
