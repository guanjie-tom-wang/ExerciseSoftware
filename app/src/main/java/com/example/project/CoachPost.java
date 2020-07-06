package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CoachPost extends AppCompatActivity {
    private TextView tvCoach;//
    private EditText edCoach;
    private Button bCoach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coachpost);
        tvCoach=findViewById(R.id.textView);
        tvCoach.setText("Entry content which you want to post");
        edCoach=findViewById(R.id.editTextTextPersonName2);
        edCoach.setText("");
        bCoach=findViewById(R.id.button8);
        bCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
