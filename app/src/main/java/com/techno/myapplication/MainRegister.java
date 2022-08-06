package com.techno.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainRegister extends AppCompatActivity {

    EditText txtName,txtLname,txtEmail,txtPass,txtContact;
    Button btnReg, btnUpload,next_login;

    FirebaseAuth mAuth;
    DatabaseReference db;
    Uri imguri;
    ImageView imgUpload;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtName = findViewById(R.id.et_fname);
        txtLname = findViewById(R.id.et_lname);
        txtEmail = findViewById(R.id.et_email);
        txtContact = findViewById(R.id.et_contact);
        txtPass = findViewById(R.id.et_pass);
        btnReg = findViewById(R.id.btnReg);
        imgUpload = findViewById(R.id.imageView2);
        btnUpload = findViewById(R.id.file_upload);
        next_login = findViewById(R.id.next_login);
        ref = FirebaseStorage.getInstance("gs://techwiztesting.appspot.com").getReference("images/");

        next_login.setOnClickListener(view -> {
            Intent i = new Intent(MainRegister.this,MainLogin.class);
            startActivity(i);
        });

        btnUpload.setOnClickListener(view->{
            choosefile();
        });

        mAuth = FirebaseAuth.getInstance();

            btnReg.setOnClickListener(view->{
            mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),txtPass.getText().toString());

            register_data();

     //       db = FirebaseDatabase.getInstance("https://techwiztesting-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Person");
//            String id = System.currentTimeMillis()+"";
//            User person =new User(id,txtName.getText().toString(),txtLname.getText().toString(),txtEmail.getText().toString(),txtPass.getText().toString(),txtContact.getText().toString(),id+"."+getextension(imguri));
//            db.child(id).setValue(person);
//            Toast.makeText(MainRegister.this, "Registered", Toast.LENGTH_SHORT).show();

            txtName.setText("");
            txtLname.setText("");
            txtEmail.setText("");
            txtContact.setText("");
            txtPass.setText("");
        });
    }

    public void register_data()
    {
        DatabaseReference db = FirebaseDatabase.getInstance("https://techwiztesting-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Person");
        String id = System.currentTimeMillis()+"";
        User person =new User(id,txtName.getText().toString(),txtLname.getText().toString(),txtEmail.getText().toString(),txtPass.getText().toString(),txtContact.getText().toString(),id+"."+getextension(imguri));
        db.child(id).setValue(person);
        Toast.makeText(MainRegister.this, "inserted", Toast.LENGTH_SHORT).show();

        StorageReference imgadd = ref.child(id+"."+getextension(imguri));
        imgadd.putFile(imguri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainRegister.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getextension(Uri uri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( resultCode == RESULT_OK && data!=null)
        {
            imguri = data.getData();
            imgUpload.setImageURI(imguri);
        }
        else
        {
            Toast.makeText(MainRegister.this, "Null image", Toast.LENGTH_SHORT).show();
        }
    }

    public void choosefile()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

}