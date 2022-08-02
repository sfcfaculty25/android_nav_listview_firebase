package com.techno.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Listview_Activity extends AppCompatActivity {

    ListView lv;
    ArrayList<User> data;
    DatabaseReference db ;
    EditText txt_search;
    Button btn_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        lv = findViewById(R.id.lv);
        txt_search = findViewById(R.id.txt_search);
        btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener(view->{
            search();
        });

        fetch();
    }


    public void fetch()
    {
        data = new ArrayList<>();
        db = FirebaseDatabase.getInstance("https://techwiz-35f0d-default-rtdb.firebaseio.com/").getReference("Person");
        db.addValueEventListener(new ValueEventListener() {
            @Override                        //datatype    variable
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot firedatabase:snapshot.getChildren())
                    {
                       User u1 = firedatabase.getValue(User.class);
                       data.add(u1);
                    }
                    ListviewAdapter adapter = new ListviewAdapter(data,Listview_Activity.this); //,Listview_Activity.this);
                    lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void search()
    {
        data = new ArrayList<>();
        db = FirebaseDatabase.getInstance("https://techwiz-35f0d-default-rtdb.firebaseio.com/").getReference("Person");
        db.addValueEventListener(new ValueEventListener() {
            @Override                        //datatype    variable
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot firedatabase:snapshot.getChildren())
                    {
                       User u1 = firedatabase.getValue(User.class);
                       if(u1.fname.contains(txt_search.getText().toString()) || u1.lname.contains(txt_search.getText().toString()))
                       {
                           data.add(u1);
                       }

                    }
                    ListviewAdapter adapter = new ListviewAdapter(data,Listview_Activity.this); //,Listview_Activity.this);
                    lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}