package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckFriendRequest extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth RegisterUser = FirebaseAuth.getInstance();
    private TextView request_message;

    //button for accept and decline
    private Button b_accept;
    private Button b_decline;


    //button to go back to main page
    private Button b_to_main;
    ArrayList<String> friend_request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_friend_request);
        Intent intent = getIntent();
        setTitle("Friend request");
        final String user_name = intent.getStringExtra("User_Name");
        final String user_type = intent.getStringExtra("User_Type");

        db = FirebaseFirestore.getInstance();
        RegisterUser = FirebaseAuth.getInstance();
        request_message = findViewById(R.id.message);
        b_accept= findViewById(R.id.button_accept);
        b_decline=findViewById(R.id.button_decline);
        b_to_main=findViewById(R.id.back_from_request);
        final Intent i=new Intent(CheckFriendRequest.this, DisplayFriendList.class);
        //search the database based on username
        DocumentReference noteRef= db.collection("Users").document(user_name);

        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        friend_request= (ArrayList<String>) documentSnapshot.get("friend_request");
                        check_request(friend_request, user_name);
                    }

                });

        //once user clicks accept, the value of friend_request_from will be set to empty
        b_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick_accept(user_name);
                i.putExtra("User_Name",user_name);
                startActivity(i);

            }
        });

        //once user clicks decline, the value of friend_request_from will be set to empty
        b_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick_decline(user_name);
                i.putExtra("User_Name",user_name);
                startActivity(i);

            }
        });

        //back to main page, and display a hello message to the user.
        final Intent contact=new Intent(CheckFriendRequest.this, DisplayFriendList.class);

        b_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.putExtra("User_Name",user_name);
                contact.putExtra("User_Type",user_type);

                startActivity(contact);



            }
        });
    }

    //method for accept
    public void onclick_accept(String user_name){
        DocumentReference update=FirebaseFirestore.getInstance().collection("Users").document(user_name);

        update.update("friend_list", FieldValue.arrayUnion(friend_request.get(0)));
        Map<String, Object> map= new HashMap<>();
        map.put("friend_request", FieldValue.arrayRemove(friend_request.get(0)));
        update.update(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CheckFriendRequest.this, "Request Accepted"
                                , Toast.LENGTH_SHORT).show();
                    }
                });

    }
    //method for decline
    public void onclick_decline(String user_name) {
        DocumentReference update = FirebaseFirestore.getInstance().collection("Users").document(user_name);
        Map<String, Object> map = new HashMap<>();
        map.put("friend_request", FieldValue.arrayRemove(friend_request.get(0)));

        update.update(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CheckFriendRequest.this, "Request Declined"
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }
    //method for accept or delcine the request
    public void check_request(ArrayList<String> friend_request, String user_name){

            //if there is a request, display it to user
            if(friend_request.size()>0) {
                b_accept.setVisibility(View.VISIBLE);
                b_decline.setVisibility(View.VISIBLE);
                request_message.setText("Hello " + user_name + ", You have a new friend request from: " + friend_request.get(0) + ".");

            }
            //if there is no request, display a message to the user.
            else{

                request_message.setText("Hello " + user_name + ", You have no friend request now. " );

            }

        }



}
