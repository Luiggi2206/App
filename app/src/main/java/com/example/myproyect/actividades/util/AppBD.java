package com.example.myproyect.actividades.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class AppBD extends SQLiteOpenHelper {
    String query0="",query1="", query2="";

    public AppBD(Context context){
        super(context, "app.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        query1 = "CREATE TABLE tb_app "+
                "(nombre TEXT PRIMARY KEY NOT NULL, "+
                "cantidadUser INTEGER, "+
                "recordarSesion TEXT, "+
                "correo TEXT, "+
                "clave TEXT )";

        query2 = "INSERT INTO tb_app "+
                "values ('Los_jardines', 3, 'false', 'correo@g.com','00000')";

        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
