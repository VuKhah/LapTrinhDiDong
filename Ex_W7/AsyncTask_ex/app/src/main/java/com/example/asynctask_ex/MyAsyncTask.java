package com.example.asynctask_ex;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextParent;

    // Tạo constructor
    public MyAsyncTask(Activity context) {
        this.contextParent = context;
    }

    // Trước khi tiến trình chạy
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextParent, "Bắt đầu tiến trình", Toast.LENGTH_SHORT).show();
    }

    // Xử lý chạy nền
    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100); // Giả lập tiến trình chạy
            publishProgress(i); // Cập nhật tiến trình
        }
        return null;
    }

    // Cập nhật giao diện khi có tiến trình mới
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        ProgressBar progressBar = contextParent.findViewById(R.id.prbDemo);

        int number = values[0];
        progressBar.setProgress(number);
        TextView textView = contextParent.findViewById(R.id.txtStatus);

        textView.setText(number + "%");
    }

    // Sau khi tiến trình kết thúc
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(contextParent, "Đã hoàn thành!", Toast.LENGTH_SHORT).show();
    }
}
