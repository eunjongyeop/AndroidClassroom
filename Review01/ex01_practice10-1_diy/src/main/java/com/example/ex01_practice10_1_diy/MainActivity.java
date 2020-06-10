package com.example.ex01_practice10_1_diy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        btnAct = findViewById(R.id.btnAct);

        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbtnSecond :
                        Intent intent = new Intent().setClass(getApplicationContext(), SecondActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.rbtnThird :
                        startActivity(new Intent().setClass(getApplicationContext(), ThirdActivity.class));
                        break;
                    default :
                        Toast.makeText(MainActivity.this, "엑티비티를 선택하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
