package com.example.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AthleteDetail extends AppCompatActivity {

    private FirebaseFirestore db;

    private TextView name, age, height, weight;

    private DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_athlete_detail);
        final String userName = getIntent().getStringExtra("username");

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        height = findViewById(R.id.al_height);
        weight = findViewById(R.id.al_weight);



        docRef = db.collection("Users").document(userName);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document != null){
                        name.setText("Username: " + userName);
                        age.setText("Age: " + String.valueOf(document.get("age")));
                        height.setText("Height: " + String.valueOf( document.get("height")));
                        weight.setText("Weight: " + String.valueOf( document.get("weight")));
                    }

                }

            }
        });
    }
}
