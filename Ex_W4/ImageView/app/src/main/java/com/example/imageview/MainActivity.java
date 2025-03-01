package com.example.imageview;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

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
        // Danh sách hình ảnh trong drawable
        int[] images = {R.drawable.pop_1, R.drawable.pop_2, R.drawable.pop_3};

        // Chọn ngẫu nhiên một hình ảnh
        int randomImage = images[new Random().nextInt(images.length)];

        // Đặt hình nền cho layout chính
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setBackgroundResource(randomImage);
    }

}