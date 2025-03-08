package com.example.customlistview;

public class ItemModel {
    private String title;
    private int imageResId;

    public ItemModel(String title, int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }
}
