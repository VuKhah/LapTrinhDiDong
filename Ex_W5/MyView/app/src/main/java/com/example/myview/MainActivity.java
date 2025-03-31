package com.example.customlistview;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("Apple", R.drawable.apple));
        items.add(new Item("Banana", R.drawable.banana));
        items.add(new Item("Cherry", R.drawable.cherry));

        CustomAdapter adapter = new CustomAdapter(this, items);
        listView.setAdapter(adapter);
    }
}