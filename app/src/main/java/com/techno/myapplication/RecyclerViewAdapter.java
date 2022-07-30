package com.techno.myapplication;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.View_holder> {


    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class View_holder extends RecyclerView.ViewHolder
    {

        public View_holder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
