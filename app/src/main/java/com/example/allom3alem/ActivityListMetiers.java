package com.example.allom3alem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityListMetiers extends AppCompatActivity {
    boolean test;
    RecyclerView rv;
    MyDataBase db;
    ArrayList<Metiers> dbListMetier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listemetiers);

        db = new MyDataBase(ActivityListMetiers.this);
        dbListMetier = db.getListMetiers();


        rv = findViewById(R.id.recyclerviewid);

        ArrayList<Metiers> metiers = new ArrayList<>();

        for(int i = 0;i<dbListMetier.size();i++)
        {
            metiers.add(new Metiers(dbListMetier.get(i).getId(),dbListMetier.get(i).getNom(),dbListMetier.get(i).getLogo()));
        }

        RecyclerViewAdapterListMetiers adapter = new RecyclerViewAdapterListMetiers(metiers);
        RecyclerView.LayoutManager lm = new GridLayoutManager(this,3);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);


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
            Intent intent = new Intent(ActivityListMetiers.this, ActivitySingIn.class);
            intent.putExtra("codeListMetierNormale",1);
            startActivity(intent);
        }
        else if(test==false)
        {
            Intent intent = new Intent(ActivityListMetiers.this, ActivitySingIn.class);
            intent.putExtra("codeOuvrier",1);
            startActivity(intent);
        }
    }
    public void compte(View view)
    {
        if(test==true)
        {
            Intent intent = new Intent(ActivityListMetiers.this, ActivityCreeCompteClient.class);
            intent.putExtra("codeListMetierNormale",1);
            startActivity(intent);
        }
        else if(test==false)
        {
            Intent intent = new Intent(ActivityListMetiers.this, ActivityCreeCompteOuvrier.class);
            intent.putExtra("codeListMetierNormale",1);
            startActivity(intent);
        }
    }
}