package com.example.exercise01_optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // xml을 menu객체로 만들어줄 Inflater 객체 생성
        MenuInflater menuInflater = getMenuInflater();

        // xml을 menu객체로 생성
        menuInflater.inflate(R.menu.option_menu, menu);

        // java 코드에서 메뉴 아이템 추가
        menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "java 코드에서 추가한 메뉴");
        menu.add(Menu.NONE, Menu.FIRST+1, Menu.NONE, "java 코드에서 추가한 메뉴");

        return true;
    }

    // 옵션 메뉴가 선택되면 호출되는 메소드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1 :
                textView.setText("메뉴1 선택됨");
                break;
            case R.id.item2 :
                textView.setText("메뉴2 선택됨");
                break;
            case R.id.subitem1 :
                textView.setText("서브메뉴1 선택됨");
                break;
            case R.id.subitem2 :
                textView.setText("서브메뉴2 선택됨");
                break;
            case Menu.FIRST :
                textView.setText("java 코드에서 추가한 메뉴 선택됨");
                break;
            case Menu.FIRST+1 :
                textView.setText("java 코드에서 추가한 메뉴2 선택됨");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
