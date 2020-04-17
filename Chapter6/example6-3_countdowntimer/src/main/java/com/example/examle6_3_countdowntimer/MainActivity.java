package com.example.examle6_3_countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btnStart, btnStop, btnPauseRestart;
    CountDownTimer countDownTimer;
    int time = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnPauseRestart = findViewById(R.id.btnPauseRestart);

        countDownTimer(time*1000, 1000);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.start();
                btnPauseRestart.setText("PAUSE");
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                countDownTimer(time*1000, 1000);
                btnPauseRestart.setText("PAUSE");
            }
        });

        btnPauseRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnPauseRestart.getText().equals("PAUSE")) {
                    countDownTimer.cancel();
                    countDownTimer(Integer.parseInt(textView.getText().toString())*1000,1000);
                    btnPauseRestart.setText("RESTART");
                } else {
                    countDownTimer.start();
                    btnPauseRestart.setText("PAUSE");
                }
            }
        });
    }

    private void countDownTimer(long millisInFuture, final long countDownInterval) {
        textView.setText(String.valueOf(millisInFuture/1000));
        countDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "CountDown Finished", Toast.LENGTH_SHORT).show();
            }
        };
    }

}
