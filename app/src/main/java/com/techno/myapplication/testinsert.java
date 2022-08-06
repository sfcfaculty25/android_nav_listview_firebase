package com.techno.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class testinsert extends AppCompatActivity
{
    EditText txtName,txtEmail,txtPass;
    Button btnRegister,btnSignin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testinsert);
        mAuth = FirebaseAuth.getInstance();

        txtName = findViewById(R.id.txtboxName);
        txtEmail = findViewById(R.id.txtboxEmail);
        txtPass = findViewById(R.id.txtboxPass);

        btnRegister = findViewById(R.id.btnRegister);
        btnSignin = findViewById(R.id.btnSignin);

    btnRegister.setOnClickListener(view->{
//        DatabaseReference db = FirebaseDatabase.getInstance("https://techwiz-35f0d-default-rtdb.firebaseio.com/").getReference("Data");
//        String id = System.currentTimeMillis()+"";
//
//        Data data = new Data(id,txtName.getText().toString(),txtEmail.getText().toString(),txtPass.getText().toString());
//        db.child(id).setValue(data);
//        Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
    });

    btnSignin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),txtPass.getText().toString()).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(testinsert.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    });



    }
}