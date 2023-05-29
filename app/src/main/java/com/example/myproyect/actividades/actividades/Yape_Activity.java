package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myproyect.R;

public class Yape_Activity extends AppCompatActivity implements View.OnClickListener {
    Button btnConfirmar, btnRegresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yape);

        //asociacion de la parte
        //logica con la grafica
        //button de tipo de transacion
        btnConfirmar = findViewById(R.id.YaBtnConfirmar);
        btnRegresar = findViewById(R.id.YaBtnRegresar);

        //asciar el evento on click a los controles
        btnConfirmar.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.YaBtnConfirmar:
                confirmaryapeo();
                break;
            case R.id.YaBtnRegresar:
                regresarpago();
                break;
        }
    }

    private void regresarpago() {
        Intent iPago = new Intent(this, PagoActivity.class);
        startActivity(iPago);
        finish();
    }

    private void confirmaryapeo() {

        Toast.makeText(getApplicationContext(),"Metodo de pago Registrado", Toast.LENGTH_SHORT).show();
        // regresa al menu las 4 lozas (bienvenido)
        Intent iBienvenido = new Intent(this, BienvenidoActivity.class);
        startActivity(iBienvenido);
        finish();
    }
}