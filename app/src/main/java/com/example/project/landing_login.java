package com.example.project;
//import libraries
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class landing_login extends AppCompatActivity {
    TextView tv;
    Button btn, post, steps, athlete;
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
        steps = findViewById(R.id.steps);
        athlete = findViewById(R.id.athletes);

        if(user_type.equals("athlete")){
            steps.setText("Start training");
            athlete.setVisibility(View.INVISIBLE);
        }else{
            steps.setText("View athlete data");
        }

        final Intent contact=new Intent(landing_login.this, DisplayFriendList.class);


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

        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_type.equals("athlete")){
                    Intent step = new Intent(landing_login.this, StepCounter.class);
                    step.putExtra("User_Name", user_name);
                    step.putExtra("User_Type", user_type);
                    startActivity(step);
                }else{
                    db.collection("Users").document(user_name).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Intent step = new Intent(getApplicationContext(), ViewAthlete.class);
                            step.putExtra("User_Name", user_name);
                            step.putExtra("User_Type", user_type);

                            User user = documentSnapshot.toObject(User.class);
                            ArrayList<String> friend_list = new ArrayList<>(user.friend_list);
                            step.putStringArrayListExtra("friends", friend_list);
                            startActivity(step);
                            finish();
                        }
                    });
                }

            }
        });
       final Intent athleteView=new Intent(landing_login.this, ViewAthlete.class);
        athlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                athleteView.putExtra("User_Name", user_name);
                athleteView.putExtra("User_Type", user_type);
                final ArrayList<String> friends = new ArrayList<>();

                db.collection("Users").document(user_name).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                User user = documentSnapshot.toObject(User.class);
                                friends.addAll(user.friend_list);
                                athleteView.putStringArrayListExtra("friends", friends);
                                startActivity(athleteView);
                                finish();
                            }
                        });


            }
        });
        //create intents to different file for different type
        final Intent coachpost= new Intent(landing_login.this, CoachPost.class);
        final Intent athletepost= new Intent(landing_login.this, ViewPost.class);
        final Intent userTypeWrong= new Intent(landing_login.this, UserTypeWrong.class);
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

