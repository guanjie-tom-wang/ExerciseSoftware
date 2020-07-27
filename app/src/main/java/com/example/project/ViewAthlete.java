package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAthlete extends AppCompatActivity {
    private RecyclerView view;
    private FirebaseFirestore db;

    private FirestoreRecyclerAdapter<User, ViewAthleteBinder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_athlete);

        db = FirebaseFirestore.getInstance();
        view = findViewById(R.id.al_list);
        view.setLayoutManager(new LinearLayoutManager(this));

        Query query = db.collection("Users").whereEqualTo("type", "athlete");
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

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }
}
