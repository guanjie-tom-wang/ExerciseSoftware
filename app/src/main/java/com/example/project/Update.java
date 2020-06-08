package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.remote.Datastore;

import java.util.HashMap;
import java.util.Map;


public class Update extends AppCompatActivity {
    //get the FirebaseFirestore instance
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth RegisterUser = FirebaseAuth.getInstance();
    //create variable to get inputs which are from andriod app page
    private TextView error_msg;
    private EditText usr;
    private EditText email;
    private EditText pass;
    private EditText  age;
    private EditText weight;
    private EditText height;
    private EditText role;
    private EditText phone;
    private EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //make alert user to input right things
        Toast.makeText(Update.this,"Please fill all of blanks and make sure type correct email address and user name",Toast.LENGTH_LONG).show();
        //initialize database
        db = FirebaseFirestore.getInstance();
        RegisterUser = FirebaseAuth.getInstance();
        //get inputs from app page
        error_msg = findViewById(R.id.errorInfo);
        phone = findViewById(R.id.telnumber);
        address = findViewById(R.id.address);
        usr = findViewById(R.id.username);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        role = findViewById(R.id.role);
        Button update = findViewById(R.id.Update);
        TextView return_to_main = findViewById(R.id.log);
        //call update() method for update database information
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                update();
            }
        });
        //back to the main page
        final Intent intent=new Intent(this,MainActivity.class);
        return_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
    }
    public void update() {
        FirebaseApp.initializeApp(this);
        //convert all inputs to string for next step
        final String username = usr.getText().toString();
        final String roleText = role.getText().toString();
        final String passText = pass.getText().toString();
        final String ageNum = age.getText().toString();
        final String weightNum = weight.getText().toString();
        final String heightNum = height.getText().toString();
        final String phoneNumber = phone.getText().toString();
        final String addressText = address.getText().toString();
        final String email_address = email.getText().toString();
        //check whether inputs are meet requirements
        String regex_email = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String regex_digit = "^\\d+";
        String error = "";
        if (passText.isEmpty()) {
            error = error + "- Invalid password!\n";
        }
        if (roleText.isEmpty()) {
            error = error + "- Invalid role!\n";
        }
        if (!ageNum.matches((regex_digit))) {
            error = error + "- Invalid age!\n";
        }
        if (!weightNum.matches((regex_digit))) {
            error = error + "- Invalid weight!\n";
        }
        if (!heightNum.matches((regex_digit))) {
            error = error + "- Invalid height!\n";
        }
        if (username.isEmpty() || username.charAt(0) == ' ' || username.charAt(username.length() - 1) == ' ') {
            error = error + "- Invalid username!\n";

        }
        if (!email_address.matches(regex_email)) {
            error = error + "- Invalid email format!\n";
        }

        if (!error.isEmpty()) {
            error_msg.setVisibility(View.VISIBLE);
            error_msg.setText(error);
            error_msg.setTextColor(Color.RED);
        } else {
            //if it meet all the requirement
            DocumentReference ref = db.collection("Users").document(username + "");// to locate the special position of database for update
            //addOnCompleteListener--- check if this user is registered in the database and check if input email address and username are same as old
            ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot result = task.getResult();// get the all value for this user
                        DocumentReference ref = db.collection("Users").document(username + "");
                        if (result != null && result.getString("emailAddress")!=null) {
                            String str9 = result.getString("emailAddress");
                            if (email_address.equals(str9)) {
                                //create a new user object for this user and update information use latest input
                                User userinfo;
                                userinfo = new User(username + "", phoneNumber + "", addressText + "," +
                                        "", email_address + "", passText + "", roleText + "", Integer.parseInt(heightNum),
                                        Integer.parseInt(weightNum), Integer.parseInt(ageNum));
                                ref.set(userinfo);
                                Toast.makeText(Update.this, "Update Successful", Toast.LENGTH_LONG).show();//tell user update successful
                            } else {
                                Toast.makeText(Update.this, "Enter correct email address", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(Update.this, "Please register first", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

        }

    }
}
