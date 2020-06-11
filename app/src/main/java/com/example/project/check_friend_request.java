package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class check_friend_request extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth RegisterUser = FirebaseAuth.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String curr_user= user.getDisplayName();
    private DocumentReference noteRef= db.collection("Users").document("Rui");


    private TextView request_message;
    private Button b_accept;
    private Button b_decline;
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
        mAuth = FirebaseAuth.getInstance();
        noteRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
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
    }
    /*
    public void check(View v){
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        request_from= documentSnapshot.getString("friend_request_from");
                        request_message.setText("You have a request from: "+request_from+".");

                    }

                });

    }*/



}