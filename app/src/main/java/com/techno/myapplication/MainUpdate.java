package com.techno.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainUpdate extends AppCompatActivity {

    EditText up_fname,up_lname,up_email,up_pass,up_contact;
    Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        up_fname = findViewById(R.id.up_fname);
        up_lname = findViewById(R.id.up_lname);
        up_email = findViewById(R.id.up_email);
        up_pass = findViewById(R.id.up_pass);
        up_contact = findViewById(R.id.up_contact);
        btn_save = findViewById(R.id.btn_save);

        String id = intent.getStringExtra("uid");
        up_fname.setText(intent.getStringExtra("fname"));
        up_lname.setText(intent.getStringExtra("lname"));
        up_email.setText(intent.getStringExtra("email"));
        up_pass.setText(intent.getStringExtra("pass"));
        up_contact.setText(intent.getStringExtra("contact"));

        btn_save.setOnClickListener(view->{
            DatabaseReference db = FirebaseDatabase.getInstance("https://techwiz-35f0d-default-rtdb.firebaseio.com/").getReference("Person");
           User person = new User(id,up_fname.getText().toString(),up_lname.getText().toString(),up_email.getText().toString(),up_pass.getText().toString(),up_contact.getText().toString(),"");
            db.child(id).setValue(person).addOnSuccessListener(MainUpdate.this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                Toast.makeText(MainUpdate.this, "updated", Toast.LENGTH_SHORT).show();
               Intent i = new Intent(MainUpdate.this, Listview_Activity.class);
               startActivity(i);
                }
            });
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        onBackPressed();
    }
}