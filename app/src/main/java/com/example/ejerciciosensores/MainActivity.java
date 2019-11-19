package com.example.ejerciciosensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    TextView textView1,textView2;
    GridLayout gridLayout;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gridLayout=findViewById(R.id.gridLayout);
        textView1=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        mediaPlayer=MediaPlayer.create(this,R.raw.qotsa);


        List<Sensor> lista = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorManager.registerListener( this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), sensorManager.SENSOR_DELAY_NORMAL);
       // sensorManager.registerListener( this, sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
           if(event.values[0]==0){
               Random rand = new Random();
               int r = rand.nextInt();

              gridLayout.setBackgroundColor(r);
           }
           else{
               gridLayout.setBackgroundColor(Color.WHITE);

           }
            textView1.setText("Proximidad: " + event.values[0]);
        }
      /*  if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
          //if(event.values[0]==0){
               textView2.setText("Rotacion x: " + event.values[2]);


        }*/



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
