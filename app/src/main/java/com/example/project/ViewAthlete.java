package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.google.android.gms.tasks.Tasks.await;

public class ViewAthlete extends AppCompatActivity {
    private RecyclerView view;
    private FirebaseFirestore db;
    private Button back;

    private FirestoreRecyclerAdapter<User, ViewAthleteBinder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_athlete);

        db = FirebaseFirestore.getInstance();
        view = findViewById(R.id.al_list);
        back=findViewById(R.id.button7);
        view.setLayoutManager(new LinearLayoutManager(this));
        //back to main intent
        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        final String user_type = intent.getStringExtra("User_Type");
        final Intent i=new Intent(ViewAthlete.this, landing_login.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("User_Name",user_name);
                i.putExtra("User_Type",user_type);
                startActivity(i);
            }
        });
        ArrayList<String> friends = intent.getStringArrayListExtra("friends");
        if(friends != null && friends.size() != 0){
            Query query = db.collection("Users")
                    .whereIn("username", friends)
                    .whereEqualTo("type", "athlete");
            FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                    .setQuery(query, User.class)
                    .build();

            adapter = new FirestoreRecyclerAdapter<User, ViewAthleteBinder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull ViewAthleteBinder holder, int position, @NonNull User model) {
                    holder.bind(model);
                }

                @NonNull
                @Override
                public ViewAthleteBinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_allist,
                            parent, false);
                    return new ViewAthleteBinder(view, getApplicationContext());
                }
            };
            view.setAdapter(adapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter != null){
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }
}
