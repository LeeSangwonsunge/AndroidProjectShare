package com.example.inha.androidpj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WalkCountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_count);
    }

    public void onBtnFinishClicked(View view){
        finish();
    }
}
