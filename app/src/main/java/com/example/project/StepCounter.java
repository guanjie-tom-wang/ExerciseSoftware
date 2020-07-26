package com.example.project;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StepCounter extends AppCompatActivity implements SensorEventListener, StepListener {
    // Set the lastStepTime to count the step
    private long lastStepTimeNs = 0;
    // Set the delay to make the step counter less sensitive
    private static final int STEP_DELAY_NS = 250000000;
    private SensorManager sensorManager;
    private Sensor accel;
    private int numSteps;
    private TextView stepInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_steps);
        setTitle("Start Counting");
        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("User_Name");
        final String user_type = intent.getStringExtra("User_Type");
        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        assert sensorManager != null;
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        stepInfo = findViewById(R.id.tv_steps);
        Button start = findViewById(R.id.btn_start);
        Button stop = findViewById(R.id.btn_stop);
        Button back = findViewById(R.id.button2);
        final Intent i=new Intent(StepCounter.this,landing_login.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("User_Name",user_name);
                i.putExtra("User_Type",user_type);
                startActivity(i);

        // Start training
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                stepInfo.setText("Start counting...");
                numSteps = 0;
                sensorManager.registerListener(StepCounter.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });

        // End of training
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                stepInfo.setText("Training completed!");
                sensorManager.unregisterListener(StepCounter.this);
                DocumentReference ref = db.collection("Users").document(user_name + "");
                ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentReference update = FirebaseFirestore.getInstance().collection("Users").document(user_name);
                            update.update("stepnumber", numSteps);
                        }
                    }
                });
            }
        });



    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // This part of code is used to count an actual step based on the x,y and z axis in
            // the emulator.
            if((event.values[0]>0.5 || event.values[0]<-0.5 ||
                    event.values[1]>10 || event.values[1]<8 ||event.values[2]>0.5 || event.values[2]<-0.5)
                    && event.timestamp - lastStepTimeNs > STEP_DELAY_NS) {
                lastStepTimeNs = event.timestamp;

                this.step();
            }

        }
    }

    @Override
    public void step() {
        numSteps++;
        stepInfo.setText("Start counting:"+ numSteps);
    }

}
