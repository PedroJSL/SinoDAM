package com.example.pedro.sinodam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (findViewById(R.id.bBuscar)).setOnClickListener(this);
        (findViewById(R.id.bInsertar)).setOnClickListener(this);
        (findViewById(R.id.bSalir)).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mAdd:
                insertarPalabra();
                return true;
            case R.id.mBuscar:
                buscarPalabra();
                return true;
            case R.id.mSalir:
                salirAPP();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bInsertar:
                insertarPalabra();
                break;
            case R.id.bBuscar:
                buscarPalabra();
                break;
            case R.id.bSalir:
                salirAPP();
                break;
        }
    }


    public void insertarPalabra() {
        Intent vInsertar = new Intent(this,IntroducirActivity.class);
        startActivity(vInsertar);
    }

    public void buscarPalabra() {
        Intent vBuscar = new Intent(this,BuscarActivity.class);
        startActivity(vBuscar);
    }


    public void salirAPP() {
        finish();
    }
}
