package com.example.imageview;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khai báo UI
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        Switch switchBackground = findViewById(R.id.switchBackground);

        // Danh sách hình ảnh trong drawable
        int[] images = {R.drawable.pop_1, R.drawable.pop_2, R.drawable.pop_3};

        // Xử lý sự kiện khi bật/tắt Switch
        switchBackground.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Chọn ngẫu nhiên một hình ảnh
                int randomImage = images[new Random().nextInt(images.length)];
                mainLayout.setBackgroundResource(randomImage);
            }
        });
    }
}
