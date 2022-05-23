package com.e.attractie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AttractieAdapter extends RecyclerView.Adapter<AttractieAdapter.MyViewHolder> {

    Context context;
    ArrayList<Attractie> list;
    RecyclerViewClickListener listener;


    public AttractieAdapter(Context context, ArrayList<Attractie> list, RecyclerViewClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Attractie attractie = list.get(position);
        holder.naam.setText(attractie.getNaamPark());
        holder.plaats.setText(attractie.getPlaats());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView naam, plaats;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            naam = itemView.findViewById(R.id.tvNaam);
            plaats = itemView.findViewById(R.id.tvPlaats);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

}
