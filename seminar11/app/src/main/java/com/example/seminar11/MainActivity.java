package com.example.seminar11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    List<laptop> laptopList = new LinkedList<>();
    ArrayAdapter<laptop> adapter;

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    databaseLaptops db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.btnReset);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLaptopData();
            }
        });
        db = Room.databaseBuilder(this,databaseLaptops.class,"DatabaseLaptops").allowMainThreadQueries().build();

        ListView lv = findViewById(R.id.lv);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, laptopList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                laptop selectedLaptop = laptopList.get(position);
                laptopList.forEach((laptop -> laptop.setEsteSalvat(false)));
                selectedLaptop.setEsteSalvat(true);
                saveLaptopToSharedPreferences(selectedLaptop);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Laptop adaugat la favorite!", Toast.LENGTH_LONG).show();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                laptop selectedLaptop = laptopList.get(position);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.laptopDao().insert(selectedLaptop);

                    }
                }).start();
                Toast.makeText(getApplicationContext(), "Laptop salvat in DB", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    private void fetchLaptopData() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://pastebin.com/raw/RHg6EvGS");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    updateLaptopList(result.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateLaptopList(String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray jsonArray = new JSONArray(data);
                    laptopList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String model = jsonObject.getString("model");
                        float pret = (float) jsonObject.getDouble("pret");
                        int memorie = jsonObject.getInt("memorie");
                        laptop laptopObject = new laptop(model, pret, memorie);
                        laptopList.add(laptopObject);
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void saveLaptopToSharedPreferences(laptop laptopObject) {
        SharedPreferences sharedPreferences = getSharedPreferences("LaptopPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("model", laptopObject.getModel());
        editor.putFloat("pret", laptopObject.getPret());
        editor.putInt("memorie", laptopObject.getMemorie());
        editor.putBoolean("esteSalvat", laptopObject.isEsteSalvat());
        editor.apply();
    }

    public void spreDB(View view) {
        Intent intent = new Intent(this.getApplicationContext(), MainActivity2.class);
        startActivityForResult(intent, 101);
    }
}