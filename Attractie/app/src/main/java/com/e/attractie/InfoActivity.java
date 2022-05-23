package com.e.attractie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        String naam = "Geen naam gevonden...";
        String plaats = "Geen plaats gevonden...";
        String imgUrl = "/";

        TextView tvName = findViewById(R.id.infoTvNaam);
        TextView tvPlaats = findViewById(R.id.infoTvPlaats);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            naam = extras.getString("NaamPark");
            plaats = extras.getString("Plaats");
            imgUrl = extras.getString("Img");
        }

        tvName.setText(naam);
        tvPlaats.setText(plaats);
    }
}