package com.example.pedro.sinodam;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
   // private boolean permisoConcedido;


   // public ManipularFicheros(Context context, boolean permisoConcedido) {
    public ManipularFicheros(Context context){
        this.context = context;
        //this.permisoConcedido = permisoConcedido;

        archivoExterno = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"/palabrasysinonimos.txt");

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
       /*if (sdDisponible && sdAccesoEscritura&&permisoConcedido) {
            try{
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivoExterno)));
                bw.write(p);
                bw.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(!sdDisponible){
            Toast.makeText(context,context.getString(R.string.error_sd),Toast.LENGTH_SHORT).show();
        }else if(!sdAccesoEscritura){
            Toast.makeText(context, R.string.permiso_denegado,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, R.string.error_sd_denegado,Toast.LENGTH_SHORT).show();
        }*/

        if (sdDisponible && sdAccesoEscritura) {
            try{
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivoExterno)));
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

    public ArrayList<String> leerFicheroExterno(){
        ArrayList<String> lineas = new ArrayList<>();
        if(sdDisponible && sdAccesoEscritura){
            try {
                String linea;
                BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(archivoExterno.getAbsolutePath())));
                while((linea=br.readLine())!=null){
                    lineas.add(linea);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return lineas;
    }




}
