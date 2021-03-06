package com.example.jjapkaotalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout myLayout, friend1Layout, friend2Layout;
    TextView myName, mySangme;
    View dialogView;
    EditText inputArea;
    int selectFriends = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLayout = (LinearLayout)findViewById(R.id.myLayout);
        friend1Layout = (LinearLayout)findViewById(R.id.friend1Layout);
        friend2Layout = (LinearLayout)findViewById(R.id.friend2Layout);

        registerForContextMenu(myLayout);
        registerForContextMenu(friend1Layout);
        registerForContextMenu(friend2Layout);
    }

    // 옵션 메뉴 사용하기 위해 onCreateOptionsMenu()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater mInf = getMenuInflater();
        mInf.inflate(R.menu.option_menu, null);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.optionMenu_deleteAllFriends:
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setMessage("친구를 모두 삭제하시겠습니까?");
                dlg.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        friend1Layout.setVisibility(View.GONE);
                        friend2Layout.setVisibility(View.GONE);
                    }
                });
                dlg.setNegativeButton("아니오", null);
                return true;
            case R.id.optionMenu_restoreAllFriends:
                friend1Layout.setVisibility(View.VISIBLE);
                friend2Layout.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v == myLayout) {
            MenuInflater mInf = getMenuInflater();
            mInf.inflate(R.menu.my_context_menu, menu);
        }
        if(v == friend1Layout) {
            selectFriends = 1;
            MenuInflater mInf = getMenuInflater();
            mInf.inflate(R.menu.friends_context_menu, menu);
        }
        if(v == friend2Layout) {
            selectFriends = 2;
            MenuInflater mInf = getMenuInflater();
            mInf.inflate(R.menu.friends_context_menu, menu);
        }

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myContextMenu_changeName :
                dialogView = (View)View.inflate(MainActivity.this, R.layout.input_dialog, null);
                AlertDialog.Builder changeNameDlg = new AlertDialog.Builder(MainActivity.this);
                changeNameDlg.setTitle("이름을 입력하세요");
                changeNameDlg.setView(dialogView);
                changeNameDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputArea = (EditText)dialogView.findViewById(R.id.inputArea);
                        myName = (TextView)findViewById(R.id.myName);
                        myName.setText(inputArea.getText().toString());
                    }
                });
                changeNameDlg.setNegativeButton("취소", null);
                return true;
            case R.id.myContextMenu_changeSangme :
                dialogView = (View)View.inflate(MainActivity.this, R.layout.input_dialog, null);
                AlertDialog.Builder changeSangmeDlg = new AlertDialog.Builder(MainActivity.this);
                changeSangmeDlg.setTitle("상태메세지을 입력하세요");
                changeSangmeDlg.setView(dialogView);
                changeSangmeDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputArea = (EditText)dialogView.findViewById(R.id.inputArea);
                        mySangme = (TextView)findViewById(R.id.mySangme);
                        mySangme.setText(inputArea.getText().toString());
                    }
                });
                changeSangmeDlg.setNegativeButton("취소", null);
                return true;
            case R.id.friendsContextMenu_delFriend:
                if(selectFriends == 1)
                    friend1Layout.setVisibility(View.GONE);
                if(selectFriends == 2)
                    friend2Layout.setVisibility(View.GONE);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
