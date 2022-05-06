package com.example.allom3alem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    RelativeLayout rellay1;
    TextView textView;
    Button button;
    MyDataBase db;
    Spinner sp_region,sp_ville;
    ArrayList<String> arrayList_region;
    ArrayAdapter<String> arrayAdapter_region;
    ArrayAdapter<String> arrayAdapter_ville;

    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        rellay1 = findViewById(R.id.rellay1);
        textView = findViewById(R.id.nom_app);

        handler.postDelayed(runnable, 2000);

        button = findViewById(R.id.btnSearch);

        db = new MyDataBase(ActivityMain.this);

        sp_region = findViewById(R.id.sp_parent);
        sp_ville = findViewById(R.id.sp_child);

        arrayList_region = db.getAllRegion();


        arrayAdapter_region = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_region);
        sp_region.setAdapter(arrayAdapter_region);

        sp_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrayAdapter_ville = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,db.getVille(position+1));
                sp_ville.setAdapter(arrayAdapter_ville);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("nomVille",sp_ville.getSelectedItem().toString());
                editor.apply();

                startActivity(new Intent(ActivityMain.this, ActivityListMetiers.class));
            }
        });
    }
}