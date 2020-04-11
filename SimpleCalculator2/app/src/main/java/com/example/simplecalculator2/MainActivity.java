package com.example.simplecalculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class Calc implements View.OnClickListener {
        int num1, num2, result;
        @Override
        public void onClick(View v) {
            if (edit1.length() == 0 || edit2.length() == 0) {
                Toast.makeText(MainActivity.this, "숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            num1 = Integer.parseInt(edit1.getText().toString());
            num2 = Integer.parseInt(edit2.getText().toString());
            switch (v.getId()) {
                case R.id.BtnAdd :
                    result = num1 + num2;
                    resultText.setText("덧셈 계산 결과 : "+result);
                    break;
                case R.id.BtnSub :
                    result = num1 - num2;
                    resultText.setText("밸셈 계산 결과 : "+result);
                    break;
                case R.id.BtnMul :
                    result = num1 * num2;
                    resultText.setText("곱셈 계산 결과 : "+result);
                    break;
                case R.id.BtnDiv :
                    result = num1 / num2;
                    resultText.setText("나눗셈 계산 결과 : "+result);
                    break;
            }
        }
    }

    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView resultText;

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

        Calc calc = new Calc();
        btnAdd.setOnClickListener(calc);
        btnSub.setOnClickListener(calc);
        btnMul.setOnClickListener(calc);
        btnDiv.setOnClickListener(calc);
    }
}
