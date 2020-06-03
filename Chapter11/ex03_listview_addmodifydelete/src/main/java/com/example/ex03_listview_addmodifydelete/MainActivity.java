package com.example.ex03_listview_addmodifydelete;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    ListView listView;
    Button btnAdd, btnModify, btnDelete;
    ArrayList<String> arrayList = getArrayListData();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region 참조변수에 뷰 객체 연결
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        btnModify = findViewById(R.id.btnMotify);
        btnDelete = findViewById(R.id.btnDelete);
        //endregion

        // 리스트부에 각 함목을 표시할 뷰와 데이터를 제공할 ArrayAdapter 객체 생성
        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_single_choice,
                arrayList
        );

        // ListView가 라디오 단추 레이아웃을 사용한다고 설정
        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        // adapter를 ListView에 연결한다
        listView.setAdapter(adapter);

        // ListView의 아이템을 클릭했을 때 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(arrayList.get(position));
            }
        });

        btnAdd.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    // 리스트뷰에 들어갈 데이터 생성
    private ArrayList<String> getArrayListData() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 1; i<=5; i++){
            arrayList.add("리스트 아이템 "+i);
        }

        return arrayList;
    }

    @Override
    public void onClick(View v) {
        final int checkedItemPosition = listView.getCheckedItemPosition();    // 체크된 아이템 번호 가져오기

        switch (v.getId()) {
            case R.id.btnAdd :
                int count = adapter.getCount(); // 아이템 개수 저장
                arrayList.add("리스트 아이템 "+(count+1));    // 데이터 추가
                break;

            case R.id.btnMotify :
                final EditText editText = new EditText(getApplicationContext()); // 대화상자에 추가할 EditText
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("리스트뷰의 아이템 수정")
                        .setMessage("선택된 데이터 : "+arrayList.get(checkedItemPosition))
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setView(editText)
                        .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.set(checkedItemPosition, editText.getText().toString());
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                break;

            case R.id.btnDelete :
                arrayList.remove(checkedItemPosition);
                break;
        }
        adapter.notifyDataSetChanged(); // 변경사항 adapter에 반영
    }
}