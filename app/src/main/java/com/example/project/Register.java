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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class Register extends AppCompatActivity {

    // Set all objects that will be used
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth registerUser = FirebaseAuth.getInstance();
    ValidateData validator = new ValidateData();
    private TextView errorMsg;
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
        setContentView(R.layout.activity_register);

        // Initialize variables
        db = FirebaseFirestore.getInstance();
        registerUser = FirebaseAuth.getInstance();
        errorMsg = findViewById(R.id.errorInfo);
        phone = findViewById(R.id.telnumber);
        address = findViewById(R.id.address);
        usr = findViewById(R.id.username);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        role = findViewById(R.id.role);
        Button register_button = findViewById(R.id.register);
        TextView return_to_main = findViewById(R.id.log);


        // When user click the button, start register
        register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                validate();
            }

        });
        final Intent intent=new Intent(this,LoginActivity.class);
        return_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });

    }

    // This method is used to validate the information user enters when registering
    public void validate() {

        FirebaseApp.initializeApp(this);

        // Get the information user enters
        final String username = usr.getText().toString();
        final String roleText = role.getText().toString();
        final String passText = pass.getText().toString();
        final String ageNum = age.getText().toString();
        final String weightNum = weight.getText().toString();
        final String heightNum = height.getText().toString();
        final String phoneNumber = phone.getText().toString();
        final String addressText = address.getText().toString();
        final String email_address = email.getText().toString();
        final int stepN=0;
        final ArrayList<String> friend_request=new ArrayList<String>() ;
        final ArrayList<String> friend_list=new ArrayList<String>() ;

        /* Check the input. Int field can only accept digits, username and email follow the
         * instruction in A2. Home address and tel number can be empty.
         */

        String error = validator.validateRegisterFields(passText,roleText,ageNum,weightNum,heightNum,username,email_address);


        // Validate the error message. If it is not empty, then we show the message
        if(!error.isEmpty()) {
            errorMsg.setVisibility(View.VISIBLE);
            errorMsg.setText(error);
            errorMsg.setTextColor(Color.RED);
        }
        else {
            // If user register successfully, add his or her information into Firestore
            registerUser.createUserWithEmailAndPassword(email_address+"", passText+"")
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        errorMsg.setText("");
                                        DocumentReference ref= db.collection("Users").document(username+"");
                                        User userinfo;


                                        userinfo = new User(username+"",phoneNumber+"",addressText+"," +
                                                "", email_address+"",passText+"", roleText+"", friend_request, friend_list, Integer.parseInt(heightNum),
                                                Integer.parseInt(weightNum),Integer.parseInt(ageNum),stepN);
                                        ref.set(userinfo);

                                        // Successful Message
                                        Toast.makeText(Register.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                    }else{

                                        // Error message
                                        Toast.makeText(Register.this, "Failed to create user:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );
        }
    }

}



