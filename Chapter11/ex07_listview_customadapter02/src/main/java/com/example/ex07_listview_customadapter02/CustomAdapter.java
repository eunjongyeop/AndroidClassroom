package com.example.ex07_listview_customadapter02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int resource;
    ArrayList<ItemVO> dataList;

    public CustomAdapter(Context context, int resource, ArrayList<ItemVO> dataList) {
        this.context = context;
        this.resource = resource;
        this.dataList = dataList;
    }

    // 리스트뷰의 아이템 개수 반환
    @Override
    public int getCount() {
        return dataList.size();
    }

    // 리스트뷰의 아이템 반환
    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 화면에 보여지는 리스트뷰의 아이템 표시를 위한 뷰 반환
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 재사용 가능한 뷰가 없으면 새로 생성
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);   // R.layout.row xml을 view 객체로 생성
        }

        // 반환할 View를 설정

        // converView의 구성요소인 view들을 참조변수에 연결
        ImageView imageViewIcon = convertView.findViewById(R.id.imageViewIcon);
        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewContent = convertView.findViewById(R.id.textViewContent);
        ImageView imageViewIcon2 = convertView.findViewById(R.id.imageViewIcon2);

        // imageViewIcon 세팀 (type에 따리 doc, img, file) 아이콘 세팀
        switch (dataList.get(position).getTypeStr()) {
            case "doc":
                imageViewIcon.setImageResource(R.drawable.ic_type_doc);
                break;
            case "img":
                imageViewIcon.setImageResource(R.drawable.ic_type_image);
                break;
            case "file":
                imageViewIcon.setImageResource(R.drawable.ic_type_file);
                break;
        }

        textViewTitle.setText(dataList.get(position).getTitleStr());
        textViewContent.setText(dataList.get(position).getContentStr());
        imageViewIcon2.setImageResource(R.drawable.ic_menu);

        return convertView;
    }
}
