package com.example.seminar10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Apartamente> apartamenteList= new LinkedList<>();
    AdaptorApartamente aa = new AdaptorApartamente();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = findViewById(R.id.lva);
        aa = new AdaptorApartamente(this.getApplicationContext(), apartamenteList);
        lv.setAdapter(aa);
    }



    public void adaugareApartment(View view) {
        Intent intent=  new Intent(this.getApplicationContext(), MainActivity2.class);
        startActivityForResult(intent, 101);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==101){
            Apartamente ap = data.getParcelableExtra("Apartament");
            apartamenteList.add(ap);
            aa.setApartamenteList(apartamenteList);
            aa.notifyDataSetChanged();
        }
    }
}