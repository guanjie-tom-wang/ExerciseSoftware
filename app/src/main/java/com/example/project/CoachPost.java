package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CoachPost extends AppCompatActivity {
    private Button return_to_main_button;
    private Button submit;
    private DatePicker simpleDatePicker;
    private EditText coachC;
    Post p;
    FirebaseFirestore db;
    FirebaseAuth user;
    List<String> friend_list = new ArrayList<>();

    // This is the activity for the coach role.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coachpost);
        setTitle("Add a Post");
        return_to_main_button=findViewById(R.id.return_to_main);
        submit = findViewById(R.id.post_c);
        coachC=findViewById(R.id.post_content);
        simpleDatePicker = findViewById(R.id.simpleDatePicker);
        //p= new Post();




        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance();

        // Get the current username
        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        final String user_type = intent.getStringExtra("User_Type");

        final Spinner mySpinner = (Spinner) findViewById(R.id.friends_spinner);
        final Intent i2=new Intent(CoachPost.this,landing_login.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the values for day of month , month and year from a date picker , and content
                int day = simpleDatePicker.getDayOfMonth();
                int month = simpleDatePicker.getMonth();
                int year = simpleDatePicker.getYear();
                String content =  coachC.getText().toString();
                //p.setAuthor(user_name);
                //p.setContent(content);
                //p.setDay(day);
                //p.setMonth(month);
                //p.setYear(year);

                db.collection("Posts").add(p);
                db.collection("Users").document(user_name).update("posts", FieldValue.arrayUnion(p));
                System.out.println(user_name+" "+day+" "+content);
                i2.putExtra("User_Name",user_name);
                i2.putExtra("User_Type",user_type);
                startActivity(i2);
            }
        });
        final Intent i=new Intent(CoachPost.this,landing_login.class);

        return_to_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("User_Name",user_name);
                i.putExtra("User_Type",user_type);
                startActivity(i);

            }
        });

        DocumentReference current_user= db.collection("Users").document(user_name);

        // Use a spinner to show all the friends of the current user in a dropdown list.
        current_user.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        friend_list= (ArrayList<String>) documentSnapshot.get("friend_list");
                        if(friend_list.size() > 0 && !friend_list.get(0).isEmpty() ){
                            final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CoachPost.this, android.R.layout.simple_list_item_1,
                                    friend_list);
                            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            mySpinner.setAdapter(myAdapter);
                        }
                        // If there is no friend yet, then give user a warning message.
                        else{
                            List<String> no_friend_list= new ArrayList<>();
                            no_friend_list.add("You have no friends yet, go add one!");
                            final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CoachPost.this, android.R.layout.simple_list_item_1,
                                    no_friend_list);
                            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            mySpinner.setAdapter(myAdapter);
                        }

                    }

                });
    }


}
