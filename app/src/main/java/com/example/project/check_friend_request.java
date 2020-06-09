package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.remote.Datastore;



import java.util.HashMap;
import java.util.Map;

public class check_friend_request extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth RegisterUser = FirebaseAuth.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private TextView request_message;

    //button for accept and decline
    private Button b_accept;
    private Button b_decline;

    
    //button to go back to main page
    private Button b_to_main;
    private FirebaseAuth mAuth;
    String request_from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_friend_request);
        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        db = FirebaseFirestore.getInstance();
        RegisterUser = FirebaseAuth.getInstance();
        request_message = findViewById(R.id.message);
        b_accept= findViewById(R.id.button_accept);
        b_decline=findViewById(R.id.button_decline);
        b_to_main=findViewById(R.id.back_from_request);
        mAuth = FirebaseAuth.getInstance();
        final Intent i=new Intent(check_friend_request.this,landing_login.class);
        assert user_name != null;

        //search the database based on username
        DocumentReference noteRef= db.collection("Users").document(user_name);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //check the value of friend_request_from
                        request_from= documentSnapshot.getString("friend_request_from");
                        //if there is a request, display it to user
                        if(!request_from.isEmpty()) {
                            b_accept.setVisibility(View.VISIBLE);
                            b_decline.setVisibility(View.VISIBLE);
                            request_message.setText("Hello " + user_name + ", You have a new friend request from: " + request_from + ".");

                        }
                        //if there is no request, display a message to the user.
                        else{

                            request_message.setText("Hello " + user_name + ", You have no friend request now. " );

                        }

                    }

                });

        //once user clicks accept, the value of friend_request_from will be set to empty
        b_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick_accept(user_name);
                i.putExtra("User_Name",user_name);
                startActivity(i);
                finish();
            }
        });

        //once user clicks decline, the value of friend_request_from will be set to empty
        b_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick_decline(user_name);
                onclick_accept(user_name);
                i.putExtra("User_Name",user_name);
                startActivity(i);
                finish();
            }
        });

        //back to main page, and display a hello message to the user.
        b_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("User_Name",user_name);
                startActivity(i);

                finish();
            }
        });
    }

    //method for accept
    public void onclick_accept(String user_name){
        DocumentReference update=FirebaseFirestore.getInstance().collection("Users").document(user_name);
        Map<String, Object> map= new HashMap<>();
        map.put("friend_request_from", "");

        update.update(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(check_friend_request.this, "Request Accepted"
                                , Toast.LENGTH_SHORT).show();
                    }
                });

    }
    //method for decline
    public void onclick_decline(String user_name){
        DocumentReference update=FirebaseFirestore.getInstance().collection("User").document(user_name);
        Map<String, Object> map= new HashMap<>();
        map.put("friend_request_from", "");

        update.update(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(check_friend_request.this, "Request Declined"
                                , Toast.LENGTH_SHORT).show();
                    }
                });

    }



}