package com.example.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    // All instances we need
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private EditText username;
    private EditText password;
    private Button login;
    private FirebaseAuth mAuth;
    private Button register;
    private ProgressDialog progressDialog;
    private static final String TAG = MainActivity.class.getSimpleName();


    private Button Update;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize instances
        setTitle("Login");
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);
        Update = findViewById(R.id.Update);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update.class));
            }
        });

    }

    private void userLogin() {
        // Get user name from the textview
        final String uName = username.getText().toString();
        String uPassword = password.getText().toString();
        final Intent login=new Intent(LoginActivity.this, landing_login.class);
        if(uName.length() == 0 || uPassword.length() == 0){
            Toast.makeText(this, "One or more of the field is Empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(uName, uPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //query the database, find the user's username according to email address entered.
                    Query query= db.collection("Users").whereEqualTo("emailAddress",uName);
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    String user_name = (String) document.getData().get("username");
                                    String user_type = (String) document.getData().get("type");

                                    progressDialog.dismiss();
                                    //send the username to other activities.
                                    login.putExtra("User_Name",user_name);
                                    login.putExtra("User_Type",user_type);
                                    startActivity(login);
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

                }else{

                    Toast.makeText(LoginActivity.this, "One or more field is incorrect"
                            , Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });


    }
}
