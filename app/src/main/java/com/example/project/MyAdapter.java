package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context c;
    ArrayList<String> friend_list;
    String user_name;
    String user_type;
    public MyAdapter(Context c, ArrayList<String> list) {
        this.c = c;
        this.friend_list = list;
    }
    public MyAdapter(Context c, ArrayList<String> list,String user_name,String user_type) {
        this.c = c;
        this.friend_list = list;
        this.user_name=user_name;
        this.user_type=user_type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(c).inflate(R.layout.model, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(friend_list.get(position));
        //make the recycler view clickable
        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(c, DetailsActivity.class);
                intent.putExtra("name",friend_list.get(position));
                intent.putExtra("User_Name",user_name);
                intent.putExtra("User_Type",user_type);
                intent.putStringArrayListExtra("list",friend_list);
                intent.putExtra("number",position);
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return friend_list.size();
    }


}