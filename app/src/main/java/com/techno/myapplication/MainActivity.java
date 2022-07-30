package com.techno.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference db = FirebaseDatabase.getInstance("https://techwiztesting-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Person");
       String id = System.currentTimeMillis()+"";
        db.child(id).child("name").setValue("smith");

    }
}