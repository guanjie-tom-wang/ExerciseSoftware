package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
//this class is designed to display the detail of a specific friend
public class DetailsActivity extends AppCompatActivity {
    TextView name,u_age,u_email,u_profession;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String n, profession="", email="";
    Long age;
    private  CollectionReference noteRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Friend Personal Info");
        setContentView(R.layout.activity_details);
        name = findViewById(R.id.u_name);
        u_age = findViewById(R.id.u_age);
        u_profession= findViewById(R.id.u_profession);
        u_email = findViewById(R.id.u_email);
        n = getIntent().getStringExtra("name");
        name.setText("Name: " + n);
        DocumentReference noteRef= db.collection("Users").document(n);
        //Query the database according the name of the friend
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //get email address
                        email=(String) documentSnapshot.getData().get("emailAddress");
                        u_email.setText("Email address: "+email);

                        //get profession
                        profession=(String) documentSnapshot.getData().get("type");
                        u_profession.setText("Profession: "+profession);

                        //get age
                        age=(Long) documentSnapshot.getData().get("age");
                        u_age.setText("Age:"+age);
                    }
                });
        }

}