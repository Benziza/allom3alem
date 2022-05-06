package com.example.allom3alem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecyclerViewAdapterListOuvrier extends  RecyclerView.Adapter<RecyclerViewAdapterListOuvrier.ListOuvrierViewHolder>{

    ArrayList<Ouvrier> ouvriers;
    MyDataBase db;
    ArrayList<Ouvrier> dbListOuvrier = new ArrayList<>();

    public RecyclerViewAdapterListOuvrier(ArrayList<Ouvrier> ouvriers) {
        this.ouvriers = ouvriers;
    }

    @NonNull
    @Override
    public ListOuvrierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,null,false);
        ListOuvrierViewHolder viewHolder = new ListOuvrierViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListOuvrierViewHolder holder, int position) {
        Ouvrier o = ouvriers.get(position);
        holder.imageOuvrier.setImageBitmap(BitmapFactory.decodeByteArray(o.getPHOTO(), 0, o.getPHOTO().length));
        holder.tv_nom.setText(o.getNOM());
        holder.tv_rating.setText(o.getRATING()+"");
        holder.tv_occurence.setText(o.getOCCURENCE()+"");
        holder.tv_dispo.setText(o.getDISPONIBILITE());


        holder.tv_nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = new MyDataBase(v.getContext());
                dbListOuvrier = db.getOuvriers();

                for(int i=0;i<dbListOuvrier.size();i++)
                {
                    if(dbListOuvrier.get(i).getNOM().equals(o.getNOM()))
                    {
                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("photoProfil", encodeTobase64(BitmapFactory.decodeByteArray(dbListOuvrier.get(i).getPHOTO(),0, dbListOuvrier.get(i).getPHOTO().length)));
                        editor.putString("nom",dbListOuvrier.get(i).getNOM());
                        editor.putString("desc",dbListOuvrier.get(i).getDESCRIPTION());
                        editor.putString("adr",dbListOuvrier.get(i).getADRESSE());
                        editor.putInt("occurence",dbListOuvrier.get(i).getOCCURENCE());
                        editor.putFloat("rating", (float) dbListOuvrier.get(i).getRATING());
                        editor.putString("dispo", dbListOuvrier.get(i).getDISPONIBILITE());
                        editor.putString("myPhone", dbListOuvrier.get(i).getMOBILE());
                        editor.putString("photoDeTravail",encodeTobase64(BitmapFactory.decodeByteArray(dbListOuvrier.get(i).getPHOTOTRAVAIL(),0,dbListOuvrier.get(i).getPHOTOTRAVAIL().length)));
                        editor.apply();
                        Intent intent = new Intent(v.getContext(), ActivityModeNormalPorfilOuvrier.class);
                        v.getContext().startActivity(intent);
                    }
                }
            }
        });
        holder.imageOuvrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = new MyDataBase(v.getContext());
                dbListOuvrier = db.getOuvriers();

                for(int i=0;i<dbListOuvrier.size();i++)
                {
                    if(dbListOuvrier.get(i).getNOM().equals(o.getNOM()))
                    {
                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("photoProfil", encodeTobase64(BitmapFactory.decodeByteArray(dbListOuvrier.get(i).getPHOTO(),0, dbListOuvrier.get(i).getPHOTO().length)));
                        editor.putString("nom",dbListOuvrier.get(i).getNOM());
                        editor.putString("desc",dbListOuvrier.get(i).getDESCRIPTION());
                        editor.putString("adr",dbListOuvrier.get(i).getADRESSE());
                        editor.putInt("occurence",dbListOuvrier.get(i).getOCCURENCE());
                        editor.putFloat("rating", (float) dbListOuvrier.get(i).getRATING());
                        editor.putString("dispo", dbListOuvrier.get(i).getDISPONIBILITE());
                        editor.putString("myPhone", dbListOuvrier.get(i).getMOBILE());
                        editor.putString("photoDeTravail",encodeTobase64(BitmapFactory.decodeByteArray(dbListOuvrier.get(i).getPHOTOTRAVAIL(),0,dbListOuvrier.get(i).getPHOTOTRAVAIL().length)));
                        editor.apply();
                        Intent intent = new Intent(v.getContext(), ActivityModeNormalPorfilOuvrier.class);
                        v.getContext().startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ouvriers.size();
    }

    //holder class for recycler view
    class ListOuvrierViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageOuvrier;
        TextView tv_nom,tv_rating,tv_occurence,tv_dispo;
        public ListOuvrierViewHolder(@NonNull View itemView) {
            super(itemView);
            imageOuvrier = itemView.findViewById(R.id.imageOuvrier);
            tv_nom = itemView.findViewById(R.id.tv_nom);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            tv_occurence = itemView.findViewById(R.id.tv_occurence);
            tv_dispo = itemView.findViewById(R.id.tv_dispo);
        }
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

