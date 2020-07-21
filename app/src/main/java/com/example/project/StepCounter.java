package com.example.project;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StepCounter extends AppCompatActivity implements SensorEventListener, StepListener {
    private long lastStepTimeNs = 0;
    private static final int STEP_DELAY_NS = 250000000;
    private SensorManager sensorManager;
    private Sensor accel;
    private int numSteps;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_steps);


        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        assert sensorManager != null;
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        tv = findViewById(R.id.tv_steps);
        Button start = findViewById(R.id.btn_start);
        Button stop = findViewById(R.id.btn_stop);



        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                tv.setText("Start counting...");
                numSteps = 0;
                sensorManager.registerListener(StepCounter.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });


        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                tv.setText("Training completed!");
                sensorManager.unregisterListener(StepCounter.this);

            }
        });



    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
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
        tv.setText("Start counting:"+ numSteps);
    }

}
