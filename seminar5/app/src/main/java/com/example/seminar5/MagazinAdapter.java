package com.example.seminar5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MagazinAdapter extends BaseAdapter {
    private List<Magazin> magazine;
    private Context context;

    public MagazinAdapter(List<Magazin> magazine, Context context) {
        this.magazine = magazine;
        this.context = context;
    }

    @Override
    public int getCount() {
        return magazine.size();
    }

    @Override
    public Object getItem(int i) {
        return magazine.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_row, viewGroup, false);
        Magazin magazin = (Magazin)getItem(i);
        TextView tvNume = v.findViewById(R.id.numeETAfisat);
        TextView tvNrAngajati = v.findViewById(R.id.nrAngajatiEtAfisat);
        tvNume.setText(magazin.getNume());
        tvNrAngajati.setText(""+magazin.getNrAngajati());
        return v;
    }
}
