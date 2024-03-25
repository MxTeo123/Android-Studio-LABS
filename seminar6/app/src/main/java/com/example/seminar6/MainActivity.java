package com.example.seminar6;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private List<String> mesaje;
    private List<String> links;
    private List<Bitmap> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mesaje = new ArrayList<>();

        links = new ArrayList<>();
        links.add("https://csie.ase.ro/wp-content/uploads/2020/10/csie3.jpg");
        links.add("https://ism.ase.ro/wp-content/uploads/2015/10/Logo-ISM_medium.png");
        links.add("https://ism.ase.ro/wp-content/uploads/2020/10/IoT-Security.png");

        for (String link: links) {
            String[] parts = link.split("/");
            mesaje.add(parts[parts.length - 1]);
        }

        ExecutorService executor = Executors.newFixedThreadPool(links.size());
        Handler handler = new Handler(Looper.myLooper());

        for (String link : links) {
            executor.execute(() -> {
                Bitmap image;
                try {
                    URL url = new URL(link);
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    image = BitmapFactory.decodeStream(is);
                    synchronized (imageList) {
                        imageList.add(image);
                    }
                    if (imageList.size() == links.size()) {
                        handler.post(() -> {
                            ListView listView = findViewById(R.id.listaMesaje);
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), mesaje, imageList);
                            listView.setAdapter(adapter);
                        });
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }
}

