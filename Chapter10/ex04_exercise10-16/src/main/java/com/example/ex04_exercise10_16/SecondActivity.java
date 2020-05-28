package com.example.ex04_exercise10_16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // MainActivity가 보낸 Intenet에서 데이터 꺼내기
        Intent inIntent = getIntent();
        final int hap = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0);

        // 돌아가기 버튼 클릭 이벤트 처리
        findViewById(R.id.btnReturn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent();
                outIntent.putExtra("Hap", hap);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
