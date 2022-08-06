package com.techno.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainLogin extends AppCompatActivity {
    EditText txtEmail,txtPass;
    Button btnLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail = findViewById(R.id.uemail);
        txtPass = findViewById(R.id.upass);
        btnLogin = findViewById(R.id.btnLog);
        mAuth = FirebaseAuth.getInstance();
        SharedPreferences sp = getSharedPreferences("SP",MODE_PRIVATE);
        btnLogin.setOnClickListener(view->{
            SharedPreferences.Editor editor= sp.edit();

            mAuth.signInWithEmailAndPassword(txtEmail.getText().toString(),txtPass.getText().toString()).addOnSuccessListener(MainLogin.this, new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    editor.putString("useremail",txtEmail.getText().toString());
                    editor.putString("pass",txtPass.getText().toString());
                    editor.putString("userid",authResult.getUser().getUid());
                    editor.commit();
                    Toast.makeText(MainLogin.this, "logged In", Toast.LENGTH_SHORT).show();
                }
            });


        });

    }
}