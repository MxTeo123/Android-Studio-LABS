package com.example.seminar6;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private final List<Bitmap> images;

    public CustomAdapter(Context context, List<String> mesaje, List<Bitmap> images) {
        super(context, 0, mesaje);
        this.images = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageItem);
        TextView textView = convertView.findViewById(R.id.textItem);

        imageView.setImageBitmap(images.get(position));
        textView.setText(getItem(position));

        return convertView;
    }
}
