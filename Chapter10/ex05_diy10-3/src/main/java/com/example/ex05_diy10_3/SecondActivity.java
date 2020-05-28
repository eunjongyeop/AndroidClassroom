package com.example.ex05_diy10_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // MainActivity가 보낸 Intenet에서 데이터 꺼내기
        Intent inIntent = getIntent();
        int result = 0;
        int num1 = inIntent.getIntExtra("Num1", 0);
        int num2 = inIntent.getIntExtra("Num2", 0);
        char op = inIntent.getCharExtra("Op", '+');

        switch (op) {
            case '+' :
                result = num1 + num2;
                break;
            case '-' :
                result = num1 - num2;
                break;
            case '*' :
                result = num1 * num2;
                break;
            case '/' :
                result = num1 / num2;
                break;
        }

        final int Result = result;

        // 돌아가기 버튼 클릭 이벤트 처리
        findViewById(R.id.btnReturn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent();
                outIntent.putExtra("Result", Result);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
