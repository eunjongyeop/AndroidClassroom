package com.example.practice01_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView selectedItemTextView;
    ListView listView;
    Button btnAdd, btnModify, btnDelete;
    int selectedNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2213 은종엽");

        selectedItemTextView = findViewById(R.id.selectedItemTextView);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        btnModify = findViewById(R.id.btnModify);
        btnDelete = findViewById(R.id.btnDelete);

        final ArrayList<String> arrayList = new ArrayList<>();
        for(int i = 1; i<=5; i++) {
            arrayList.add("리스트 데이터 "+i);
        }

        final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_single_choice, arrayList);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNum = position;
                selectedItemTextView.setText(arrayList.get(position));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add("리스트 데이터 " + (arrayList.size() + 1));
                adapter.notifyDataSetChanged();
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.size() == 0){
                    Toast.makeText(MainActivity.this, "수정할 리스트가 없습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                final EditText et = new EditText(getApplicationContext());
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("리스트 아이템 수정")
                        .setMessage("현제 데이터 : "+ arrayList.get(selectedNum))
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setView(et)
                        .setNegativeButton("취소", null)
                        .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.set(selectedNum, et.getText().toString());
                                adapter.notifyDataSetChanged();
                                selectedItemTextView.setText(arrayList.get(selectedNum));
                            }
                        })
                        .show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.size() == 0){
                    Toast.makeText(MainActivity.this, "삭제할 리스트가 없습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                arrayList.remove(selectedNum);
                adapter.notifyDataSetChanged();

                if(selectedNum == arrayList.size()) {
                    selectedNum -= 1;
                }

                if(arrayList.size() != 0) {
                    selectedItemTextView.setText(arrayList.get(selectedNum));
                }
                else {
                    selectedItemTextView.setText("selected item content");
                }
            }
        });
    }
}
