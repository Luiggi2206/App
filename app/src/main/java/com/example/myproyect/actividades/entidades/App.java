package com.example.myproyect.actividades.entidades;

import android.content.Context;
import com.example.myproyect.actividades.modelos.DAO_App;

public class App {
    public static  String nombre="Los_jardines";
    public static  int canUser=0;
    public static boolean recordarS = false;
    public static String correo= null;
    public static String clave = null;

    public static void loadtDatos(Context context){
        DAO_App dao_app = new DAO_App(context);
        dao_app.abrirBD(); //abrir bd
        if(dao_app.getsRS().equals("true")){
            recordarS = true;
            correo = dao_app.getDatosRS().getCorreo();
            clave = dao_app.getDatosRS().getClave();
        }else {
            recordarS = false;
            correo= null;
            clave = null;
        }

        dao_app.cerrarBD(); //cerrar bd
    }

    public static void uploadDatos(Context context, boolean RS, String correo, String clave){
        DAO_App dao_app = new DAO_App(context);
        dao_app.abrirBD(); //abrir bd
        dao_app.setRS(RS);
        if(RS)
            dao_app.setDatosRS(correo, clave);
        else
            dao_app.setDatosRS(null, null);

        dao_app.cerrarBD();

    }





}
