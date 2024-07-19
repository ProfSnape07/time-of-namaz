package com.example.timeofnamaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Readingdata2 extends AppCompatActivity implements ValueEventListener {

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference("M2");
    private final DatabaseReference thirddatabase = databaseReference.child("asr");
    private final DatabaseReference firstdatabase = databaseReference.child("fajr");
    private final DatabaseReference eightdatabase = databaseReference.child("sunset");
    private final DatabaseReference fifthdatabase = databaseReference.child("isha");
    private final DatabaseReference seconddatabse = databaseReference.child("zuhr");
    private final DatabaseReference sixthdatabase = databaseReference.child("juma");
    private final DatabaseReference fourthdatabase = databaseReference.child("maghrib");
    private final DatabaseReference ninedatabase = databaseReference.child("sehri");
    private final DatabaseReference seventhdatabase = databaseReference.child("sunrise");
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readingdata2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView1 = findViewById(R.id.textview11);
        textView2 = findViewById(R.id.textview35);
        textView3 = findViewById(R.id.textview32);
        textView4 = findViewById(R.id.textview29);
        textView5 = findViewById(R.id.textview26);
        textView6 = findViewById(R.id.textview23);
        textView7 = findViewById(R.id.textview20);
        textView8 = findViewById(R.id.textview17);
        textView9 = findViewById(R.id.textview14);
        textView = findViewById(R.id.chalo2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true) {
                    DisplayTrack();
                }
            }
        });

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        if (dataSnapshot.getValue(String.class) != null) {
            String key = dataSnapshot.getKey();
            if (key.equals("asr")) {
                String first1 = dataSnapshot.getValue(String.class);
                textView1.setText(first1);
            }
            if (key.equals("zuhr")) {
                String first2 = dataSnapshot.getValue(String.class);
                textView2.setText(first2);
            }
            if (key.equals("fajr")) {
                String first3 = dataSnapshot.getValue(String.class);
                textView3.setText(first3);
            }
            if (key.equals("maghrib")) {
                String first4 = dataSnapshot.getValue(String.class);
                textView4.setText(first4);
            }
            if (key.equals("isha")) {
                String first5 = dataSnapshot.getValue(String.class);
                textView5.setText(first5);
            }
            if (key.equals("juma")) {
                String first6 = dataSnapshot.getValue(String.class);
                textView6.setText(first6);
            }
            if (key.equals("sunrise")) {
                String first7 = dataSnapshot.getValue(String.class);
                textView7.setText(first7);
            }
            if (key.equals("sunset")) {
                String first8 = dataSnapshot.getValue(String.class);
                textView8.setText(first8);
            }
            if (key.equals("sehri")) {
                String first9 = dataSnapshot.getValue(String.class);
                textView9.setText(first9);
            }
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        firstdatabase.addValueEventListener(this);
        seconddatabse.addValueEventListener(this);
        thirddatabase.addValueEventListener(this);
        fourthdatabase.addValueEventListener(this);
        fifthdatabase.addValueEventListener(this);
        sixthdatabase.addValueEventListener(this);
        seventhdatabase.addValueEventListener(this);
        eightdatabase.addValueEventListener(this);
        ninedatabase.addValueEventListener(this);
    }

    private void DisplayTrack() {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir//Bilal Masjid");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}