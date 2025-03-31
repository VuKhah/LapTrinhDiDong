package com.example.customadaptergridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MonHocAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MonHoc> monHocList;

    public MonHocAdapter(Context context,
                         int layout, List<MonHoc> monHocList) {
        this.context = context;
        this.layout = layout;
        this.monHocList = monHocList;
    }

    @Override
    public int getCount() {
        return monHocList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //lấy context
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//gọi view chứa layout
        view = inflater.inflate(layout,null);
//ánh xạ view
        TextView textName = (TextView) view.findViewById(R.id.textViewName);
        TextView textDesc = (TextView) view.findViewById(R.id.textViewAddress);
        ImageView imagePic = (ImageView) view.findViewById(R.id.imageViewFood);
//gán giá trị
        MonHoc monHoc = monHocList.get(i);
        textName.setText(monHoc.getName());
        textDesc.setText(monHoc.getDesc());
        imagePic.setImageResource(monHoc.getPic());
//trả về view
        return view;
    }
}
