package com.example.seminar11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ArrayAdapter<laptop> adapter;
    databaseLaptops db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView lv2 = findViewById(R.id.lv2);
        lv2.setAdapter(adapter);

        db = Room.databaseBuilder(getApplicationContext(), databaseLaptops.class, "DatabaseLaptops").build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<laptop> laptops = db.laptopDao().getAllLaptops();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, laptops);
                        lv2.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    public void stopActivate(View view) {
        finish();
    }
}