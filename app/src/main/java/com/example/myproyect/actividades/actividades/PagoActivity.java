package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myproyect.R;

public class PagoActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAceptar, btnRegresar;
    RadioButton rbtVisa, rbtEfe, rbtYape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        //asociacion de la parte
        //logica con la grafica
        //button grup de tipo de transacion
        rbtVisa = findViewById(R.id.biepagRbtTar);
        rbtEfe = findViewById(R.id.biepagRbtEfe);
        rbtYape = findViewById(R.id.biepagRbtYa);
        //button de aceptar y regresar
        btnAceptar = findViewById(R.id.biepagBtnAcep);
        btnRegresar = findViewById(R.id.biepagBtnReg);
        //asciar el evento on click a los controles
        btnAceptar.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.biepagBtnAcep:
                guardarDatos();
                break;
            case R.id.biepagBtnReg:
                regresarMenu();
                break;
        }
    }

    private void regresarMenu() {
        Intent iBienvenida = new Intent(this, BienvenidoActivity.class);
        startActivity(iBienvenida);

    }

    private void guardarDatos() {
        //validar metodo de pago
        System.out.println("acá se validara el metodo de pago");
        //procesar
        System.out.println("acá se procesara el metodo de pago");
        //mostrar resultados
        Toast.makeText(getApplicationContext(),"Metodo de pago Registrado", Toast.LENGTH_SHORT).show();

        Intent iBienvenido = new Intent(this, BienvenidoActivity.class);
        String txtNombre = null;
        iBienvenido.putExtra("txtNombre", txtNombre);
        startActivity(iBienvenido);

    }

}