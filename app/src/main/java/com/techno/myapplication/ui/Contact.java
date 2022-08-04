package com.techno.myapplication.ui;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.techno.myapplication.Listview_Activity;
import com.techno.myapplication.R;
import com.techno.myapplication.User;

public class Contact extends Fragment {
    Uri imguri;
    ImageView imgUpload;
    StorageReference ref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contact,container,false);
        ref = FirebaseStorage.getInstance("gs://techwiz-35f0d.appspot.com/").getReference("images/");

        EditText txtName,txtLname,txtEmail,txtPass,txtContact;
        txtName = root.findViewById(R.id.edit_fname);
        txtLname = root.findViewById(R.id.edit_lname);
        txtEmail = root.findViewById(R.id.edit_email);
        txtContact = root.findViewById(R.id.edit_contact);
        txtPass = root.findViewById(R.id.edit_pass);
        Button btnAdd = root.findViewById(R.id.btnAdd);
        Button btnshow = root.findViewById(R.id.btn_show);
        Button btnUpload = root.findViewById(R.id.btn_upload);
        imgUpload = root.findViewById(R.id.img_upload);



        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                choosefile();
            }
        });



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference db = FirebaseDatabase.getInstance("https://techwiz-35f0d-default-rtdb.firebaseio.com/").getReference("Person");
                String id = System.currentTimeMillis()+"";
                User person =new User(id,txtName.getText().toString(),txtLname.getText().toString(),txtEmail.getText().toString(),txtPass.getText().toString(),txtContact.getText().toString(),id+"."+getextension(imguri));
                db.child(id).setValue(person);
                Toast.makeText(container.getContext(), "inserted", Toast.LENGTH_SHORT).show();

                 StorageReference imgadd = ref.child(id+"."+getextension(imguri));
                  imgadd.putFile(imguri).addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                          Toast.makeText(container.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                      }
                  });


                txtName.setText("");
                txtLname.setText("");
                txtEmail.setText("");
                txtContact.setText("");
                txtPass.setText("");
                imgUpload.setImageResource(R.drawable.ic_menu_camera);
            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(container.getContext(), Listview_Activity.class);
                startActivity(i);
            }
        });


        return root;
    }


    public String getextension(Uri uri)
    {
        ContentResolver cr = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    public void choosefile()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
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
            Toast.makeText(getContext(), "Null image", Toast.LENGTH_SHORT).show();
        }
    }



}
