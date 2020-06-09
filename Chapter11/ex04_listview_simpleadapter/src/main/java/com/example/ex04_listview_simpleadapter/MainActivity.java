package com.example.ex04_listview_simpleadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;

    // ListView에 표시될 데이터
    ArrayList<String> titleData = getStringList("Title", 50);
    ArrayList<String> contentsData = getStringList("Contents", 50);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);

        // ListView에 표시할 데이터들을 합쳐 하나의 ArrayList 만들기
        final ArrayList<HashMap<String, String>> listViewData = new ArrayList<HashMap<String, String>>();
        for(int i=0; i<titleData.size(); i++) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("title", titleData.get(i));
            hashMap.put("contents", contentsData.get(i));

            listViewData.add(hashMap);
        }

        // 키값 배열
        String[] keys = {"title", "contents"};

        // 한 항목에 해당하는 textView의 id값 배열
        int[] ids = {android.R.id.text1, android.R.id.text2};

        // SimpleAdapter에 ListView를 구성할 View와 데이터를 합침.
        // SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
        SimpleAdapter adapter = new SimpleAdapter(
                this, listViewData,
                android.R.layout.simple_list_item_2,
                keys,
                ids
                );

        // ListView에 ArrayAdapter 연결
        listView.setAdapter(adapter);

        // ListView의 각 항목을 클릭했을 때 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 화면 상단의 textView에 선택된 아이템의 내용을 출력함
                textView.setText(titleData.get(position) + " : " + contentsData.get(position));
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
    private ArrayList<String> getStringList(String data, int count) {
        ArrayList<String> arrayList = new ArrayList<>();

        for(int i = 1; i<=count; i++) {
            arrayList.add(data + i);
        }

        return arrayList;
    }
}
