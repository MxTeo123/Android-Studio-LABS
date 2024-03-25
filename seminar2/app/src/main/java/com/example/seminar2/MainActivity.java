package com.example.seminar2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btnApasa);
        StringBuilder builder = new StringBuilder();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Esti smecher terminator!", Toast.LENGTH_LONG).show();
                EditText et = findViewById(R.id.editTextIntrodus);
                String textIntrodus = et.getText().toString();
                builder.append(textIntrodus);
                CheckBox cb=findViewById(R.id.checkboxBifeaza);
                if (cb.isChecked()) {
                    builder.append("Bifa este pusa!");
                } else {
                    builder.append("N ai bifat nimic boss");
                }
                RatingBar rb = findViewById(R.id.rbStele);
                builder.append(""+rb.getNumStars());
                Switch sw = findViewById(R.id.switchView);
                if (sw.isChecked()) {
                    builder.append("Switch este pus!");
                } else {
                    builder.append("N ai switch boss");
                }
                RadioGroup grup = findViewById(R.id.rdGrup);
                RadioButton radioButton = findViewById(grup.getCheckedRadioButtonId());
                builder.append(radioButton.getText());
                TextView tcRezultat = findViewById(R.id.tvRezultat);
                tcRezultat.setText(builder);
            }
        });
    }
}