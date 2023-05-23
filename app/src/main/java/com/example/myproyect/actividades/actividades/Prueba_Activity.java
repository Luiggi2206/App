package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myproyect.R;
import com.example.myproyect.actividades.conexion.ConexionMySQL;

public class Prueba_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);


        ConexionMySQL.obtenerConexion();
    }
}