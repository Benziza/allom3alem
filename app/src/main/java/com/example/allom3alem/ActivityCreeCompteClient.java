package com.example.allom3alem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ActivityCreeCompteClient extends AppCompatActivity {
    Button btnCreeCompteClient;

    TextInputLayout cin;
    TextInputLayout tel;
    TextInputLayout nom;
    TextInputLayout mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cree_compte_client);
        cin=findViewById(R.id.cinclient);
        nom=findViewById(R.id.nomclient);
        tel=findViewById(R.id.telephoneclient);
        mdp=findViewById(R.id.mdpclient);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnCreeCompteClient = findViewById(R.id.btnCreeCompteClient);
        MyDataBase db=new MyDataBase(ActivityCreeCompteClient.this);

        btnCreeCompteClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cin =  cin.getEditText().getText().toString();
                String Nom =  nom.getEditText().getText().toString();
                String Tel =  tel.getEditText().getText().toString();
                String Mdp =  mdp.getEditText().getText().toString();

                if(Cin.equals("") || Nom.equals("") || Tel.equals("") || Mdp.equals(""))
                {
                    Toast.makeText(getBaseContext(),"Il faut remplir tout les champs",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.ajouterclient(Cin,Nom,Tel,Mdp);

                    Bundle extras = getIntent().getExtras();
                    if(extras.getInt("codeListMetierNormale")==1)
                    {
                        startActivity(new Intent(ActivityCreeCompteClient.this, ActivityListMetiersClient.class));
                    }
                    if(extras.getInt("codeListOuvrierNormale")==1)
                    {
                        startActivity(new Intent(ActivityCreeCompteClient.this, ActivityListOuvrierClient.class));
                    }
                    if(extras.getInt("codeCompteNormale")==1)
                    {
                        startActivity(new Intent(ActivityCreeCompteClient.this, ActivityModeClientPorfilOuvrier.class));
                    }
                }
            }
        });
    }


}