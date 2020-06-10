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


public class Register extends AppCompatActivity {

    // Set all objects that will be used
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth RegisterUser = FirebaseAuth.getInstance();
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
        setContentView(R.layout.activity_register);

        // Initialize variables
        db = FirebaseFirestore.getInstance();
        RegisterUser = FirebaseAuth.getInstance();
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
        final String friend_request_from="";
        String regex_email = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String regex_digit = "^\\d+";
        String error = "";

        /* Check the input. Int field can only accept digits, username and email follow the
         * instruction in A2. Home address and tel number can be empty.
         */

        if ( passText.isEmpty()) {
            error = error+"- Invalid password!\n";
        }
        if( roleText.isEmpty() ){
            error = error + "- Invalid role!\n";
        }
        if(!ageNum.matches((regex_digit))){
            error = error + "- Invalid age!\n";
        }
        if(!weightNum.matches((regex_digit))){
            error = error + "- Invalid weight!\n";
        }
        if(!heightNum.matches((regex_digit))){
            error = error + "- Invalid height!\n";
        }
        if ( username.isEmpty() || username.charAt(0)== ' ' || username.charAt(username.length() - 1) == ' ') {
            error = error + "- Invalid username!\n";

        }
        if (!email_address.matches(regex_email)) {
            error = error + "- Invalid email format!\n";
        }


        // If there is an error, show the message
        if(!error.isEmpty()) {
            error_msg.setVisibility(View.VISIBLE);
            error_msg.setText(error);
            error_msg.setTextColor(Color.RED);
        }
        else {
            // If user register successfully, add his or her information into Firestore
            RegisterUser.createUserWithEmailAndPassword(email_address+"", passText+"")
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        error_msg.setText("");
                                        DocumentReference ref= db.collection("Users").document(username+"");
                                        User userinfo;

                                        userinfo = new User(username+"",phoneNumber+"",addressText+"," +
                                                "", email_address+"",passText+"", roleText+"",friend_request_from,Integer.parseInt(heightNum),
                                                Integer.parseInt(weightNum),Integer.parseInt(ageNum));
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



