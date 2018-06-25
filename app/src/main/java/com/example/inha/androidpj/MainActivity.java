package com.example.inha.androidpj;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GPSService gpsService;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.Calendar_btn){
            Intent intent = new Intent(this, Calendar.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, GPSService.class));

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        Toast.makeText(this, date, Toast.LENGTH_LONG).show();
    }

    public void onBtnWalkCountClicked(View view){
        Intent intent = new Intent(this,WalkCountActivity.class);
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
