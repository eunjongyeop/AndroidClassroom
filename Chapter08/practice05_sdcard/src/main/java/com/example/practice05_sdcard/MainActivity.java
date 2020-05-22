package com.example.practice05_sdcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnRead, btnWrite;
    EditText editText;
    TextView textView;

    // 권환 목록 배열
    String[] permissionList = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // 외부 저장소에 읽기/쓰기할 디렉토리 참조 변수 생성
    File appSpecificExternalDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        // 권환 체크
        checkPermissions();

        //region 외부 저장장치의 사용 가능 여부 체크
        if(isExternalStorageWritable()) {
            Toast.makeText(this, "외부 저장소 쓰기 가능", Toast.LENGTH_SHORT).show();
        }
        if(isExternalStorageReadable()) {
            Toast.makeText(this, "외부 저장소 읽기 가능", Toast.LENGTH_SHORT).show();
        }
        //endregion

        //region 외부 저장소 앱별 디렉토리 접근  getExternalFileDir()
        File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(this, null);
        File primaryExternalStorage = externalStorageVolumes[0];  // 기본 외부 저장장치
        Log.d("MainActivity", String.valueOf(primaryExternalStorage.isFile()));
        Log.d("MainActivity", String.valueOf(primaryExternalStorage.isDirectory()));
        Log.d("MainActivity", primaryExternalStorage.getName());
        Log.d("MainActivity", primaryExternalStorage.getAbsolutePath());
        Log.d("MainActivity", primaryExternalStorage.getPath());
        Log.d("MainActivity", String.valueOf(primaryExternalStorage.canRead()));
        Log.d("MainActivity", String.valueOf(primaryExternalStorage.canWrite()));
        //endregion

        // 문서 파일을 저장할 myDocument 디렉토리 생성
        appSpecificExternalDir = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "myDocuments");
        Log.d("MainActivity", appSpecificExternalDir.getAbsolutePath());

        //region 외부 저장소 앱별 비공개 디렉토리에 파일 작성
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // appSpecificExternalDir 실제 디렉토리 생성
                if (appSpecificExternalDir.mkdir() == false) {
                    Log.d("MainActivity", "myDocuments 디렉토리 생성 실패");
                }

                //region 실제 텍스트 파일 생성 및 텍스트 쓰기
                final File myDocumentFile1 = new File(appSpecificExternalDir, "document1.txt");
                try(FileOutputStream fos = new FileOutputStream(myDocumentFile1, false);) {
                    fos.write(editText.getText().toString().getBytes());  // editText의 입력값을 파일에 쓰기
                    editText.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //endregion

                /*//region 외부 저장소 앱별 비공개 디렉토리에서 파일 읽기
                btnRead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File readfile = new File(appSpecificExternalDir, "document1.txt")
                        try(FileInputStream fis = new FileInputStream(readfile)) {
                            int i;
                            StringBuilder sb = new StringBuilder();
                            while((i = fis.read()) != -1) {
                                sb.append((char)i);
                            }
                            textView.setText(sb.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //endregion*/

                //region 외부 저장소 앱별 비공개 디렉토리에서 파일 읽기 - 문자 단위 읽기
                btnRead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File readfile = new File(appSpecificExternalDir, "document1.txt");
                        try(FileReader fileReader = new FileReader(    readfile)) {
                            int i;
                            StringBuilder sb = new StringBuilder();
                            while((i = fileReader.read()) != -1) {
                                sb.append((char)i);
                            }
                            textView.setText(sb.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //endregion
            }
        });

    }

    private void checkPermissions() {
        for(String permission : permissionList) {
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, permission+" 권환 없음", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, permissionList, 0);
            }
            else {
                Toast.makeText(this, "권환 있음", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}
