package com.example.chapter7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnLayoutInflater;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLayoutInflater = findViewById(R.id.btnLayoutInflater);
        linearLayout = findViewById(R.id.mainLinearLayout);

        btnLayoutInflater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = getLayoutInflater();  // xml코드를 view 객채로 만들어준다.
                View sampleLayoutView = layoutInflater.inflate(R.layout.sample_layout, null);
                linearLayout.addView(sampleLayoutView);
            }
        });
    }
}
