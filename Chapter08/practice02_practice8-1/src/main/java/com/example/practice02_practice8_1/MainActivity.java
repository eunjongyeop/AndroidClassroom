package com.example.practice02_practice8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    EditText editText;
    Button btnWriter;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        datePicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);
        btnWriter = findViewById(R.id.btnWriter);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 앱이 처음 실행될때, 일기가 있으면 가져와서 출력
        readAndWriteDiary(year, month, day);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                readAndWriteDiary(year, monthOfYear, dayOfMonth);
            }
        });

        btnWriter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try(FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);) {
                    String str = editText.getText().toString();
                    outFs.write(str.getBytes());
                    Toast.makeText(MainActivity.this, fileName+" 이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void readAndWriteDiary(int year, int month, int day) {
        fileName = String.format("%d_%d_%d.txt", year, month+1, day);
        String str = readDiary2(fileName);       // 선택된 날짜 이름을 가진 파일의 이름을 읽어와서 저장
        editText.setText(str);                 // 읽어온 텍스트를 editText에 표시
        btnWriter.setEnabled(true);             // 버튼 활성화
    }

    // 500byte가 넘어가면 짤림
    private String readDiary1(String fileName) {
        String dirayStr = null;

        try(FileInputStream inFs = openFileInput(fileName)) {
            byte[] txt = new byte[500];
            inFs.read(txt);
            dirayStr = (new String(txt)).trim();
            btnWriter.setText("수정하기");
        } catch (IOException e) {
            editText.setHint("일기 없음");
            btnWriter.setText("새로 저장");
        }

        return dirayStr;
    }

    // 일기를 전부다 읽어 오는 메소드
    private String readDiary2(String fileName) {
        String dirayStr = null;

        try(FileInputStream fis = openFileInput(fileName)) {
            byte[] txt = new byte[fis.available()];     // 읽어올 데이터 길이 만큼의 배열을 생성
            fis.read(txt);
            dirayStr = (new String(txt)).trim();
            btnWriter.setText("수정하기");
        } catch (IOException e) {
            editText.setHint("일기 없음");
            btnWriter.setText("새로 저장");
        }

        return dirayStr;
    }
}
