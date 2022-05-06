package com.example.allom3alem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;

public class ActivityModificationOuvrier extends AppCompatActivity {

    Button btnModification,btnImageProfil,btnImageTravaux;
    MyDataBase db;
    TextInputLayout editTextTextMultiLine,editTextTextMultiLine2,editTextTextMultiLine3,editTextPhone,editTextTextPassword,editTextTextPassword1;
    public SharedPreferences sp;
    private static final int imageCode= 100,imageCode1=101;
    //private static final int imageCodeTravaux = 101;
    private Uri imageFilePath,imageFilePath2;
    private Bitmap imageToStore,imageToStore2;
    SharedPreferences.Editor editor;
    String photoCodage,photoCodage2;
    Switch switch2;
    String disponibilite="non";
    boolean checkUpdate,checkUpdate1;
    boolean checkUpdate2,checkUpdate3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_ouvrier);
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        btnModification = findViewById(R.id.btnModification);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        editTextTextMultiLine2 =findViewById(R.id.editTextTextMultiLine2);
        editTextTextMultiLine3 = findViewById(R.id.editTextTextMultiLine3);
        switch2 = findViewById(R.id.switch2);
        editTextPhone = findViewById(R.id.editTextPhone);
        btnImageProfil = findViewById(R.id.btnImageProfil);
        btnImageTravaux = findViewById(R.id.btnImageTravaux);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextPassword1 = findViewById(R.id.editTextTextPassword1);

        editTextTextMultiLine.getEditText().setText(sp.getString("nomOuvrier","???"));
        editTextTextMultiLine2.getEditText().setText(sp.getString("desc","???"));
        editTextTextMultiLine3.getEditText().setText(sp.getString("adresse","???"));
        editTextTextPassword.getEditText().setText(sp.getString("password","123456"));

        if(sp.getString("dispo","So so").equals("oui"))
        {
            switch2.setChecked(true);
        }
        else
        {
            switch2.setChecked(false);
        }
        db = new MyDataBase(ActivityModificationOuvrier.this);


        btnModification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disponibilite = disponibilite();

                if(imageToStore != null && imageToStore2 == null) {

                    checkUpdate1 = db.updateRecordWithPhotoProfil(sp.getString("CINOUVRIER","C111112"),editTextTextMultiLine.getEditText().getText().toString(),editTextTextMultiLine2.getEditText().getText().toString(),
                            editTextTextMultiLine3.getEditText().getText().toString(),disponibilite,editTextPhone.getEditText().getText().toString(),editTextTextPassword.getEditText().getText().toString(),editTextTextPassword1.getEditText().getText().toString(),imageToStore);

                }else {

                    checkUpdate = db.updateRecord(sp.getString("CINOUVRIER","C111112"),editTextTextMultiLine.getEditText().getText().toString(),editTextTextMultiLine2.getEditText().getText().toString(),
                            editTextTextMultiLine3.getEditText().getText().toString(),disponibilite,editTextPhone.getEditText().getText().toString(),editTextTextPassword.getEditText().getText().toString(),editTextTextPassword1.getEditText().getText().toString());

                }

                if(imageToStore2 != null && imageToStore == null) {

                    checkUpdate2 = db.updateRecordWithPhotoTravail(sp.getString("CINOUVRIER","C111112"),editTextTextMultiLine.getEditText().getText().toString(),editTextTextMultiLine2.getEditText().getText().toString(),
                            editTextTextMultiLine3.getEditText().getText().toString(),disponibilite,editTextPhone.getEditText().getText().toString(),editTextTextPassword.getEditText().getText().toString(),editTextTextPassword1.getEditText().getText().toString(),imageToStore2);

                }else {

                    checkUpdate = db.updateRecord(sp.getString("CINOUVRIER","C111112"),editTextTextMultiLine.getEditText().getText().toString(),editTextTextMultiLine2.getEditText().getText().toString(),
                            editTextTextMultiLine3.getEditText().getText().toString(),disponibilite,editTextPhone.getEditText().getText().toString(),editTextTextPassword.getEditText().getText().toString(),editTextTextPassword1.getEditText().getText().toString());

                }

                if(imageToStore != null && imageToStore2 != null) {

                    checkUpdate3 = db.updateRecordWithBoth(sp.getString("CINOUVRIER","C111112"),editTextTextMultiLine.getEditText().getText().toString(),editTextTextMultiLine2.getEditText().getText().toString(),
                            editTextTextMultiLine3.getEditText().getText().toString(),disponibilite,editTextPhone.getEditText().getText().toString(),editTextTextPassword.getEditText().getText().toString(),editTextTextPassword1.getEditText().getText().toString(),imageToStore,imageToStore2);

                }else {

                    checkUpdate = db.updateRecord(sp.getString("CINOUVRIER","C111112"),editTextTextMultiLine.getEditText().getText().toString(),editTextTextMultiLine2.getEditText().getText().toString(),
                            editTextTextMultiLine3.getEditText().getText().toString(),disponibilite,editTextPhone.getEditText().getText().toString(),editTextTextPassword.getEditText().getText().toString(),editTextTextPassword1.getEditText().getText().toString());

                }

                if(checkUpdate == true) {

                    editor = sp.edit();
                    editor.putString("nomOuvrier",editTextTextMultiLine.getEditText().getText().toString());
                    editor.putString("adresse",editTextTextMultiLine2.getEditText().getText().toString());
                    editor.putString("desc",editTextTextMultiLine3.getEditText().getText().toString());
                    editor.putString("dispo",disponibilite);
                    editor.putString("telephone",editTextPhone.getEditText().getText().toString());
                    Toast.makeText(ActivityModificationOuvrier.this,"Entry Updated WithOut Image",Toast.LENGTH_SHORT).show();

                }

                if(checkUpdate1 == true) {

                    editor = sp.edit();
                    editor.putString("nomOuvrier",editTextTextMultiLine.getEditText().getText().toString());
                    editor.putString("adresse",editTextTextMultiLine2.getEditText().getText().toString());
                    editor.putString("desc",editTextTextMultiLine3.getEditText().getText().toString());
                    editor.putString("dispo",disponibilite);
                    editor.putString("telephone",editTextPhone.getEditText().getText().toString());
                    editor.putString("photoProfil", photoCodage);
                    Toast.makeText(ActivityModificationOuvrier.this,"Entry Updated With Profil Image",Toast.LENGTH_SHORT).show();

                }

                if(checkUpdate2 == true) {

                    editor = sp.edit();
                    editor.putString("nomOuvrier",editTextTextMultiLine.getEditText().getText().toString());
                    editor.putString("adresse",editTextTextMultiLine2.getEditText().getText().toString());
                    editor.putString("desc",editTextTextMultiLine3.getEditText().getText().toString());
                    editor.putString("dispo",disponibilite);
                    editor.putString("telephone",editTextPhone.getEditText().getText().toString());
                    editor.putString("photoDeTravail", photoCodage2);
                    Toast.makeText(ActivityModificationOuvrier.this,"Entry Updated With Work Image",Toast.LENGTH_SHORT).show();
                }

                if(checkUpdate3 == true) {

                    editor = sp.edit();
                    editor.putString("nomOuvrier",editTextTextMultiLine.getEditText().getText().toString());
                    editor.putString("adresse",editTextTextMultiLine2.getEditText().getText().toString());
                    editor.putString("desc",editTextTextMultiLine3.getEditText().getText().toString());
                    editor.putString("dispo",disponibilite);
                    editor.putString("telephone",editTextPhone.getEditText().getText().toString());
                    editor.putString("photoProfil", photoCodage);
                    editor.putString("photoDeTravail", photoCodage2);
                    Toast.makeText(ActivityModificationOuvrier.this,"Entry Updated For Both Images",Toast.LENGTH_SHORT).show();
                }

                editor.apply();
                Intent intent = new Intent(ActivityModificationOuvrier.this,ActivityModeOuvrierPorfilOuvrier.class);
                startActivity(intent);
            }
        });
    }

    //la methode appeler par la button de photo de profil
    public void choisirImageProfil(View view) {

        try {

            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,imageCode);
            Toast.makeText(this, "Image Profil Selected", Toast.LENGTH_SHORT).show();

        }catch(Exception ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    //la methode appeler par la button de photo de travaux

    public void choisirImageTravail(View view) {

        try {

            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,imageCode1);
            Toast.makeText(this, "Image Travail Selected", Toast.LENGTH_SHORT).show();

        }catch(Exception ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    //La méthode pour stocker le chemin de l'image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try {

            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == imageCode && resultCode==RESULT_OK && data!=null && data.getData()!=null) {

                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
                photoCodage = encodeTobase64(imageToStore);
//                boolean check1 = db.updateRecord2(sp.getString("CINOUVRIER","C111114"),imageToStore);
//                if(check1 == true) {
//
//                    Toast.makeText(this, "Image Profil Updated", Toast.LENGTH_SHORT).show();
//                }else {
//
//                    Toast.makeText(this, "Image Profil not Updated", Toast.LENGTH_SHORT).show();
//                }
            }
            if(requestCode == imageCode1 && resultCode==RESULT_OK && data!=null && data.getData()!=null) {

                imageFilePath2 = data.getData();
                imageToStore2 = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath2);
                photoCodage2 = encodeTobase64(imageToStore2);
//                boolean check2 = db.updateRecord3(sp.getString("CINOUVRIER","C111114"),imageToStore2);
//                if(check2 == true) {
//
//                    Toast.makeText(this, "Image Travail Updated", Toast.LENGTH_SHORT).show();
//                }else {
//
//                    Toast.makeText(this, "Image Travail not Updated", Toast.LENGTH_SHORT).show();
//                }
            }
        }catch (Exception ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    public String disponibilite()
    {
        if(switch2.isChecked())
        {
            disponibilite = "oui";
        }
        else
        {
            disponibilite = "non";
        }

        return disponibilite;
    }
}