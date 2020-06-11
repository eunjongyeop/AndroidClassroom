package com.example.calculatoreasy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2213은종엽");

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        textView = findViewById(R.id.textView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1=0, num2=0, result=0;
                try {
                    num1 = Double.parseDouble(editText1.getText().toString());
                    num2 = Double.parseDouble(editText2.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                switch (v.getId()) {
                    case R.id.btnAdd:
                        result = num1 + num2;
                        break;
                    case R.id.btnSub:
                        result = num1 - num2;
                        break;
                    case R.id.btnMul:
                        result = num1 * num2;
                        break;
                    case R.id.btnDiv:
                        if (num2 == 0) {
                            Toast.makeText(MainActivity.this, "0으로 나눌수 없습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        result = num1 / num2;
                        break;
                }

                textView.setText("계산결과: "+String.valueOf(result));
            }
        };

        btnAdd.setOnClickListener(listener);
        btnSub.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);
    }
}
