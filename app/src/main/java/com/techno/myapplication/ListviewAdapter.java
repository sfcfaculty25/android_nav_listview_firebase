package com.techno.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ListviewAdapter extends BaseAdapter {

    ArrayList<User> data;
    Context context;
    StorageReference ref;

    public ListviewAdapter(ArrayList<User> data,Context context) {
        this.data = data;
     this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View root, ViewGroup viewGroup) {

        DatabaseReference db = FirebaseDatabase.getInstance("https://techwiz-35f0d-default-rtdb.firebaseio.com/").getReference("Person");

    ref = FirebaseStorage.getInstance("gs://techwiz-35f0d.appspot.com/").getReference("images/"+data.get(position).getImgname());

        root = LayoutInflater.from(context).inflate(R.layout.listview_content,null);
        TextView txtFname = root.findViewById(R.id.txtFname);
        TextView txtLname = root.findViewById(R.id.txtLname);
        TextView txtContact = root.findViewById(R.id.txt_Contact);
        TextView txtEmail = root.findViewById(R.id.txtEmail);
        TextView txtPass = root.findViewById(R.id.txtPass);
        Button btnDel = root.findViewById(R.id.btnDel);
        Button btnUp = root.findViewById(R.id.btnUpdate);
        ImageView imgfetch = root.findViewById(R.id.fetch_img);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.child(data.get(position).getUserId()).removeValue();
                Intent i = new Intent(context,Listview_Activity.class);
                context.startActivity(i);
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,MainUpdate.class);
                i.putExtra("uid",data.get(position).getUserId());
                i.putExtra("fname",data.get(position).getFname());
                i.putExtra("lname", data.get(position).getLname());
                i.putExtra("email", data.get(position).getEmail());
                i.putExtra("pass", data.get(position).getPass());
                i.putExtra("contact", data.get(position).getContact());
                context.startActivity(i);
            }
        });

   //

        ref.getDownloadUrl().addOnSuccessListener((Activity) context, new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(imgfetch);

            }
        });

     //  txtFname.setText(""+ref.child(""+data.get(position).getImgname()).getDownloadUrl());
  txtFname.setText(data.get(position).getFname());
        txtLname.setText(data.get(position).getLname());
        txtContact.setText(data.get(position).getContact());
        txtEmail.setText(data.get(position).getEmail());
        txtPass.setText(data.get(position).getPass());

        return root;
    }
}
