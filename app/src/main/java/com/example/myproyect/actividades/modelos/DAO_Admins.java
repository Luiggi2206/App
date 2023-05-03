package com.example.myproyect.actividades.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myproyect.actividades.entidades.Admin;
import com.example.myproyect.actividades.entidades.Usuario;
import com.example.myproyect.actividades.util.AppBD;

import java.util.ArrayList;
import java.util.List;

public class DAO_Admins {
    AppBD appBD;
    SQLiteDatabase db;
    Context context;
    final String tabla="tb_admins";

    public DAO_Admins(Context context){
        appBD = new AppBD(context);
        this.context = context;
    }

    public void abrirBD(){
        db = appBD.getWritableDatabase();
    }

    public List<Admin> listarAdmins(){
        List<Admin> listaAdmins = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM tb_admins", null);
            while(c.moveToNext()){
                listaAdmins.add(new Admin
                        (c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)
                        )
                );
            }
        }catch (Exception e){
            Log.d("LISTAR_ADMINS", "Error listarAdmins: "+e);
        }
        if(listaAdmins.size()==0){
            Log.d("LISTAR_ADMINS", "LISTA VACÍA");
        }else
            Log.d("LISTAR_ADMINS", "Cantidad: "+listaAdmins.size());

        return listaAdmins;

    }
    public boolean buscarAdminDNI(String dni){
        List<Admin> listaAdmins = new ArrayList<>();
        listaAdmins = listarAdmins();
        if(listaAdmins.size() == 0) return false;
        else{
            for(int i=0 ; i <listaAdmins.size(); i++){
                if(listaAdmins.get(i).getDNI().equals(dni)) return true;
            }
        }
        return false;
    }
}
