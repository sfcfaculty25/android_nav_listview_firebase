package com.techno.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techno.myapplication.Listview_Activity;
import com.techno.myapplication.R;
import com.techno.myapplication.User;

public class Contact extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contact,container,false);

        EditText txtName,txtLname,txtEmail,txtPass,txtContact;
        txtName = root.findViewById(R.id.edit_fname);
        txtLname = root.findViewById(R.id.edit_lname);
        txtEmail = root.findViewById(R.id.edit_email);
        txtContact = root.findViewById(R.id.edit_contact);
        txtPass = root.findViewById(R.id.edit_pass);
        Button btnAdd = root.findViewById(R.id.btnAdd);
        Button btnshow = root.findViewById(R.id.btn_show);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference db = FirebaseDatabase.getInstance("https://techwiz-35f0d-default-rtdb.firebaseio.com/").getReference("Person");
                String id = System.currentTimeMillis()+"";
                User person =new User(id,txtName.getText().toString(),txtLname.getText().toString(),txtEmail.getText().toString(),txtPass.getText().toString(),txtContact.getText().toString());
                db.child(id).setValue(person);
                Toast.makeText(container.getContext(), "inserted", Toast.LENGTH_SHORT).show();

                txtName.setText("");
                txtLname.setText("");
                txtEmail.setText("");
                txtContact.setText("");
                txtPass.setText("");
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
}
