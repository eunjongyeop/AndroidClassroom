package com.example.ex01_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    // 서비스가 시작될 때 호출되는 메서드

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("test", "서비스 시작");
        Toast.makeText(this, "서비스 시작", Toast.LENGTH_SHORT).show();

        // 사용자 정의 스레드 시작
        ThreadClass thread = new ThreadClass();
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }


    // 서비스가 종료될 때 호출되는 메서드

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("test", "서비스 종료");
        Toast.makeText(this, "서비스 종료", Toast.LENGTH_SHORT).show();
    }

    // 스레드 클래스 정의(총 10번 1초 마다 로그 출력)
    class ThreadClass extends Thread{
        @Override
        public void run() {
            for(int i=0; i<10; i++){
                SystemClock.sleep(1000);                // 1초 기다림
                long time = System.currentTimeMillis();     // 현재 시간(밀리세컨드)
                Log.d("test", "Service Running.... " + time);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
