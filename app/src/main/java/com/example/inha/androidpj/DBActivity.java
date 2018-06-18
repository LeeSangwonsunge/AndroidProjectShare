package com.example.inha.androidpj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DBActivity extends AppCompatActivity {

    TextView tvSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
    }

    public void onBtnFinishClicked(View view){
        finish();
    }

    public void onBtnSelectClicked(View view)throws Exception{
        tvSelect=(TextView)findViewById(R.id.TVselect);
        final Database db = new Database(getApplicationContext(), "Location1.db",null,1);
        String result = db.getResult();
        tvSelect.setText(result);
    }
}
