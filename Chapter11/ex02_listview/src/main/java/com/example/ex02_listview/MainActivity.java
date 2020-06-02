package com.example.ex02_listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;

    // ListView에 표시될 데이터
    ArrayList<String> dataList = getStringList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);

        // ArrayAdapter에 ListView를 구성할 View와 데이터를 합침.
        // ArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.row, R.id.textViewRow, dataList);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        // ListView에 ArrayAdapter 연결
        listView.setAdapter(adapter);

        // ListView의 각 항목을 클릭했을 때 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                textView.setText(dataArr[position]);
                textView.setText(dataList.get(position));
            }
        });
    }

    // ListView에 표시될 데이터 배열 생성 메소드
    private String[] getStringArray() {
        String[] strArr = new String[100];

        for(int i = 0; i < strArr.length; i++) {
            strArr[i] = "리스트 아이템"+(i+1);
        }

        return strArr;
    }

    // ListView에 표시될 데이터 ArrayList 생성 메소드
    private ArrayList<String> getStringList() {
        ArrayList<String> arrayList = new ArrayList<>();

        for(int i = 1; i<=100; i++) {
            arrayList.add("리스트 아이템" + i);
        }

        return arrayList;
    }
}
