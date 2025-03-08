package com.example.sqlite;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private List<Note> noteList;
    private NoteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo DatabaseHandler
        db = new DatabaseHandler(this);
        // Lấy danh sách ghi chú từ database
        noteList = db.getAllNotes();

        ListView listViewNotes = findViewById(R.id.listViewNotes);

        // Khởi tạo adapter và gán cho ListView
        adapter = new NoteAdapter(this, noteList,db);
        listViewNotes.setAdapter(adapter);

        // Ánh xạ nút "Add" từ layout
        Button buttonAdd = findViewById(R.id.buttonAdd);

        // Xử lý sự kiện khi nhấn nút "Add"
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note("New Title", "New Content");
                db.addNote(note);
                noteList.add(note);
                adapter.notifyDataSetChanged();
            }
        });
    }

}