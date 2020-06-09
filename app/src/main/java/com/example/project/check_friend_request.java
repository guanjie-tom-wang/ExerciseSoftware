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
    private Button b_accept;
    private Button b_decline;
    private Button b_to_main;
    private FirebaseAuth mAuth;
    String request_from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_friend_request);

        db = FirebaseFirestore.getInstance();
        RegisterUser = FirebaseAuth.getInstance();
        request_message = findViewById(R.id.message);
        b_accept= findViewById(R.id.button_accept);
        b_decline=findViewById(R.id.button_decline);
        b_to_main=findViewById(R.id.back_from_request);
        mAuth = FirebaseAuth.getInstance();
   

        DocumentReference noteRef= db.collection("Users").document("Rui");

        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String curr_user=documentSnapshot.getString("username");
                        request_from= documentSnapshot.getString("friend_request_from");
                        if(!request_from.isEmpty()) {
                            b_accept.setVisibility(View.VISIBLE);
                            b_decline.setVisibility(View.VISIBLE);
                            request_message.setText("Hello " + curr_user + ", You have a new friend request from: " + request_from + ".");

                        }
                        else{

                            request_message.setText("Hello " + curr_user + ", You have no friend request now. " );

                        }

                    }

                });
        b_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick_accept();
                Intent i=new Intent(check_friend_request.this,landing_login.class);
                startActivity(i);
                finish();
            }
        });
        b_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick_decline();
                onclick_accept();
                Intent i=new Intent(check_friend_request.this,landing_login.class);
                startActivity(i);
                finish();
            }
        });
        b_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(check_friend_request.this,landing_login.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void onclick_accept(){
        DocumentReference update=FirebaseFirestore.getInstance().collection("Users").document("Rui");
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
    public void onclick_decline(){
        DocumentReference update=FirebaseFirestore.getInstance().collection("User").document("Rui");
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