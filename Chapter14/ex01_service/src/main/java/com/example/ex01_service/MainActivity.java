package com.example.ex01_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStartService, btnStopService, btnStartIntentService, btnStopIntentService, btnStartForegroundIntentService;
    Intent service_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btnStartService);
        btnStopService = findViewById(R.id.btnStopService);
        btnStartIntentService = findViewById(R.id.btnStartIntentService);
        btnStopIntentService = findViewById(R.id.btnStopIntentService);
        btnStartForegroundIntentService = findViewById(R.id.btnStartForegroundIntentService);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnStartIntentService.setOnClickListener(this);
        btnStopIntentService.setOnClickListener(this);
        btnStartForegroundIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:  // 서비스 시작
                service_intent = new Intent(this, MyService.class);
                startService(service_intent);
                break;

            case R.id.btnStopService:  // 서비스 종료
            case R.id.btnStopIntentService:
                stopService(service_intent);
                break;

            case R.id.btnStartIntentService:
                service_intent = new Intent(this, MyIntentService.class);
                startService(service_intent);
                break;

            case R.id.btnStartForegroundIntentService:
                service_intent = new Intent(this, MyForegroundIntentService.class);
                startForegroundService(service_intent);
                break;
        }
    }
}
