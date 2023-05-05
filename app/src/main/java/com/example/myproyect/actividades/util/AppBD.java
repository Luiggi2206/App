package com.example.myproyect.actividades.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class AppBD extends SQLiteOpenHelper {
    String query0="",query1="", query2="", query3="", query4="", query5="", query6="", query7="";
    List<String> listaQ;

    public AppBD(Context context){
        super(context, "app.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        query0 = "CREATE TABLE tb_usuarios "+
                "(DNI TEXT PRIMARY KEY NOT NULL, "+
                "nombre TEXT NOT NULL, "+
                "apellido TEXT NOT NULL, "+
                "correo TEXT NOT NULL UNIQUE, "+
                "clave TEXT NOT NULL, "+
                "fecha_naci TEXT, "+
                "sexo TEXT, "+
                "celular TEXT NOT NULL)";

        query1 = "INSERT INTO tb_usuarios values "+
                "('0000001', 'Milhos', 'Sihuay', 'mi@g.com','12345', '12/12/12', 'M', '987654321' ), "+
                "('0000002', 'Luiggi', 'Rebatta', 'lu@g.com','@tupapi', '13/12/12', 'M', '987654321'), "+
                "('0000003', 'Marcelo', 'Yabar', 'ma@g.com','123', '14/12/12', 'M', '987654321' ) ";


        query2 = "CREATE TABLE tb_app "+
                "(nombre TEXT PRIMARY KEY NOT NULL, "+
                "cantidadUser INTEGER, "+
                "recordarSesion TEXT, "+
                "correo TEXT, "+
                "clave TEXT )";

        query3 = "INSERT INTO tb_app "+
                "values ('Los_jardines', 3, 'false', 'm@g.com','12345')";

        query4 = "CREATE TABLE tb_losas "+
                "(nombre TEXT PRIMARY KEY NOT NULL, "+
                "distrito TEXT, "+
                "direccion TEXT )";

        query5 = "INSERT INTO tb_losas values"+
                "('El_pueblo', 'distrito1', 'direccion1'), "+
                "('El_barrio', 'distrito2', 'direccion2'), "+
                "('Los_patas', 'distrito3', 'direccion3'), "+
                "('La_familia', 'distrito4', 'direccion4') ";

        query6 = "CREATE TABLE tb_admins "+
                "(DNI TEXT PRIMARY KEY NOT NULL, "+
                "nombre TEXT NOT NULL, "+
                "apellido TEXT NOT NULL, "+
                "correo TEXT NOT NULL UNIQUE, "+
                "clave TEXT NOT NULL )";

        query7= "INSERT INTO tb_admins values "+
                "('0000010', 'Milhos', 'Sihuay', 'mi_adm@g.com','12345'), "+
                "('0000011', 'Luiggi', 'Rebatta', 'lu_adm@g.com','54321'), "+
                "('0000012', 'Marcelo', 'Yabar', 'ma_adm@g.com','123') ";


        sqLiteDatabase.execSQL(query0);
        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
        sqLiteDatabase.execSQL(query3);
        sqLiteDatabase.execSQL(query4);
        sqLiteDatabase.execSQL(query5);
        sqLiteDatabase.execSQL(query6);
        sqLiteDatabase.execSQL(query7);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
