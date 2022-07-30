package com.techno.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        lv = findViewById(R.id.lv);
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


}