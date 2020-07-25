package com.example.project;
//import libraries
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class landing_login extends AppCompatActivity {
    TextView tv;
    Button btn, add,post;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //create a new intent
        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        final String user_type = intent.getStringExtra("User_Type");
        setTitle("Welcome "+user_type+ " !");

        tv=findViewById(R.id.welcomeBox);
        btn=findViewById(R.id.contacts);
        post = findViewById(R.id.post);

        //tv.setText("Welcome, "+user_type+" "+user_name+"!\n You can view your tasks from your coach by clicking Post.");
        final Intent contact=new Intent(landing_login.this, display_friend_list.class);


        //start a new activity when click button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.putExtra("User_Name", user_name);
                contact.putExtra("User_Type", user_type);
                startActivity(contact);
                finish();
            }
        });


        //create intents to different file for different type
        final Intent coachpost= new Intent(landing_login.this, AddPost.class);
        final Intent athletepost= new Intent(landing_login.this, ViewPost.class);
        final Intent userTypeWrong= new Intent(landing_login.this, userTypeWrong.class);
        //get user information
        DocumentReference ref = db.collection("Users").document(user_name + "");
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot result = task.getResult();// get the all value for this user
                    if (result != null && result.getString("type") != null) {
                        String str9 = result.getString("type");
                        assert str9 != null;
                        str9=str9.toLowerCase();//make all type tp lower case
                        if (str9.equals("coach")) {
                            tv.setText("Welcome, "+user_type+" "+user_name+"!\n You can send task to an athlete by clicking Post.");
                            post.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coachpost.putExtra("User_Name",user_name);
                                    coachpost.putExtra("User_Type",user_type);

                                    startActivity(coachpost);
                                    finish();
                                    Toast.makeText(landing_login.this, "Entry content which you want to post", Toast.LENGTH_LONG).show();
                                }
                            });
                        } else if (str9.equals("athlete")) {
                            tv.setText("Welcome, "+user_type+" "+user_name+"!\n You can view your tasks from your coach by clicking Post.");
                            post.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    athletepost.putExtra("User_Name",user_name);
                                    athletepost.putExtra("User_Type",user_type);
                                    startActivity(athletepost);
                                    finish();
                                    Toast.makeText(landing_login.this, "Your tasks are shown above ", Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            post.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(userTypeWrong);
                                    finish();
                                }
                            });
                        }
                    }
                }
            }
        });



    }
    }

