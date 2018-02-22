package com.example.pedro.sinodam;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BuscarActivity extends AppCompatActivity {
    LinearLayout listaPalabras, mostrarSinonimos;
    ArrayList<Palabras> palabras;
    ArrayList<TextView> listaTextView;
    TextView sin1,sin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        listaTextView = new ArrayList<>();
        ManipularFicheros mf = new ManipularFicheros(this);
        listaPalabras = findViewById(R.id.listaPalabras);
        mostrarSinonimos = findViewById(R.id.mostrarSinonimos);
        palabras = mf.leerFicheroExterno();
        sin1 = findViewById(R.id.tvSin1);
        sin2 = findViewById(R.id.tvSin2);

        if(!palabras.isEmpty()){
            mostrarListaPalabras();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("No hay palabras en el diccionario");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void mostrarListaPalabras(){
        for (Palabras p:palabras) {
            TextView tv = (TextView) getLayoutInflater().inflate(R.layout.textview_buscar,null);
            tv.setText(p.getPalabra());
            listaPalabras.addView(tv);
            listaTextView.add(tv);
        }
    }

    public void elegirPalabra(View v){
        TextView tv = (TextView) v;
        Palabras pElegida = buscarPalabra(tv.getText().toString());

        for (TextView t:listaTextView){
            if(!t.getText().toString().equals(tv.getText().toString())){
                t.setBackground(getResources().getDrawable(R.drawable.fondo_label));
                t.setTextColor(getResources().getColor(R.color.colorAccentDark));
            }
        }
        tv.setBackground(getResources().getDrawable(R.drawable.fondo_label_select));
        tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        mostrarSinonimos.setVisibility(View.VISIBLE);
        sin1.setText(pElegida.getSin1());
        sin2.setText(pElegida.getSin2());


    }

    private Palabras buscarPalabra(String palabra){
        for (Palabras p:palabras) {
            if(palabra.equals(p.getPalabra())){
                return p;
            }
        }
        return null;
    }


}
