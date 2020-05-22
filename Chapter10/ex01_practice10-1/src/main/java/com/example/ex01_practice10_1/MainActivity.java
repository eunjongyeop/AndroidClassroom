package com.example.ex01_practice10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnNewActivity;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewActivity = findViewById(R.id.btnNewActivity);
        radioGroup = findViewById(R.id.radioGroup);

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.btnSecond :
                        startActivity(new Intent(MainActivity.this, SecondActivity.class));
                        break;
                    case R.id.btnThird :
                        startActivity(new Intent(MainActivity.this, ThirdActivity.class));
                        break;
                    default :
                        Toast.makeText(MainActivity.this, "엑티비티를 선택하시오", Toast.LENGTH_SHORT).show();
                        break;
                }

                /*
                // SecondActivity의 정보를 담고 있는 Intent 객체 생성
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                */
            }
        });
    }
}
