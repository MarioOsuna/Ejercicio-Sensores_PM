package com.example.ejerciciosensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;

    GridLayout gridLayout;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gridLayout = findViewById(R.id.gridLayout);


        mediaPlayer = MediaPlayer.create(this, R.raw.qotsa);
        List<Sensor> lista = sensorManager.getSensorList(Sensor.TYPE_ALL);

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), sensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] == 0) {
                Random rand = new Random();
                int r = rand.nextInt();

                gridLayout.setBackgroundColor(r);
            } else {
                gridLayout.setBackgroundColor(Color.WHITE);

            }

        }
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

            int rotation = display.getRotation();

            // El objeto devuelve 3 estados 0,1 y 3
            if (rotation == 0) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();

                }


            } else if (mediaPlayer.isPlaying()) {

            } else {
                mediaPlayer.start();

            }

        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
