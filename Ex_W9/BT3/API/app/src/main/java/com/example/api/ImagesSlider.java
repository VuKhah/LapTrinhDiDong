package com.example.api;

import java.io.Serializable;

public class ImagesSlider implements Serializable {
    private int id;
    private int position;
    private String avatar; // Đường dẫn ảnh

    public String getAvatar() {
        return avatar;
    }
}

