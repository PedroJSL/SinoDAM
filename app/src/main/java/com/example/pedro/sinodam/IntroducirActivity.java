package com.example.pedro.sinodam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IntroducirActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int PETICION_ESCRITURA_FICHERO_EXTERNO = 1;
    private EditText etPalabra, etSin1, etSin2;
    private ManipularFicheros mf;
    private boolean permisoConcedido = true;
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
             /*   peticionPermiso();
                if(!permisoConcedido){
                    return;
                }
                mf = new ManipularFicheros(this,permisoConcedido);*/
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

  /*  private void peticionPermiso(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},PETICION_ESCRITURA_FICHERO_EXTERNO);
        }else{
            permisoConcedido = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PETICION_ESCRITURA_FICHERO_EXTERNO:
                if(grantResults!=null&&grantResults.length>0) {
                    permisoConcedido = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    mf = new ManipularFicheros(this,permisoConcedido);
                    guardarPalabra();
                }
        }
    }*/
}
