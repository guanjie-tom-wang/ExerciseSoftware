package com.example.project;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;

public class CoachPost extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coachpost);

        Spinner mySpinner = (Spinner) findViewById(R.id.planets_spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CoachPost.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.drop_lists));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
    }


}
