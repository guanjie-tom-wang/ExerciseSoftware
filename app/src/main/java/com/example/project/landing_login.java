package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


public class landing_login extends AppCompatActivity {
    TextView tv;
    Button btn, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_login);

        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        tv=findViewById(R.id.welcomeBox);
        btn=findViewById(R.id.contacts);
        add = findViewById(R.id.add);

        tv.setText("Welcome "+user_name+"!");

        final Intent contact=new Intent(landing_login.this, check_friend_request.class);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                contact.putExtra("User_Name",user_name);
                startActivity(contact);
                finish();
            }
        });

        final Intent addFreinds=new Intent(landing_login.this, search_and_send_page.class);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addFreinds.putExtra("User_Name",user_name);
                startActivity(addFreinds);
                finish();
            }
        });

    }
    }

