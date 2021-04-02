package com.example.sqlsozlukuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetayActivity extends AppCompatActivity {

    private TextView textViewing, textViewturkce;
    private Kelimeler kelime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        textViewing = findViewById(R.id.textViewing);
        textViewturkce = findViewById(R.id.textViewturkce);

        kelime = (Kelimeler)getIntent().getSerializableExtra("nesne");

        textViewing.setText(kelime.getIngilizce());
        textViewturkce.setText(kelime.getTurkce());
    }
}