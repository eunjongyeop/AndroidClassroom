package com.example.ex01_service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyForegroundIntentService extends IntentService {

    public MyForegroundIntentService() {
        super("MyForegroundIntentService2");
    }

    // 서비스가 시작되는 호출되는 메소드
    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("test", "Foreground 인텐트 서비스 시작");

        // Notification 띄우기
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("test", "Service", NotificationManager.IMPORTANCE_HIGH);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        manager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test");
        builder.setSmallIcon(android.R.drawable.ic_menu_search);
        builder.setContentTitle("서비스 가동");
        builder.setContentText("서비스 가동 중입니다.");
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        // 현재 notification 메세지를 포그라운드 서비스의 메세지에 등록
        startForeground(100, notification);

        return super.onStartCommand(intent, flags, startId);
    }

    // 서비스가 종료되는 호출되는 메소드
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("test", "Foreground 인텐트 서비스 종료");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        for(int i=0; i<10; i++){
            SystemClock.sleep(1000);                // 1초 기다림
            long time = System.currentTimeMillis();     // 현재 시간(밀리세컨드)
            Log.d("test", "Service Running.... " + time);
        }

        // 스레드 작업이 완료되면 Notification 메세지가 사라지도록 함
        stopForeground(STOP_FOREGROUND_REMOVE);
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(100);
    }
}
