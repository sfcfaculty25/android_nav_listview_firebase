package com.techno.myapplication.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techno.myapplication.ListviewAdapter;
import com.techno.myapplication.Listview_Activity;
import com.techno.myapplication.MainActivity2;
import com.techno.myapplication.R;
import com.techno.myapplication.User;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ListView lv;
    ArrayList<User> data;
   // DatabaseReference db ;
    ListviewAdapter adapter;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
    //    fetch();
        root = inflater.inflate(R.layout.fragment_home,container,false);
  //fetch();
//        ListviewAdapter adapter = new ListviewAdapter(data,getContext()); //,Listview_Activity.this);
//        lv.setAdapter(adapter);
        return root;
    }

//    public void fetch()
//    {
//        data = new ArrayList<>();
//
//        DatabaseReference  db = FirebaseDatabase.getInstance("https://techwiz-35f0d-default-rtdb.firebaseio.com/").getReference("Person");
//        db.addValueEventListener(new ValueEventListener() {
//            @Override                        //datatype    variable
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for(DataSnapshot firedata:snapshot.getChildren())
//                {
//                    User u1 = firedata.getValue(User.class);
//                    data.add(u1);
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }


}