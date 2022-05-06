package com.example.allom3alem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterListMetiers extends RecyclerView.Adapter<RecyclerViewAdapterListMetiers.ListMetiersViewHolder> {

    ArrayList<Metiers> metiers;
    public int clickedPos ;



    public RecyclerViewAdapterListMetiers(ArrayList<Metiers> metiers) {
        this.metiers = metiers;
    }

    @NonNull
    @Override
    public ListMetiersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item_metier,null,false);
        ListMetiersViewHolder viewHolder = new ListMetiersViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListMetiersViewHolder holder, int position) {
        Metiers lm = metiers.get(position);
        holder.logoMetier.setImageBitmap(BitmapFactory.decodeByteArray(lm.getLogo(), 0, lm.getLogo().length));
        holder.nomMetier.setText(lm.getNom());

        holder.logoMetier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedPos = holder.getAdapterPosition() +1;

                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("position", clickedPos);
                editor.putString("nomVille",sp.getString("nomVille","Oujda"));
                editor.apply();

                Intent intent = new Intent(v.getContext(), ActivityListOuvrier.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return metiers.size();
    }

    //holder class for recycler view
    class ListMetiersViewHolder extends RecyclerView.ViewHolder
    {
        ImageView logoMetier;
        TextView nomMetier;
        public ListMetiersViewHolder(@NonNull View itemView) {
            super(itemView);
            logoMetier = itemView.findViewById(R.id.logoMetier);
            nomMetier = itemView.findViewById(R.id.nomMetier);
        }
    }
}
