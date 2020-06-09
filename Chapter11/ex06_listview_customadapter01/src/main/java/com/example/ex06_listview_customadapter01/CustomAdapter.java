package com.example.ex06_listview_customadapter01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    int resource;
    ArrayList<String> dataList;

    public CustomAdapter(Context context, int resource, ArrayList<String> dataList) {
        this.context = context;
        this.resource = resource;
        this.dataList = dataList;
    }

    // 리스트뷰의 항목 개수 반환
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) { return null; }

    @Override
    public long getItemId(int position) { return 0; }

    // 리스트뷰의 한 항목을 구현하는 뷰 반환
    // 화면에 항목이 보여질 때 호출됨. 화면에 보여지는 개수만큼 호출
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // 재활용 가능한 View가 없다면 새로운 view 생성
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);
        }

        // 반환할 View 객체 설정
        TextView textViewRow = convertView.findViewById(R.id.textViewRows);
        textViewRow.setText(dataList.get(position));

        Button btnModify = convertView.findViewById(R.id.btnModify);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        //region 수정 버튼 이벤트 정의
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(context);
                new AlertDialog.Builder(context)
                        .setTitle("리스트뷰 아이템 수정")
                        .setMessage("선택된 데이터: "+ dataList.get(position))
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setView(editText)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataList.set(position, editText.getText().toString());
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });
        //endregion

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("리스트뷰 아이템 삭제")
                        .setMessage("["+dataList.get(position)+"]를 정말로 삭제하시겠습니까?")
                        .setIcon(R.mipmap.ic_launcher_remove)
                        .setCancelable(false)
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();
            }
        });

        return convertView;
    }
}
