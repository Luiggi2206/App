package com.example.myproyect.actividades.clases;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class MostrarMensaje {

    public static void mensaje(String txt, Context context){
        AlertDialog.Builder ventana = new AlertDialog.Builder(context);
        ventana.setTitle("Información: ");
        ventana.setMessage(txt);
        ventana.setPositiveButton("ACEPTAR", null);
        ventana.create().show();
    }

    public static void mensaje(String txt, Context context, Class clas){
        AlertDialog.Builder ventana = new AlertDialog.Builder(context);
        ventana.setTitle("Información: ");
        ventana.setMessage(txt);
        ventana.setPositiveButton("ACEPTAR",  (dialogInterface, i) -> {
            Intent intent = new Intent(context, clas);
            context.startActivity(intent);
        });
        ventana.create().show();
    }
    public static void mensajeToast(String txt, Context context, Class clas){
        Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, clas);
        context.startActivity(intent);

    }

    public static void mensaje2(String txt, Context context, Class clas){
        AlertDialog.Builder ventana = new AlertDialog.Builder(context);
        ventana.setTitle("Información: ");
        ventana.setMessage(txt);
        ventana.setNegativeButton("NO", null);
        ventana.setPositiveButton("ACEPTAR",  (dialogInterface, i) -> {
            Intent intent = new Intent(context, clas);
            context.startActivity(intent);
        });
        ventana.create().show();
    }

}
