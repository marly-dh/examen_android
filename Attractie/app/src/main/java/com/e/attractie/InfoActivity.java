package com.e.attractie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class InfoActivity extends AppCompatActivity {
    DatabaseReference database;
    ImageView imageView;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        String naam = "Geen naam gevonden...";
        String plaats = "Geen plaats gevonden...";
        String imgUrl = "/";

        TextView tvName = findViewById(R.id.infoTvNaam);
        TextView tvPlaats = findViewById(R.id.infoTvPlaats);
        imageView = findViewById(R.id.infoImage);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            naam = extras.getString("NaamPark");
            plaats = extras.getString("Plaats");
            imgUrl = extras.getString("Img");
        }

        storageReference = FirebaseStorage.getInstance().getReference().child("images/"+imgUrl);

        tvName.setText(naam);
        tvPlaats.setText(plaats);

        try {
            final File localFile = File.createTempFile("attractie", "jpg");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            imageView.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}