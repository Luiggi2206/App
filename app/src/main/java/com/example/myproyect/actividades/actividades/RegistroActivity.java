package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myproyect.R;
import com.example.myproyect.actividades.arrayList.ArrayUsuarios;
import com.example.myproyect.actividades.clases.MostrarMensaje;
import com.example.myproyect.actividades.conexion.ConexionMySQL;
import com.example.myproyect.actividades.entidades.Usuario;
import com.example.myproyect.actividades.modelos.DAO_Usuarios;

import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtNombre, txtApellido, txtCorreo, txtClave, txtFechaNac, txtDni, txtCel;
    Button btnContinuar, btnRegresar;
    CheckBox chkTerminos;
    TextView lblIniciar, lblTerminos;

    DAO_Usuarios dao_usuarios = new DAO_Usuarios(this);
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
        //MostrarMensaje.mensajeToast(arrayUsuarios.insertar(user),this, Login_Activity.class ); //MYSQL
        //ConexionMySQL.obtenerConexion();
        //ConexionMySQL.cerrarConexion();

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