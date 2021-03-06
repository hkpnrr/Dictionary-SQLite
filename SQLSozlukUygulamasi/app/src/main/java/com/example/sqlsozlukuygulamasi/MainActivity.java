package com.example.sqlsozlukuygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Kelimeler> kelimelerListe;
    private KelimelerAdapter adapter;
    private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);

        toolbar.setTitle("DICTIONARY");

        setSupportActionBar(toolbar);

        veritabaniKopyala();

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        vt = new Veritabani(this);

        kelimelerListe = new Kelimelerdao().tumKelimeler(vt);


        adapter = new KelimelerAdapter(this,kelimelerListe);

        rv.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbarmenu,menu);

        MenuItem item = menu.findItem(R.id.action_ara);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(this);




        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        aramaYap(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        aramaYap(newText);
        return false;
    }

    public void veritabaniKopyala(){


        DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseCopyHelper.openDataBase();
    }

    public void aramaYap(String aramaKelime){



        kelimelerListe = new Kelimelerdao().kelimeAra(vt,aramaKelime);

        adapter = new KelimelerAdapter(this,kelimelerListe);
        rv.setAdapter(adapter);


    }
}