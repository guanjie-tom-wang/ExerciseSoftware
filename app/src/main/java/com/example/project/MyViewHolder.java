package com.example.project;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    RelativeLayout relative;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView) itemView.findViewById(R.id.text_view_name);
        relative=itemView.findViewById(R.id.L1);
    }
}
