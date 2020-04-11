package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView resultText;
    int num1, num2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Calculator");

        edit1 = findViewById(R.id.Edit1);
        edit2 = findViewById(R.id.Edit2);
        btnAdd = findViewById(R.id.BtnAdd);
        btnSub = findViewById(R.id.BtnSub);
        btnMul = findViewById(R.id.BtnMul);
        btnDiv = findViewById(R.id.BtnDiv);
        resultText = findViewById(R.id.ResultText);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.getText().length()==0 || edit2.getText().length()==0){
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                num1 = Integer.parseInt(edit1.getText().toString());
                num2 = Integer.parseInt(edit2.getText().toString());
                result = num1 + num2;
                resultText.setText("덧셈 계산 결과 : "+result);
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.getText().length()==0 || edit2.getText().length()==0){
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                num1 = Integer.parseInt(edit1.getText().toString());
                num2 = Integer.parseInt(edit2.getText().toString());
                result = num1 - num2;
                resultText.setText("뺄셈 계산 결과 : "+result);
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.getText().length()==0 || edit2.getText().length()==0){
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                num1 = Integer.parseInt(edit1.getText().toString());
                num2 = Integer.parseInt(edit2.getText().toString());
                result = num1 * num2;
                resultText.setText("곱셈 계산 결과 : "+result);
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.getText().length()==0 || edit2.getText().length()==0){
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                num1 = Integer.parseInt(edit1.getText().toString());
                num2 = Integer.parseInt(edit2.getText().toString());
                result = num1 / num2;
                resultText.setText("나눗셈 계산 결과 : "+result);
            }
        });

    }
}
