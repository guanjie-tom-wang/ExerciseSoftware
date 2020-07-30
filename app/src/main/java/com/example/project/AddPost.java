package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddPost extends AppCompatActivity {
    EditText title;
    EditText content;
    Button post;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        title = findViewById(R.id.post_title);
        content = findViewById(R.id.post_content);
        post = findViewById(R.id.post_button);
        db = FirebaseFirestore.getInstance();
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_str = title.getText().toString();
                String content_str = content.getText().toString();
                if(title_str.equals("")){
                    title.setError("Title can't be empty");
                    return;
                }
                if(content_str.equals("")){
                    content.setError("Content can't be empty");
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("title", title_str);
                map.put("content", content_str);
                db.collection("Posts")
                        .add(map)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("post", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(getApplicationContext(),"Post successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddPost.this, LandingLogin.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("post", "Error adding document", e);
                                Toast.makeText(getApplicationContext(),"Post failed",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}