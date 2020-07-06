package com.example.project;
//import libraries
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


public class landing_login extends AppCompatActivity {
    TextView tv;
    Button btn, add, post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_login);

        //create a new intent
        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        tv=findViewById(R.id.welcomeBox);
        btn=findViewById(R.id.contacts);
        add = findViewById(R.id.add);
        post = findViewById(R.id.post);

        //set welcome words
        tv.setText("Welcome "+user_name+"!");

        final Intent contact=new Intent(landing_login.this, check_friend_request.class);

        //start a new activity when click button
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                contact.putExtra("User_Name",user_name);
                startActivity(contact);
                finish();
            }
        });
        final Intent post_page=new Intent(landing_login.this, CoachPost.class);
        post.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                post_page.putExtra("User_Name",user_name);
                startActivity(post_page);
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

