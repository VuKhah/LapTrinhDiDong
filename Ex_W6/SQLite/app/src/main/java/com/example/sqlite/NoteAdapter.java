package com.example.sqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {
    private Context context;
    private List<Note> notes;
    private DatabaseHandler db;

    public NoteAdapter(Context context, List<Note> notes, DatabaseHandler db) {
        super(context, 0, notes);
        this.context = context;
        this.notes = notes;
        this.db = db;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        }

        // Lấy đối tượng Note tại vị trí hiện tại
        final Note note = notes.get(position);

        // Ánh xạ các view trong layout note_item.xml
        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewContent = convertView.findViewById(R.id.textViewContent);
        Button buttonEdit = convertView.findViewById(R.id.buttonEdit);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        // Đặt dữ liệu vào các view
        textViewTitle.setText(note.getTitle());
        textViewContent.setText(note.getContent());

        // Xử lý sự kiện khi nhấn nút "Edit"
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditNoteDialog(note);
            }
        });

        // Xử lý sự kiện khi nhấn nút "Delete"
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa ghi chú khỏi database
                db.deleteNote(note);

                // Xóa ghi chú khỏi danh sách
                notes.remove(note);

                // Cập nhật lại ListView
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    // Hiển thị dialog để chỉnh sửa ghi chú
    private void showEditNoteDialog(final Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Note");

        // Tạo layout cho dialog
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_note, null);
        final EditText editTextTitle = view.findViewById(R.id.editTextTitle);
        final EditText editTextContent = view.findViewById(R.id.editTextContent);

        // Điền dữ liệu hiện tại vào dialog
        editTextTitle.setText(note.getTitle());
        editTextContent.setText(note.getContent());

        builder.setView(view);

        // Xử lý khi nhấn nút "Save"
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy dữ liệu mới từ dialog
                String newTitle = editTextTitle.getText().toString();
                String newContent = editTextContent.getText().toString();

                // Cập nhật ghi chú trong database
                note.setTitle(newTitle);
                note.setContent(newContent);
                db.updateNote(note);

                // Cập nhật lại ListView
                notifyDataSetChanged();
            }
        });

        // Xử lý khi nhấn nút "Cancel"
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Hiển thị dialog
        builder.create().show();
    }
}