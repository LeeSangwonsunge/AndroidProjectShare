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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private GPSService gpsService;

    Button btnSleep;
    TextView TVDate;

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

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String str_date = df.format(new Date());

        TVDate = findViewById(R.id.TVDate);
        TVDate.setText(checkDateString());
        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        Toast.makeText(this, date, Toast.LENGTH_LONG).show();
        checkDateString();

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

    public void onBtnCircleClicked(View view){
        Intent intent = new Intent(this, CircleActivity.class);
        startActivity(intent);
    }


    private String checkDateString() {

        final Database db = new Database(getApplicationContext(), "Location3.db",null,1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String str_date = df.format(new Date());
        Log.e("time",str_date+"");
        int cnt1 = 0;
        btnSleep=findViewById(R.id.btn_Circle);
        ArrayList<String> strList = db.getResult();
        String[] strArray ;
        if(db.getDateResult(str_date)!=null){
            strArray=db.getDateResult("2018-06-27");

            String[] cnt = strArray[1].split("/");

            Log.e("dbR",cnt[1]+"");

            for (int i=0; i<cnt.length; i++){
                if(cnt[i].equals("sleep")){
                    cnt1=cnt1+1;
                }

            }
            Log.e("cnt",cnt1+"");

            double cntDouble = cnt1/4;
            btnSleep.setText("수면 : "+cntDouble+"시간");
        }


        return str_date;
    }
}
