package com.example.customadaptergridview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    Button btnNhap;
    Button btnCapNhat;
    int vitri = -1;

    GridView gridView;
    ArrayList<MonHoc> arrayList;
    MonHocAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        gridView = (GridView) findViewById(R.id.gridview1);
//Thêm dữ liệu vào List

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//code yêu cầu
//i: trả về vị trí click chuột trên GridView -> iban đầu =0
                Toast.makeText(MainActivity.this, "" + i, Toast.LENGTH_SHORT).show();
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//code yêu cầu
//i: trả về vị trí click chuột trên GridView -> i ban đầu =0
                Toast.makeText(MainActivity.this, "Bạn đang nhấn giữ " + i + "-" + arrayList.get(i), Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        AnhXa();
//Tạo Adapter
        adapter = new MonHocAdapter(MainActivity.this,
                R.layout.row_monhoc,
                arrayList
        );
//truyền dữ liệu từ adapter ra listview
        gridView.setAdapter(adapter);
    }

    private void AnhXa() {
        gridView = (GridView) findViewById(R.id.gridview1);
        editText1 = (EditText) findViewById(R.id.editText1);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
//Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java", "Java 1", R.drawable.java1));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.c));
        arrayList.add(new MonHoc("PHP", "PHP 1", R.drawable.php));
        arrayList.add(new MonHoc("Kotlin", "Kotlin1", R.drawable.kotlin));
        arrayList.add(new MonHoc("Dart", "Dart 1", R.drawable.dart));
    }
}
