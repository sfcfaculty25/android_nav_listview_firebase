package com.techno.myapplication.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
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
import com.techno.myapplication.R;
import com.techno.myapplication.User;
import com.techno.myapplication.databinding.FragmentGalleryBinding;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {



DatabaseReference db;
ArrayList<User> data;
ListView lv;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
      View root = inflater.inflate(R.layout.fragment_gallery,container,false);
      lv = root.findViewById(R.id.lv_gallery);
      fetch();
        return root;
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
                ListviewAdapter adapter = new ListviewAdapter(data, getActivity()); //,Listview_Activity.this);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}