package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class search_and_send_page extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private  CollectionReference noteRef;
    private Button a_add,a_check, back;
    private TextView input_email;
    private Boolean check_email=false;
    private String username_get;
    ArrayList<String> friend_request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_and_send_page);
        db = FirebaseFirestore.getInstance();
        input_email = findViewById(R.id.addEmail1);
        a_add = findViewById(R.id.button3);
        a_check = findViewById(R.id.button4);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        final Intent i=new Intent(search_and_send_page.this,landing_login.class);
        final Intent contact=new Intent(search_and_send_page.this,display_friend_list.class);

        final String user_name = intent.getStringExtra("User_Name");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.putExtra("User_Name",user_name);
                startActivity(contact);
                finish();
            }
        });

        // Check the email
        a_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate_email();
            }

        });

        // Send request
        a_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                send_request(user_name);
            }
        });
    }

    public void validate_email() {
        if (input_email.getText().toString().isEmpty()) {
            Toast.makeText(search_and_send_page.this, "Please enter something", Toast.LENGTH_LONG).show();
        }
        else {
            noteRef = db.collection("Users");

            //query the database, find the user's username according to email address entered.
            Query query = noteRef.whereEqualTo("emailAddress", input_email.getText().toString());

            if (query == null) {
                Toast.makeText(search_and_send_page.this, "there is no email in your group, please input", Toast.LENGTH_LONG).show();
            } else {
                check_email=true;
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //get the username from email
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                username_get = (String) document.getData().get("username");
                                Toast.makeText(search_and_send_page.this, "Email address belongs to: " +username_get , Toast.LENGTH_LONG).show();


                            }

                        }
        }
        });

}}}

    public void send_request(final String user_name){
        if( check_email) {
            final Query query= db.collection("Users").whereEqualTo("emailAddress",input_email.getText().toString());

            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            DocumentReference ref= db.collection("Users").document(username_get+"");
                            ref.update("friend_request", FieldValue.arrayUnion(user_name)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(search_and_send_page.this, "Request sent!", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(search_and_send_page.this, "Error! Something goes wrong...", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }
            });

        }
        else{
            Toast.makeText(search_and_send_page.this, "Please input the correct email and check it" , Toast.LENGTH_LONG).show();
        }
    }
}