package com.example.ex06_practice_test_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView textView;
    RadioGroup radioGroup;
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = findViewById(R.id.textView);
        radioGroup = findViewById(R.id.radioGroup);
        btnReturn = findViewById(R.id.btnReturn);

        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("text"));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultCode = 0;
                switch (radioGroup.getCheckedRadioButtonId()) {

                    case R.id.rbtnPhone:
                        resultCode = 1;
                        break;
                    case R.id.rbtnCamera:
                        resultCode = 2;
                        break;
                }

                Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(resultCode, returnIntent);
                finish();
            }
        });
    }
}