package com.example.json_object_ex;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView txtName, txtDesc, txtPic;
    private static final String JSON_URL = "https://yourapi.com/data.json"; // Thay URL của bạn ở đây

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các TextView
        txtName = findViewById(R.id.txtName);
        txtDesc = findViewById(R.id.txtDesc);
        txtPic = findViewById(R.id.txtPic);

        // Kiểm tra kết nối Internet trước khi tải JSON
        if (isNetworkAvailable()) {
            new ReadJSONObject().execute(JSON_URL);
        } else {
            Toast.makeText(this, "Không có kết nối Internet!", Toast.LENGTH_SHORT).show();
        }
    }

    // Kiểm tra kết nối Internet
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    // AsyncTask để tải JSON từ URL
    private class ReadJSONObject extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                if (connection.getResponseCode() == 200) { // Kiểm tra phản hồi HTTP
                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        content.append(line);
                    }
                    bufferedReader.close();
                } else {
                    return "Error: " + connection.getResponseCode();
                }
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "URL không hợp lệ!";
            } catch (IOException e) {
                e.printStackTrace();
                return "Lỗi kết nối!";
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                String name = object.getString("name");
                String desc = object.getString("desc");
                String pic = object.getString("pic");

                // Hiển thị dữ liệu lên TextView
                txtName.setText("Tên: " + name);
                txtDesc.setText("Mô tả: " + desc);
                txtPic.setText("Ảnh: " + pic);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Lỗi phân tích JSON!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
