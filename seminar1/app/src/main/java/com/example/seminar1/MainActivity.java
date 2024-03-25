package com.example.seminar1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Ciclu de viata", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Ciclu de viata", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Ciclu de viata", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Ciclu de viata", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("Ciclu de viata", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.wtf("Ciclu de viata", "onRestart");
    }
}