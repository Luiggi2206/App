package com.example.myproyect.actividades.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myproyect.actividades.entidades.Usuario;
import com.example.myproyect.actividades.util.AppBD;

import java.util.ArrayList;
import java.util.List;

public class DAO_Usuarios {
    AppBD appBD;
    SQLiteDatabase db;
    Context context;

    public DAO_Usuarios(Context context){
        appBD = new AppBD(context);
        this.context = context;
    }

    public void abrirBD(){
        db = appBD.getWritableDatabase();
    }

    public String registrarCliente(Usuario user){
        String respuesta="";
        try {
            ContentValues valores = new ContentValues();
            valores.put("dni", user.getCorreo());
            valores.put("nombre", user.getNombre());
            valores.put("apellido", user.getApellido());
            valores.put("correo", user.getCorreo());
            valores.put("clave", user.getClave());
            valores.put("celular", user.getCelular());

            long r = db.insert("tb_usuarios", null, valores);
            if(r==1) respuesta= "Error al insertar Usuario";
            else respuesta = "Se registró correctamente";
        }catch (Exception e){
            respuesta = e.getMessage();
        }

        return respuesta;
    }

    public List<Usuario> listarUsuarios(){
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM tb_usuarios", null);
            while(c.moveToNext()){
                listaUsuarios.add(new Usuario(
                        c.getString(0), //dni
                        c.getString(1), //nombre
                        c.getString(2), //apellido
                        c.getString(3), //correo
                        c.getString(4), //clave
                        c.getString(5) //celular
                ));
            }
        }catch (Exception e){

        }
        if(listaUsuarios.size()==0){
            Log.d("LISTAR_USUARIOS", "LISTA VACÍA");
        }else
            Log.d("LISTAR USUARIOS", "Cantidad: "+listaUsuarios.size());

        return listaUsuarios;

    }
    public boolean setPassword(String pass, String correo){
        long r;
        try{
            ContentValues values = new ContentValues();
            values.put("clave", pass);
            r = db.update("tb_usuarios",values, "correo='"+correo+"'", null);
            if(r<=0) return false;
            else return true;
        }catch (Exception e){
            Log.d("DAO_USERS", "ERROR SETPASS: "+e);
            return false;
        }
    }
    public boolean buscarUsuarioDNI(String dni){
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios = listarUsuarios();
        if(listaUsuarios.size() == 0) return false;
        else{
            for(int i=0 ; i <listaUsuarios.size(); i++){
                if(listaUsuarios.get(i).getDNI().equals(dni)) return true;
            }
        }
        return false;
    }

    public boolean findUserEmail(String email){
        //buscar si el correo está registrado
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios = listarUsuarios();
        if(listaUsuarios.size() == 0) return false;
        else{
            for(int i=0 ; i <listaUsuarios.size(); i++){
                if(listaUsuarios.get(i).getCorreo().equals(email)) return true;
            }
        }
        return false;
    }

    public boolean findUserCel(String email, String cel){
        //buscar el celular del usuario
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios = listarUsuarios();
        if(listaUsuarios.size() == 0) return false;
        else{
            for(int i=0 ; i <listaUsuarios.size(); i++){
                if(listaUsuarios.get(i).getCorreo().equals(email)
                    && listaUsuarios.get(i).getCelular().equals(cel)
                ) return true;
            }
        }
        return false;
    }


}
