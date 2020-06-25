package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;



import androidx.appcompat.app.AppCompatActivity;

public class AthletePost  extends AppCompatActivity {
    private TextView t1;
    private EditText e1;
    private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_althletepost);
        t1=findViewById(R.id.textView2);
        t1.setText("Entry content which you want to post");
        e1=findViewById(R.id.editTextTextPersonName);
        e1.setText("");
        b1=findViewById(R.id.button6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
