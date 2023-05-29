package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myproyect.R;
import com.example.myproyect.actividades.clases.MostrarMensaje;
import com.example.myproyect.actividades.modelos.DAO_Cliente;

public class RecuperarPassword_Activity extends AppCompatActivity {
    Button btnSalir, btnConfirmar;
    EditText pass1, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);

        asignarReferencias();
    }
    void asignarReferencias(){
        btnConfirmar = findViewById(R.id.btnConfirmar_RecupPass);
        btnConfirmar.setOnClickListener(view -> {
            validarConfirmacion();
        });
        btnSalir = findViewById(R.id.btnSalir_RecupPass);
        btnSalir.setOnClickListener(view -> {
            Intent intent = new Intent(this, Login_Activity.class);
            startActivity(intent);
        });
        pass1 = findViewById(R.id.txtPass1_RecupPass);
        pass2 = findViewById(R.id.txtPass2_RecupPass);

    }

    void validarConfirmacion(){
        String p1,p2;
        p1 = pass1.getText().toString();
        p2 = pass2.getText().toString();

        if(!p1.equals(p2)){
            MostrarMensaje.mensaje("Contraseñas no coinciden", this);
        }else{
            //pasa
            String dni = getIntent().getStringExtra("dni");
            String msg = DAO_Cliente.editarPass(dni, p1);
            MostrarMensaje.mensajeToast(msg,this, Login_Activity.class);

        }
    }
}