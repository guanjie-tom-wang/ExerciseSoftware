package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ViewPost extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Post> list;
    FirebaseFirestore db;
    RecyclerView.LayoutManager layoutManager;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        final String user_type = intent.getStringExtra("User_Type");
        recyclerView = findViewById(R.id.view_rv);
        list = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        back=findViewById(R.id.button5);
        db.collection("Posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                Log.d("view", document.getId() + " => " + document.getData());
                                Map<String, Object> map = document.getData();
                                list.add(new Post((String)map.get("title"), (String)map.get("content")));
                            }
                            PostAdapter postAdapter = new PostAdapter(list);
                            recyclerView.setAdapter(postAdapter);
                        } else {
                            Log.w("view", "Error getting documents.", task.getException());
                        }
                    }
                });
        final Intent i=new Intent(ViewPost.this, landing_login.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("User_Name",user_name);
                i.putExtra("User_Type",user_type);
                startActivity(i);



            }
        });
    }
}