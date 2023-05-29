package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myproyect.R;
import com.example.myproyect.actividades.clases.MostrarMensaje;
import com.example.myproyect.actividades.entidades.Usuario;
import com.example.myproyect.actividades.modelos.DAO_Cliente;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtNombre, txtApellido, txtCorreo, txtClave, txtFechaNac, txtDni, txtCel;
    Button btnContinuar, btnRegresar;
    CheckBox chkTerminos;
    TextView lblIniciar, lblTerminos;

    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //asociacion de la parte
        //logica        con la        grafica
        txtDni = findViewById(R.id.regTxtDni);
        txtNombre = findViewById(R.id.regTxtNombre);
        txtApellido = findViewById(R.id.regTxtApellido);
        txtCorreo = findViewById(R.id.regTxtCorreo);
        txtClave = findViewById(R.id.regTxtClave);
        txtCel = findViewById(R.id.regTxtCel);

        //link
        lblIniciar = findViewById(R.id.regLblIniciar);
        lblTerminos = findViewById(R.id.regLblTerminos);
        //chek
        chkTerminos = findViewById(R.id.regChkTerminos);
        //button
        btnRegresar = findViewById(R.id.regBtnRegresar);
        btnContinuar = findViewById(R.id.regBtnContinuar);
        //asociar el evento on click a los controles
        chkTerminos.setOnClickListener(this);
        btnContinuar.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);
        lblIniciar.setOnClickListener(this);
        lblTerminos.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.regChkTerminos:
                validarTerminos();
                break;
            case R.id.regLblTerminos:
                cargarTerminos();
                break;
            case R.id.regBtnContinuar:
                registrar();
                break;
            case R.id.regBtnRegresar:
                regresar();
                break;
            case R.id.regLblIniciar:
                cargarActividadIniciar();
                break;
        }
    }

    private void cargarTerminos() {
        Intent iTerminos = new Intent(this, TerminosActivity.class);
        startActivity(iTerminos);
    }

    private void validarTerminos() {
        boolean activo = chkTerminos.isChecked() ? true : false;
        btnContinuar.setEnabled(activo);
    }

    private void capturarDatos(){
        //guardar datos ingresados por el usuario
        String dni, correo, clave, nombre, apellido,   celular;
        dni = txtDni.getText().toString();
        nombre = txtNombre.getText().toString();
        apellido = txtApellido.getText().toString();
        correo = txtCorreo.getText().toString();
        clave =  txtClave.getText().toString();
        celular = txtCel.getText().toString();

        user = new Usuario(dni, nombre, apellido, correo, clave, celular);
    }

    //CONTINUAR
    private void registrar() {
        capturarDatos();
        if(user.getDNI().isEmpty()|| user.getCelular().isEmpty()|| user.getCorreo().isEmpty()
        ||user.getNombre().isEmpty() || user.getApellido().isEmpty()
        ){
            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            if(DAO_Cliente.ConsultarDni(user.getDNI() ) ){
                //DNI INGRESADO YA  SE ENCUENTRA REGISTRADO EN LA BD
                Toast.makeText(this, "DNI ya registrado", Toast.LENGTH_SHORT).show();
            }else{
                //DNI INGRESADO NO ESTÁ REGISTRADO
                if(DAO_Cliente.ConsultarCorreo(user.getCorreo())){
                    Toast.makeText(this, "CORREO ya registrado", Toast.LENGTH_SHORT).show();
                }else{
                    //USUARIO REGISTRADO CORRECTAMENTE
                    String msg = DAO_Cliente.insertarCLI(user);
                    MostrarMensaje.mensaje(msg, this, Login_Activity.class);
                }
            }
        }

    }
    private void regresar() {
        Intent iIniciarSesion = new Intent(this, Login_Activity.class);
        startActivity(iIniciarSesion);
        finish();
    }
    private void cargarActividadIniciar() {
        Intent iIniciarSesion = new Intent(this, Login_Activity.class);
        startActivity(iIniciarSesion);
        finish();
    }

}