package com.example.pedro.sinodam;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class ManipularFicheros {
    private Context context;
    private File archivoExterno;
    private boolean sdDisponible = false;
    private boolean sdAccesoEscritura = false;


    public ManipularFicheros(Context context){
        this.context = context;
        File sd = context.getExternalFilesDir(null);
        archivoExterno = new File(sd.getAbsolutePath(),"palabrasysinonimos.txt");

        String estado = Environment.getExternalStorageState();
        switch (estado) {
            case Environment.MEDIA_MOUNTED:
                sdDisponible = true;
                sdAccesoEscritura = true;
                break;
            case Environment.MEDIA_MOUNTED_READ_ONLY:
                sdDisponible = true;
                sdAccesoEscritura = false;
                break;
            default:
                sdDisponible = false;
                sdAccesoEscritura = false;
                break;
        }

    }

    public void escribirFicheroInterno(String p) {
        String file = context.getFilesDir().getPath() + "palabras.txt";
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file))));
            bw.write(p);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirFicheroExterno(String p) {


        if (sdDisponible && sdAccesoEscritura) {
            try{
                Log.d("Ruta fichero externo",archivoExterno.getAbsolutePath());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivoExterno,true)));
                bw.write(p);
                bw.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(!sdDisponible){
            Toast.makeText(context,context.getString(R.string.error_sd),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, R.string.permiso_denegado,Toast.LENGTH_SHORT).show();
        }


    }

    public ArrayList<Palabras> leerFicheroExterno(){
        ArrayList<Palabras> palabras = new ArrayList<>();
        if(sdDisponible && sdAccesoEscritura && archivoExterno.exists()){
            try {
                String linea;
                String[] sPalabra;
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivoExterno)));
                while((linea=br.readLine())!=null){
                    sPalabra = linea.split("-");
                    Palabras p = new Palabras(sPalabra[0],sPalabra[1],sPalabra[2]);
                    palabras.add(p);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return palabras;
    }




}
