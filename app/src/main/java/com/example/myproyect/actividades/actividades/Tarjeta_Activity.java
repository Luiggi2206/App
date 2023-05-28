package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;



import java.util.Calendar;

import com.example.myproyect.R;
import com.example.myproyect.actividades.clases.MostrarMensaje;
import com.example.myproyect.actividades.entidades.Usuario;

public class Tarjeta_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText txtNucuenta, txtTicuenta, txtFecha, txtCVV;
    Button btnConfirmar, btnRegresar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta);

        txtNucuenta = findViewById(R.id.paNumTar);
        txtTicuenta = findViewById(R.id.paTiTar);
        txtFecha = findViewById(R.id.paFechVenTar);
        txtCVV = findViewById(R.id.paCCVTar);
        //button
        btnRegresar = findViewById(R.id.tarBtnRegresar);
        btnConfirmar= findViewById(R.id.tarBtnContinuar);

        btnConfirmar.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.paFechVenTar:
                fechadevencimiento();
                break;
            case  R.id.tarBtnContinuar:
                registrotar();
                break;
        }

    }

    private void capturarDatos() {
        String Nucuenta, Ticuenta, Fecha, CVV;
        Nucuenta = txtNucuenta.getText().toString();
        Ticuenta = txtTicuenta.getText().toString();
        Fecha = txtFecha.getText().toString();
        CVV = txtCVV.getText().toString();



        //" user = new Usuario( Nucuenta, Ticuenta, Fecha, CVV);
    }

    private void registrotar() {
        capturarDatos();

        }

    private void fechadevencimiento() {
        DatePickerDialog dpd;
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year =  2023;
        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                //2023-10-28
                txtFecha.setText(y+"-"+((1+m) < 10 ? "0" + (m+1) : (m+1))+"-"+(d <10 ? "0" +d : d));
                txtFecha.setTextColor(Color.parseColor("#ffffff"));
            }
        }, year, month, day );
        dpd.show();
    }
}
