package com.example.inha.androidpj;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private GPSService gpsService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, GPSService.class));
    }

    public void onBtnWalkCountClicked(View view){
        Intent intent = new Intent(this, WalkCountActivity.class);
        startActivity(intent);
    }

    public void onBtnDustClicked(View view){
        Intent intent = new Intent(this, DustActivity.class);
        startActivity(intent);
    }

    public void onBtnSleepClicked(View view){
        Intent intent = new Intent(this, SleepActivity.class);
        startActivity(intent);
    }

    public void onBtnLocationClicked(View view){
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void onBtnDBClicked(View view){
        Intent intent = new Intent(this, DBActivity.class);
        startActivity(intent);
    }

}
