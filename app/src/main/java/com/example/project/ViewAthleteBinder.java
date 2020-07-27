package com.example.project;

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

    public ViewAthleteBinder(@NonNull View itemView, final Context context) {
        super(itemView);

        name = itemView.findViewById(R.id.al_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AthleteDetail.class);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("username", name.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    public void bind(User user){
        name.setText(user.username);
    }
}
