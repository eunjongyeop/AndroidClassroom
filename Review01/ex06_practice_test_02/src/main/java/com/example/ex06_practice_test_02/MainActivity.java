package com.example.ex06_practice_test_02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2213은종엽");

        editText = findViewById(R.id.editText);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("text", editText.getText().toString());
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 1) {
            Uri uri = Uri.parse("tel:0629496800");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        } else if (resultCode == 2) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
            startActivity(intent);
        }
    }
}
