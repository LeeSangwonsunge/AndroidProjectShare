package com.example.inha.androidpj;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.view.Window;
import java.text.DecimalFormat;

public class WalkCountActivity extends AppCompatActivity implements SensorEventListener {

    private Handler wHandler;
    public static int cnt = 0;
    public static double Ccnt = 0.0;
    private DecimalFormat decimalFormat = new DecimalFormat("#.###");
    private EditText edtstreet;
    private EditText edtcal;
    private EditText edtwalk;
    private TextView txtTime;
    long BaseTime;
    final static int IDLE = 0;

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
        txtTime = (TextView)findViewById(R.id.txtTime);


        edtwalk.setText("" +cnt);
        edtcal.setText("" +Ccnt);
    }
    Handler mTimer = new Handler(){
        public void handleMessage(android.os.Message msg) {
            txtTime.setText(getEllapse());
            mTimer.sendEmptyMessage(0);//0은 메시지를 구분하기 위한 것
        };
    };
    @Override
    protected void onDestroy() {
        mTimer.removeMessages(0);//메시지를 지워서 메모리릭 방지
        super.onDestroy();
    }
    public void mOnClick(View v){
        switch(v.getId()){
            case R.id.btnstart:
                        BaseTime = SystemClock.elapsedRealtime();
                        mTimer.sendEmptyMessage(0);
                        break;
                }
        }
    String getEllapse(){
        long now = SystemClock.elapsedRealtime();
        long ell = now - BaseTime;
        String sEll = String.format("%02d:%02d:%02d", ell / 1000 / 60, (ell/1000)%60, (ell %1000)/10);
        return sEll;
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
