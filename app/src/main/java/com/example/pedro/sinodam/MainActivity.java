package com.example.pedro.sinodam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View v = new View(this);
        switch (item.getItemId()){
            case R.id.mAdd:
                insertarPalabra(v);
                return true;
            case R.id.mBuscar:
                buscarPalabra(v);
                return true;
            case R.id.mSalir:
                salirAPP(v);
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

    public void insertarPalabra(View view) {
    }

    public void buscarPalabra(View view) {
    }


    public void salirAPP(View view) {
    }
}
