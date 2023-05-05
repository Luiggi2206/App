package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myproyect.R;

public class CargaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);
        Thread tCarga = new Thread(){
            @Override
            public void run() {
                super.run();
                //validar los parametros correctos del programa
                //internet datos y etc
                //domir aprox 3 seg
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //cargar la siguiente actividad
                    Intent iInicioSesion = new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(iInicioSesion);
                    //no deja historial
                    finish();
                }
            }
        };
        tCarga.start();
    }
}