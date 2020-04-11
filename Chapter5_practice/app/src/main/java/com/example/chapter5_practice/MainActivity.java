package com.example.chapter5_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 1. View 참조변수 생성
    Button btn_visible, btn_invisible;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. 참조변수에 View 인스턴스 연결
        btn_visible = findViewById(R.id.btn_visible);
        btn_invisible = findViewById(R.id.btn_invisible);
        imageView = findViewById(R.id.imageView);

        // 3. 버튼 클릭 이벤트 클릭
        btn_visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
            }
        });

        btn_invisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
            }
        });
    }
}
