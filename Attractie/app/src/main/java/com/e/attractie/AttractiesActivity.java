package com.e.attractie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AttractiesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    AttractieAdapter adapter;
    ArrayList<Attractie> list;
    private AttractieAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attracties);

        setOnClickListener();
        recyclerView = findViewById(R.id.attractieList);
        database = FirebaseDatabase.getInstance().getReference("Attracties");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new AttractieAdapter(this, list, listener);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot data : snapshot.getChildren()) {
                    Attractie attractie = data.getValue(Attractie.class);
                    list.add(attractie);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setOnClickListener() {
        listener = new AttractieAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                intent.putExtra("NaamPark", list.get(position).getNaamPark());
                intent.putExtra("Plaats", list.get(position).getPlaats());
                intent.putExtra("Img", list.get(position).getImg());
                startActivity(intent);
            }
        };
    }
}