package com.example.ex03_practice3_inputoutputmenory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btnWrite, btnRead;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
        textView= findViewById(R.id.textView);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fos = null;

                try {
                    fos = openFileOutput("myFile.txt", Context.MODE_PRIVATE);

                    String string = editText.getText().toString();
                    fos.write(string.getBytes());

                    Toast.makeText(MainActivity.this, "myFile.txt 생성됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;

                try {
                    fis = openFileInput("myFile.txt");

                    byte[] byteArray = new byte[50];

                    fis.read(byteArray);
                    textView.setText(new String(byteArray));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
