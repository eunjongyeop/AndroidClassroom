package com.example.ex06_excersie10_20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDial, btnWeb, btnGoogle, btnSearch, btnSms, btnPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDial = findViewById(R.id.btnDial);
        btnWeb = findViewById(R.id.btnWeb);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnSearch = findViewById(R.id.btnSearch);
        btnSms = findViewById(R.id.btnSms);
        btnPhoto = findViewById(R.id.btnPhoto);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri;
                Intent intent;

                switch (v.getId()) {
                    case R.id.btnDial :
                        uri = Uri.parse("tel:0101234567");
                        intent = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(intent);
                        break;
                    case R.id.btnWeb :
                        uri = Uri.parse("http://www.naver.com");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.btnGoogle :
                        uri = Uri.parse("https://goo.gl/maps/h7yFGRK8Czqc6YRC8");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.btnSearch :
                        intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, "안드로이드");
                        startActivity(intent);
                        break;
                    case R.id.btnSms :
                        intent = new Intent(Intent.ACTION_SENDTO);
                        intent.putExtra("sms_body", "안녕하세요");
                        intent.setData(Uri.parse("smsto:" + Uri.encode("010-1234-4567")));
                        startActivity(intent);
                        break;
                    case R.id.btnPhoto :
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(intent);
                        break;
                }
            }
        };

        btnDial.setOnClickListener(listener);
        btnWeb.setOnClickListener(listener);
        btnGoogle.setOnClickListener(listener);
        btnSearch.setOnClickListener(listener);
        btnSms.setOnClickListener(listener);
        btnPhoto.setOnClickListener(listener);
    }
}
