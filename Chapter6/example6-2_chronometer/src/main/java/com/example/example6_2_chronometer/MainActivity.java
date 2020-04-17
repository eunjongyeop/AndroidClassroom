package com.example.example6_2_chronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    start버튼을 여러번 누르거나 1시간이 넘으면 error 발생
     */

    Chronometer chronometer;
    Button btnStart, btnStop, btnReset;
    int stopTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                Log.d("realTime", String.valueOf(SystemClock.elapsedRealtime()).toString());
            }});

        //region  //버튼 이벤트 처리
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime()-stopTime*1000);
                chronometer.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                stopTime = Integer.parseInt(chronometer.getText().toString().substring(0, 2))*60 + Integer.parseInt(chronometer.getText().toString().substring(3));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                stopTime = 0;
            }
        });
        //endregion
    }
}
