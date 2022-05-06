package com.example.allom3alem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityListOuvrier extends AppCompatActivity {

    RecyclerView rvlo;
    boolean test;
    MyDataBase db;
    ArrayList<Ouvrier> dbListOuvrier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ouvrier);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());


        db = new MyDataBase(ActivityListOuvrier.this);
        dbListOuvrier = db.getOuvriers();

        rvlo = findViewById(R.id.rv_list_ouvrier);

        ArrayList<Ouvrier> ouvriers = new ArrayList<>();

        for(int i = 0;i<dbListOuvrier.size();i++)
        {
            if(dbListOuvrier.get(i).getNOMVILLE().equals(sp.getString("nomVille","Oujda")) && dbListOuvrier.get(i).getIDMETIER()==sp.getInt("position",1))
            {
                ouvriers.add(new Ouvrier(dbListOuvrier.get(i).getNOM(),dbListOuvrier.get(i).getRATING(),dbListOuvrier.get(i).getOCCURENCE(),dbListOuvrier.get(i).getDISPONIBILITE(),dbListOuvrier.get(i).getIDMETIER(),dbListOuvrier.get(i).getNOMVILLE(),dbListOuvrier.get(i).getPHOTO()));
            }
        }

        RecyclerViewAdapterListOuvrier adapter = new RecyclerViewAdapterListOuvrier(ouvriers);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        rvlo.setHasFixedSize(true);
        rvlo.setLayoutManager(lm);
        rvlo.setAdapter(adapter);
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
            Intent intent = new Intent(ActivityListOuvrier.this, ActivitySingIn.class);
            intent.putExtra("codeListOuvrierNormale",1);
            startActivity(intent);
        }
        else if(test==false)
        {
            Intent intent = new Intent(ActivityListOuvrier.this, ActivitySingIn.class);
            intent.putExtra("codeOuvrier",1);
            startActivity(intent);
        }
    }
    public void compte(View view)
    {
        if(test==true)
        {
            Intent intent = new Intent(ActivityListOuvrier.this, ActivityCreeCompteClient.class);
            intent.putExtra("codeListOuvrierNormale",1);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(ActivityListOuvrier.this, ActivityCreeCompteOuvrier.class);
            intent.putExtra("codeListMetierNormale",1);
            startActivity(intent);
        }
    }
}