package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;



import androidx.appcompat.app.AppCompatActivity;

public class AthletePost  extends AppCompatActivity {
    private TextView tAthlete;
    private EditText etAthlete;
    private Button bAthlete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_althletepost);
        tAthlete=findViewById(R.id.textView2);
        tAthlete.setText("Entry content which you want to post");
        etAthlete=findViewById(R.id.editTextTextPersonName);
        etAthlete.setText("");
        bAthlete=findViewById(R.id.button6);
        bAthlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
