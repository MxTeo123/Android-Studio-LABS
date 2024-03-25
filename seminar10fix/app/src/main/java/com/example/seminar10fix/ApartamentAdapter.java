package com.example.seminar10fix;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class ApartamentAdapter extends BaseAdapter {
    List<Apartament> apartamentList = new LinkedList<>();
    Context context;

    private final int textSize;
    private final String backgroundColor;

    public ApartamentAdapter(List<Apartament> apartamentList, Context context, int textSize, String backgroundColor) {
        this.apartamentList = apartamentList;
        this.context = context;
        this.textSize = textSize;
        this.backgroundColor = backgroundColor;
    }


    public void setApartamentList(List<Apartament> apartamentList) {
        this.apartamentList = apartamentList;
    }

    @Override
    public int getCount() {
        return apartamentList.size();
    }

    @Override
    public Object getItem(int i) {
        return apartamentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.apartament, viewGroup, false);
        TextView tv = v.findViewById(R.id.idtv);
        tv.setText(getItem(i).toString());
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tv.setBackgroundColor(Color.parseColor(backgroundColor));
        return v;
    }
}
