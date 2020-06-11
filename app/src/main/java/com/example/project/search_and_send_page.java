package com.example.project;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import android.util.Log;




public class search_and_send_page extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

     // private DocumentReference noteRef= db.collection("Users").document("rui");
    private  CollectionReference noteRef;
   // private Query query;
    private Button a_add;
    private Button a_check;
    private TextView input_email;
    private Boolean check_email=false;
    private String username_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_friend_request);
        db = FirebaseFirestore.getInstance();

        input_email = findViewById(R.id.addEmail1);
        a_add = findViewById(R.id.button3);
        a_check = findViewById(R.id.button4);

        Boolean email_checking;


        a_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate_email();
            }

        });


        a_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                send_request();
            }
        });
    }

    public void validate_email() {
        if (input_email.getText().toString().isEmpty()) {
            Toast.makeText(search_and_send_page.this, "error message, empty input", Toast.LENGTH_LONG).show();
        }
        else {
            noteRef = db.collection("Users");
            Query query = noteRef.whereEqualTo("emailAddress", input_email.getText().toString());
            query.get().addon
            if (query == null) {
                Toast.makeText(search_and_send_page.this, "there is no email in your group, please input", Toast.LENGTH_LONG).show();
            } else {
                check_email=true;

                Toast.makeText(search_and_send_page.this, "confirm:" + query, Toast.LENGTH_LONG).show();


            }
        }



        }



    public void send_request(){
        if( check_email) {
            DocumentReference ref = db.collection("Users").document("");
            ref.update("friend_request_from", input_email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(search_and_send_page.this, "DocumentSnapshot successfully sending!", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(search_and_send_page.this, "Error sending", Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            Toast.makeText(search_and_send_page.this, "please input the correct email and check it" , Toast.LENGTH_LONG).show();
        }
    }
}