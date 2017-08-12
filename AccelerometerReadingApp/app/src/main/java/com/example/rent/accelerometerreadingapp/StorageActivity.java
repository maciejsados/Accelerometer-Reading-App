package com.example.rent.accelerometerreadingapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class StorageActivity extends AppCompatActivity implements SensorEventListener {

    private TextView axis_x_label;
    private TextView axis_y_label;
    private TextView axis_z_label;
    private Button button;
    private SensorManager sensorManager;
    private Sensor accelerometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        axis_x_label = (TextView) findViewById(R.id.axis_x);
        axis_y_label = (TextView) findViewById(R.id.axis_y);
        axis_z_label = (TextView) findViewById(R.id.axis_z);
        button = (Button) findViewById(R.id.button_stop);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        SensorEventMenager.instance.OpenFile(getApplicationContext());

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SensorEventMenager.instance.close();
                startActivity(new Intent(StorageActivity.this, MainActivity.class));
            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float value_x = sensorEvent.values[0];
            String string_value_x = String.valueOf(value_x);
            axis_x_label.setText(string_value_x);
            float value_y = sensorEvent.values[1];
            String string_value_y = String.valueOf(value_y);
            axis_y_label.setText(string_value_y);
            float value_z = sensorEvent.values[2];
            String string_value_z = String.valueOf(value_z);
            axis_z_label.setText(string_value_z);

           SensorEventMenager.instance.saveToFile(value_x, value_y, value_z);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this, accelerometer);
    }
}
