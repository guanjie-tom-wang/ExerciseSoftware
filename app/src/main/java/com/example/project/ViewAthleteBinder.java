package com.example.project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.core.content.ContextCompat.startActivity;

public class ViewAthleteBinder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView steps;

    public ViewAthleteBinder(@NonNull View itemView, final Context context) {
        super(itemView);

        name = itemView.findViewById(R.id.al_name);
        steps = itemView.findViewById(R.id.al_step);
    }

    @SuppressLint("SetTextI18n")
    public void bind(User user){
        name.setText("User name: " + user.username);
        steps.setText("Steps: " + String.valueOf(user.stepnumber));
    }
}
