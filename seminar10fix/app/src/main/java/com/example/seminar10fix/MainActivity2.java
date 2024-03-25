package com.example.seminar10fix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void salveazaPreferinte(View view) {
        try {
            EditText dimtext = findViewById(R.id.dimensiuneText);
            EditText culfundal = findViewById(R.id.culoareFundal);
            Preferinte p = new Preferinte(Integer.parseInt(dimtext.getText().toString()), culfundal.getText().toString());

            SharedPreferences sp = getSharedPreferences("date", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("dimText", p.getDimensiuneText());
            editor.putString("culFundal", p.getCuloareFundal());
            editor.commit();
            setResult(RESULT_OK);
            finish();
        } catch (Exception ex) {
            Toast.makeText(this.getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}