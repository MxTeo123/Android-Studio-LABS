package com.example.seminar10fix;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actualizareLV();

        ListView lv = findViewById(R.id.lv);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteItem(position);
                actualizareLV();
                return true;
            }
        });
    }

    private void deleteItem(int position) {
        SharedPreferences sp = getSharedPreferences("date", MODE_PRIVATE);
        int apNumber = sp.getInt("apNumber", 0);

        SharedPreferences.Editor editor = sp.edit();
        for (int i = position; i < apNumber - 1; i++) {
            String nextItem = sp.getString("Apartament" + (i + 1), null);
            editor.putString("Apartament" + i, nextItem);
        }
        editor.remove("Apartament" + (apNumber - 1));
        editor.putInt("apNumber", apNumber - 1);
        editor.apply();
    }



    public void deschideActivity2(View view) {
        Intent intent = new Intent(this.getApplicationContext(), MainActivity2.class);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            actualizareLV();
            Toast.makeText(this.getApplicationContext(), "Preferintele au fost salvate cu succes!", Toast.LENGTH_LONG).show();
        }
    }

    void actualizareLV() {
        SharedPreferences sp = getSharedPreferences("date", MODE_PRIVATE);
        Preferinte p = new Preferinte(sp.getInt("dimText", 16), sp.getString("culFundal", "#FFFFFF"));
        List<Apartament> apartamentList = new ArrayList<>();
        int apNumber = sp.getInt("apNumber", 0);
        for (int i = 0; i < apNumber; i++) {
            String serializedData = sp.getString("Apartament" + i, null);
            if (serializedData != null) {
                Apartament apartament = deserializeApartament(serializedData);
                apartamentList.add(apartament);
            }
        }
        ApartamentAdapter aa = new ApartamentAdapter(apartamentList, this.getApplicationContext(), p.getDimensiuneText(), p.getCuloareFundal());
        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(aa);
    }

    private String serializeApartament(Apartament apartament) {
        Parcel parcel = Parcel.obtain();
        apartament.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();
        parcel.recycle();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private Apartament deserializeApartament(String serializedData) {
        byte[] bytes = Base64.decode(serializedData, Base64.DEFAULT);
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0);

        Apartament apartament = Apartament.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return apartament;
    }

    private void saveApartaments(List<Apartament> apartamentList) {
        SharedPreferences sp = getSharedPreferences("date", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        for (int i = 0; i < apartamentList.size(); i++) {
            String serializedApartament = serializeApartament(apartamentList.get(i));
            editor.putString("Apartament" + i, serializedApartament);
        }
        editor.putInt("apNumber", apartamentList.size());
        editor.apply();
    }

    public void resetLV(View view) {
        List<Apartament> apartamentList = new LinkedList<>();
        apartamentList.add(new Apartament(10, "Cora Bratianu", 73.75, 80000));
        apartamentList.add(new Apartament(2, "Tomis Plus", 80.13, 100000));
        apartamentList.add(new Apartament(1, "Faleza Nord", 25.00, 40000));
        apartamentList.add(new Apartament(13, "Complex Delfinul", 180.84, 300000));
        apartamentList.add(new Apartament(3, "Congostantos", 15.15, 20000));
        saveApartaments(apartamentList);
        actualizareLV();
    }
}