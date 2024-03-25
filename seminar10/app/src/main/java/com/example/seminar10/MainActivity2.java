package com.example.seminar10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void Adaugare(View view) {
        EditText etnrcamere = findViewById(R.id.etnrcamere);
        EditText etnretaj = findViewById(R.id.etnretaj);
        EditText etzona = findViewById(R.id.etzona);
        Button btnAdauga = findViewById(R.id.btnAdauga);
        Apartamente ap = new Apartamente(Integer.parseInt(etnrcamere.getText().toString()),
                Integer.parseInt(etnretaj.getText().toString()),
                etzona.getText().toString());
        Intent inte = new Intent();
        inte.putExtra("Apartament",ap);
        setResult(RESULT_OK, inte);
        finish();

    }
}