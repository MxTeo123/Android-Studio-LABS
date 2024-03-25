package com.example.seminar5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent it=getIntent();
        int informatie = it.getIntExtra("metadata", 1);
        Bundle bundle = it.getExtras();
        int informatie2 = bundle.getInt("metadata");
        Toast.makeText(this, ""+informatie, Toast.LENGTH_LONG).show();

        Button creareMagazin = findViewById(R.id.btnCreeare);
        creareMagazin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nume = findViewById(R.id.etNume);
                EditText nrAngajati = findViewById(R.id.etNrAngajati);
                Magazin magazin = new Magazin(nume.getText().toString(), Integer.parseInt(nrAngajati.getText().toString()));
                Intent intent = new Intent();
                intent.putExtra("magazin", magazin);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}