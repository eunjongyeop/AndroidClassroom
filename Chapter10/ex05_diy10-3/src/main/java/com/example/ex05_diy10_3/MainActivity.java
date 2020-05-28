package com.example.ex05_diy10_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextNum1, editTextNum2;
    Button btnCalculate;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNum1 = findViewById(R.id.EditTextNum1);
        editTextNum2 = findViewById(R.id.EditTextNum2);
        btnCalculate = findViewById(R.id.btnClaclate);
        radioGroup = findViewById(R.id.radioGroup);

        findViewById(R.id.rbtnAdd);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

                if (editTextNum1.getText().toString().equals("") || editTextNum2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "숫자를 입력해 주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num1 = Integer.parseInt(editTextNum1.getText().toString()),
                        num2 = Integer.parseInt(editTextNum2.getText().toString());
                
                intent.putExtra("Num1", num1);
                intent.putExtra("Num2", num2);

                char op;
                switch(radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbtnAdd :
                        op = '+';
                        break;
                    case R.id.rbtnSub :
                        op = '-';
                        break;
                    case R.id.rbtnMul :
                        op = '*';
                        break;
                    case R.id.rbtnDiv :
                        if(num2 == 0) {
                            Toast.makeText(MainActivity.this, "0으로는 나눌수 없습니다", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        op = '/';
                        break;
                    default :
                        Toast.makeText(MainActivity.this, "부호를 선택해 주세요", Toast.LENGTH_SHORT).show();
                        return;
                }
                intent.putExtra("Op", op);

                startActivityForResult(intent, 0);
            }
        });
    }

    // startActivityForResult() 응답이 돌아오면 자동 실행되는 메소드

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==0 && resultCode == RESULT_OK) {
            int hap = data.getIntExtra("Result", 0);
            Toast.makeText(this, "합계 : "+hap, Toast.LENGTH_SHORT).show();
        }
    }
}
