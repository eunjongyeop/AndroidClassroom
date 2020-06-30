package com.example.ex07_listview_customadapter02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;
    Button btnAdd;

    ArrayList<ItemVO> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        // 리스트뷰에 표시할 초기 데이터
        dataList.add(new ItemVO("doc", "Document 1", "Sample Data"));
        dataList.add(new ItemVO("img", "Image 1", "Sample Data"));
        dataList.add(new ItemVO("file", "File 1", "Sample Data"));

        // CustomAdapter 객체 생성

        final CustomAdapter adapter = new CustomAdapter(this, R.layout.row, dataList);

        // 리스트뷰에 어댑터 연결
        listView.setAdapter(adapter);

        //region 리스트뷰에 아이템 추가
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                final View myView = inflater.inflate(R.layout.alertdialog_add_item, null);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("리스트 아이템 추가")
                        .setMessage("문서 종류 선택")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setView(myView)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 입력된 데이터를 임시변수에 저장
                                String type=null, title, content;

                                // 라디오 버튼과 EditText에 입력된 데이터 가져오기
                                int checkedId = ((RadioGroup)myView.findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
                                switch (checkedId) {
                                    case R.id.rbDoc:
                                        type = "doc";
                                        break;
                                    case R.id.rbImg:
                                        type = "img";
                                        break;
                                    case R.id.rbFile:
                                        type = "file";
                                        break;
                                }
                                title = ((EditText)myView.findViewById(R.id.editTextTitle)).getText().toString();
                                content = ((EditText)myView.findViewById(R.id.editTextContent)).getText().toString();

                                dataList.add(new ItemVO(type, title, content));
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });
        //endregion

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(dataList.get(position).getTitleStr());
            }
        });
    }
}
