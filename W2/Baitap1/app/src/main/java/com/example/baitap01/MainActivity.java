package com.example.baitap01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private TextView textView;

    List<Integer> randomArr = new ArrayList<>();
    List<Integer> soChan = new ArrayList<>();
    List<Integer> soLe = new ArrayList<>();

    private void phanLoaiSoChanSoLe() {
        for (int number : randomArr) {
            if (number % 2 == 0) {
                soChan.add(number);
            } else {
                soLe.add(number);
            }
        }
        Log.d("soChan", "phanLoaiSoChanSoLe: " + soChan);
        Log.d("soLe", "phanLoaiSoChanSoLe: " + soLe);
    }

    private void soChanSoLe() {
        for (int i = 0; i < 20; i++) {
            randomArr.add(generateRandomNumbers());
        }
    }

    private int generateRandomNumbers() {
        // Tạo đối tượng Random
        Random random = new Random();
        // Sinh số nguyên ngẫu nhiên trong khoảng từ 0 đến 100
        return random.nextInt(101);
    }
    public void reverse(View view) {
        String input = editText.getText().toString().toUpperCase();

        // Tách chuỗi thành các từ
        String[] words = input.split(" ");

        // Đảo ngược thứ tự các từ trong mảng
        StringBuilder reversedSentence = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]).append(" ");
        }
        textView.setText(reversedSentence.toString().trim());
        Toast.makeText(this, reversedSentence, Toast.LENGTH_SHORT).show();
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button);
        editText = findViewById(R.id.input_text);
        textView = findViewById(R.id.text_View);

        soChanSoLe();
        phanLoaiSoChanSoLe();
    }
}