package com.example.intentshpg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        setTitle("2213은종엽");

        textView = findViewById(R.id.textView);
        radioGroup = findViewById(R.id.radioGroup);
        btnReturn = findViewById(R.id.btnReturn);

        final Intent intent = getIntent();
        textView.setText(intent.getStringExtra("text"));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                int resultCode = 0;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbtnCall:
                        resultCode = 1;
                        break;
                    case R.id.rbtnCamera:
                        resultCode = 2;
                        break;
                }

                setResult(resultCode, intent1);
                finish();
            }
        });
    }
}