package com.techno.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListviewAdapter extends BaseAdapter {

    ArrayList<User> data;
    Context context;


    public ListviewAdapter(ArrayList<User> data,Context context) {
        this.data = data;
     this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
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

       root = LayoutInflater.from(context).inflate(R.layout.listview_content,null);
        TextView txtFname = root.findViewById(R.id.txtFname);
        TextView txtLname = root.findViewById(R.id.txtLname);
        TextView txtContact = root.findViewById(R.id.txt_Contact);
        TextView txtEmail = root.findViewById(R.id.txtEmail);
        TextView txtPass = root.findViewById(R.id.txtPass);

        txtFname.setText(data.get(position).getFname());
        txtLname.setText(data.get(position).getLname());
        txtContact.setText(data.get(position).getContact());
        txtEmail.setText(data.get(position).getEmail());
        txtPass.setText(data.get(position).getPass());

        return root;
    }
}
