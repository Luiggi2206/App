package com.example.myproyect.actividades.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.myproyect.actividades.entidades.App;
import com.example.myproyect.actividades.entidades.Usuario;
import com.example.myproyect.actividades.util.AppBD;

public class DAO_App {
    AppBD appBD;
    SQLiteDatabase db;
    Context context;

    public DAO_App(Context context){
        appBD = new AppBD(context);
        this.context = context;
    }
    public void abrirBD(){
        db = appBD.getWritableDatabase();
    }
    public void cerrarBD(){
        appBD.close();
    }

    public boolean setRS(boolean bool){
        //CAMBIAR EL ESTADO DE RECORDAR sesión - login
        long r;
        String str=null;
        if(bool) str = "true";
            else str = "false";
        try{
            ContentValues values = new ContentValues();
            values.put("recordarSesion", str);
            r = db.update("tb_app",values, "nombre="+ "'"+App.nombre+"'", null);
            if(r<=0) return false;
            else return true;
        }catch (Exception e){
            Log.d("DAO_APP", "ERROR UPDATE setRS: "+e);
            return false;
        }

    }

    public String getsRS(){ //OBTENER SESION GUARDADA
        String s=null;
        try{
             Cursor c = db.rawQuery("SELECT * FROM tb_app", null);
             c.moveToFirst();
             return c.getString(2);
        }catch (Exception e){
            Log.d("DAO_APP", "ERROR getRS(): "+e.getMessage());
        }
        return s;
    }
    public boolean setDatosRS(String correo, String clave){ //CAMBIAR DATOS
        long r;
        try{
            ContentValues values = new ContentValues();
            values.put("correo", correo);
            values.put("clave", clave);
            r = db.update("tb_app",values, "nombre="+"'"+App.nombre+"'", null);
            if(r<=0) return false;
            else return true;
        }catch (Exception e){
            Log.d("DAO_APP", "ERROR UPDATE setDatosRS: "+e);
            return false;
        }
    }

    public Usuario getDatosRS(){ // OBTENER DATOS
        Usuario usuario= null;
        try{
            Cursor c = db.rawQuery("SELECT * FROM tb_app", null);
            c.moveToFirst();
            usuario = new Usuario(c.getString(3), c.getString(4));
        }catch (Exception e){
            Log.d("DAO_app", "getDatosRS(): "+e.getMessage());
        }
        return usuario;
    }


}
