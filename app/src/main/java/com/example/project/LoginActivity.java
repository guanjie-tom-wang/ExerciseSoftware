package com.example.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        String uName = username.getText().toString();
        String uPassword = password.getText().toString();

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
                    progressDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "One or more field is incorrect"
                    , Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
