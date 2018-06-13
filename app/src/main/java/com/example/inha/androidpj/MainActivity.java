package com.example.inha.androidpj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
