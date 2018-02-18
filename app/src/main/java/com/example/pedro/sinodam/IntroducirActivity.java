package com.example.pedro.sinodam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IntroducirActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etPalabra, etSin1, etSin2;
    private ManipularFicheros mf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir);
        (findViewById(R.id.bAceptar)).setOnClickListener(this);
        (findViewById(R.id.bCancelar)).setOnClickListener(this);

        etPalabra = findViewById(R.id.etNPalabra);
        etSin1 = findViewById(R.id.etSin1);
        etSin2 = findViewById(R.id.etSin2);

        mf = new ManipularFicheros(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bCancelar:
                finish();
                break;
            case R.id.bAceptar:
                guardarPalabra();
                break;
        }
    }

    private void guardarPalabra() {
        mf.escribirFicheroInterno(etPalabra.getText().toString());
        String palabraYsinonimos = etPalabra.getText().toString()+"-"+etSin1.getText().toString()+"-"+etSin2.getText().toString();
        mf.escribirFicheroExterno(palabraYsinonimos);
        etPalabra.setText("");
        etSin1.setText("");
        etSin2.setText("");
    }
}
