package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.regex.Pattern;

public class landing_login extends AppCompatActivity {
    TextView tv;
    Button btn;
    String username;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_login);
        tv=findViewById(R.id.welcomeBox);
        btn=findViewById(R.id.contacts);

        tv.setText("Welcome!");

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i=new Intent(landing_login.this,check_friend_request.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }

