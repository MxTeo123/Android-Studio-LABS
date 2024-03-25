package com.example.seminar10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class AdaptorApartamente extends BaseAdapter {

    Context context;
    List<Apartamente> apartamenteList = new LinkedList<>();


    public AdaptorApartamente(Context context, List<Apartamente> apartamenteList) {
        this.context = context;
        this.apartamenteList = apartamenteList;
    }

    public AdaptorApartamente() {
    }



    @Override
    public int getCount() {
        return apartamenteList.size();
    }

    @Override
    public Object getItem(int position) {
        return apartamenteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.apartament, parent, false);
        TextView tv = v.findViewById(R.id.tv);
        tv.setText(((Apartamente)getItem(position)).toString());
        return v;
    }

    public void setApartamenteList(List<Apartamente> apartamenteList) {
        this.apartamenteList = apartamenteList;
    }
}
