package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myproyect.R;
import com.example.myproyect.actividades.clases.MostrarMensaje;
import com.example.myproyect.actividades.modelos.DAO_Usuarios;

public class RecuperarPassword_Activity extends AppCompatActivity {
    Button btnSalir, btnConfirmar;
    EditText pass1, pass2;
    //HOLA
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

        if(!p1.equals(p1)){
            MostrarMensaje.mensaje("Contraseñas no coinciden", this);
        }else{
            //pasa
            DAO_Usuarios dao_usuarios = new DAO_Usuarios(this);
            dao_usuarios.abrirBD();

            String email = getIntent().getStringExtra("email");
            //Toast.makeText(this, email, Toast.LENGTH_SHORT).show();

            if(dao_usuarios.setPassword(p1, email)){
                //exito
                MostrarMensaje.mensajeToast("Se restableció su contraseña",this, Login_Activity.class);
            }else{//error
                MostrarMensaje.mensaje("Error al restablecer su contraseña", this);
            }


        }
    }
}