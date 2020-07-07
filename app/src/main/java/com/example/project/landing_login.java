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

        //create a new intent
        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        tv=findViewById(R.id.welcomeBox);
        btn=findViewById(R.id.contacts);
        add = findViewById(R.id.add);
        post = findViewById(R.id.post);


        //set welcome words
        tv.setText("Welcome " + user_name + "!");

        final Intent contact = new Intent(landing_login.this, check_friend_request.class);
        //start a new activity when click button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.putExtra("User_Name", user_name);
                startActivity(contact);
                finish();
            }
        });

        final Intent addFreinds = new Intent(landing_login.this, search_and_send_page.class);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFreinds.putExtra("User_Name", user_name);
                startActivity(addFreinds);
                finish();
            }
        });
        //create intents to different file for different type
        final Intent coachpost= new Intent(landing_login.this, CoachPost.class);
        final Intent athletepost= new Intent(landing_login.this, AthletePost.class);
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
                            post.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coachpost.putExtra("User_Name",user_name);
                                    startActivity(coachpost);
                                    finish();
                                    Toast.makeText(landing_login.this, "Entry content which you want to post", Toast.LENGTH_LONG).show();
                                }
                            });
                        } else if (str9.equals("athlete")) {
                            post.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(athletepost);
                                    finish();
                                    Toast.makeText(landing_login.this, "Entry content which you want to post", Toast.LENGTH_LONG).show();
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

