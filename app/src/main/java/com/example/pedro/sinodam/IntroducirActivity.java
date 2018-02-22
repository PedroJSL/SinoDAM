package com.example.pedro.sinodam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IntroducirActivity extends AppCompatActivity implements View.OnClickListener {
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
        switch (v.getId()) {
            case R.id.bCancelar:
                finish();
                break;
            case R.id.bAceptar:
                guardarPalabra();
                break;
        }
    }

    private void guardarPalabra() {
        if (!etPalabra.getText().toString().equals("")) {
            mf.escribirFicheroInterno(etPalabra.getText().toString());

            String palabra = etPalabra.getText().toString();
            String sin1;
            if (etSin1.getText().toString().equals("")) {
                sin1 = " ";
            } else {
                sin1 = etSin1.getText().toString();
            }
            String sin2;
            if (etSin2.getText().toString().equals("")) {
                sin2 = " ";
            } else {
                sin2 = etSin2.getText().toString();
            }
            String palabraYsinonimos = palabra + "-" + sin1 + "-" + sin2 + "\n";
            mf.escribirFicheroExterno(palabraYsinonimos);

            etPalabra.setText("");
            etSin1.setText("");
            etSin2.setText("");
        }else{
            Toast.makeText(this,getString(R.string.int_palabra),Toast.LENGTH_SHORT).show();
        }
    }

}
