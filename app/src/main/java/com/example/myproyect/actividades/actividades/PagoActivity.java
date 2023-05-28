package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myproyect.R;

public class PagoActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnYape, btnTarjeta, btnRegresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        //asociacion de la parte
        //logica con la grafica
        //button de tipo de transacion
        btnTarjeta = findViewById(R.id.biepagBtnTarjeta);
        btnYape = findViewById(R.id.biepagBtnYape);
        //button de  regresar
        btnRegresar = findViewById(R.id.biepagBtnReg);
        //asciar el evento on click a los controles
        btnTarjeta.setOnClickListener(this);
        btnYape.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.biepagBtnTarjeta:
                ingresarTarjeta();
                break;

            case R.id.biepagBtnYape:
                ingresarYape();
                break;
            case R.id.biepagBtnReg:
                regresarMenu();
                break;
        }
    }

    private void ingresarTarjeta() {
        Intent iTarjeta= new Intent(this, Tarjeta_Activity.class);
        startActivity(iTarjeta);
        finish();
    }

    private void ingresarYape() {
        Intent iYape = new Intent(this, Yape_Activity.class);
        startActivity(iYape);
        finish();
    }



    private void regresarMenu() {
        Intent iBienvenido = new Intent(this, BienvenidoActivity.class);
        startActivity(iBienvenido);
        finish();
    }

    private void ingresarYapeoTarjeta() {
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