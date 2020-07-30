package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;



import androidx.appcompat.app.AppCompatActivity;

public class AthletePost  extends AppCompatActivity {
    private TextView tAthlete;
    private Button bAthlete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_althletepost);
        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        final String user_type = intent.getStringExtra("User_Type");
        tAthlete=findViewById(R.id.textView2);
        tAthlete.setText("Please enter the content you want to post");
        bAthlete=findViewById(R.id.button6);
        final Intent i=new Intent(AthletePost.this, LandingLogin.class);
        bAthlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("User_Name",user_name);
                i.putExtra("User_Type",user_type);
                startActivity(i);
                finish();
            }
        });
    }
}
