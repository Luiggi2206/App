package com.example.myproyect.actividades.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sesion extends SQLiteOpenHelper {
    private static final String nombreBD = "LosaDeportiva.db";
    private static final int versionBD = 1;
    private static final String tablaUsuario= "CREATE TABLE IF NOT EXISTS USUARIO (ID INTEGER, CORREO VARCHAR (100), CLAVE VARCHAR(100))";
    public Sesion(@Nullable Context context) {
        super(context, nombreBD, null, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tablaUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USUARIO");
            sqLiteDatabase.execSQL(tablaUsuario);
        }
    }

    public boolean agregarUsuario(int id, String usuario, String clave){
        boolean bAgregado = false;
        SQLiteDatabase db = getWritableDatabase();

        if(db != null) {
            db.execSQL("INSERT INTO USUARIO VALUES("+id+",'"+usuario+"','"+clave+"')");
            db.close();
            bAgregado = true;
        }
        return bAgregado;
    }

    public boolean recordoSesion(){
        boolean bRecordo = false;
        SQLiteDatabase db = getReadableDatabase();
        if(db != null) {
            Cursor cursor = db.rawQuery("SELECT * FROM USUARIO", null);
            if(cursor.moveToNext())
                bRecordo = true;
        }
        return bRecordo;
    }

    public String buscarCampo(String campo){
        String data = null;
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT %s FROM USUARIO", campo);
        if(db != null) {
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToNext()){
                data = cursor.getString(0);
            }
        }
        return data;
    }

    public boolean eliminarUsuario(int id){
        boolean bBorrado = false;
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            db.execSQL("DELETE FROM USUARIO WHERE ID = "+id);
            db.close();
            bBorrado = true;
        }
        return bBorrado;
    }
}
