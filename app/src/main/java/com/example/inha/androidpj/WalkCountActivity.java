package com.example.inha.androidpj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WalkCountActivity extends AppCompatActivity implements SensorEventListener {

    public static int cnt = 0;
    public static double Ccnt = 0.0;
    private EditText edtstreet;
    private EditText edtcal;
    private EditText edtwalk;
    private TextView txtDate;

    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;

    private static final int SHAKE_THRESHOLD = 800;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_count);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        edtstreet = (EditText)findViewById(R.id.edtstreet);
        edtcal = (EditText)findViewById(R.id.edtcal);
        edtwalk = (EditText)findViewById(R.id.edtwalk);
        txtDate = (TextView)findViewById(R.id.txtDate);
        edtwalk.setText("" +cnt);
        edtcal.setText("" +Ccnt);
    }

    private String getDateString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String str_date = df.format(new Date());
        return str_date;
    }
    public void onBtnDateClicked(View view){
        txtDate.setText(getDateString());
    }
    @Override
    public void onStart(){
        super.onStart();
        if(accelerormeterSensor != null)
            sensorManager.registerListener(this,accelerormeterSensor,SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    edtwalk.setText("" + (++cnt));
                    edtcal.setText(""+ (++Ccnt*0.033) +"cal");
                }

                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    public void onBtnFinishClicked(View view){ finish(); }
}
