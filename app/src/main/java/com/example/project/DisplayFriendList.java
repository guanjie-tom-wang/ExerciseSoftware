package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DisplayFriendList extends AppCompatActivity {
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    RecyclerView rv;
    private Button add, check, back;
    ArrayList<String> f_list=new ArrayList<String>();
    ArrayList<String> friend_list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_friend_list);
        setTitle("Contacts");
        add= findViewById(R.id.add_friend);
        check= findViewById(R.id.check_friend);
        back=findViewById(R.id.back);
        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        final String user_type = intent.getStringExtra("User_Type");
        final Intent contact=new Intent(DisplayFriendList.this, SearchAndSend.class);

        //start a new activity when click button
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                contact.putExtra("User_Name",user_name);
                contact.putExtra("User_Type",user_type);
                contact.putExtra("User_Name",user_name);
                startActivity(contact);

            }
        });

        final Intent request=new Intent(DisplayFriendList.this, CheckFriendRequest.class);
        check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                request.putExtra("User_Name",user_name);
                request.putExtra("User_Type",user_type);
                request.putExtra("User_Name",user_name);
                startActivity(request);

            }
        });
        //back to main page, and display a hello message to the user.
        final Intent i=new Intent(DisplayFriendList.this, LandingLogin.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("User_Name",user_name);
                i.putExtra("User_Type",user_type);
                startActivity(i);



            }
        });
        //create a recycler view to display all user's friends.
        DocumentReference nRef= db.collection("Users").document(user_name);
        nRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                        //check the value of friend_request_from
                            friend_list = (ArrayList<String>) documentSnapshot.get("friend_list");

                            rv = (RecyclerView) findViewById(R.id.rv);
                            rv.setLayoutManager(new LinearLayoutManager(DisplayFriendList.this));

                        MyAdapter adapter = new MyAdapter(DisplayFriendList.this, friend_list,user_name,user_type);
                            rv.setAdapter(adapter);
                        }

                });


    }
}