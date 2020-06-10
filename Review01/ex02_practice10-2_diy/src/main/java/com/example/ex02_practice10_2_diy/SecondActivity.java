package com.example.ex02_practice10_2_diy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        btnReturn = findViewById(R.id.btnReturn);

        final Intent intent = getIntent();
        textView.setText(intent.getStringExtra("text"));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                intent2.putExtra("meg", editText.getText().toString());
                setResult(RESULT_OK, intent2);
                finish();
            }
        });
    }
}