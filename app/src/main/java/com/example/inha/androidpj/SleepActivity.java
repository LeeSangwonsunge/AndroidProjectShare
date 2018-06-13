package com.example.inha.androidpj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SleepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
    }

    public void onBtnFinishClicked(View view){
        finish();
    }
}
