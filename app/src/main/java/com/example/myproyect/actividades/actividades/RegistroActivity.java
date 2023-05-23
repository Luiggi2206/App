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
import com.example.myproyect.actividades.clases.MostrarMensaje;
import com.example.myproyect.actividades.entidades.Usuario;
import com.example.myproyect.actividades.modelos.DAO_Usuarios;

import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtNombre, txtApellido, txtCorreo, txtClave, txtFechaNac, txtDni, txtCel;
    Button btnContinuar, btnRegresar;
    RadioGroup rgrSexo;
    RadioButton rbtNoDef, rbtMaculino, rbtFemenino;
    Spinner cboDistritos;
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
        //radio grup button
        rgrSexo = findViewById(R.id.regRgrSexo);
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
        txtFechaNac.setOnClickListener(this);
        lblIniciar.setOnClickListener(this);
        lblTerminos.setOnClickListener(this);

        //inicializar el spinner (combo box) //se coloca el contexto, el tipo y el dato para utlilizar el array
        cboDistritos.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[] {"Seleccione Distrito", "San Juan de Lurigancho", "Comas", "Rimac"}));
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


    private void cargarSelectorFechas() {
        DatePickerDialog dpd;
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year =  calendar.get(Calendar.YEAR);
        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                //2000-10-28
                txtFechaNac.setText(y+"-"+((1+m) < 10 ? "0" + (m+1) : (m+1))+"-"+(d <10 ? "0" +d : d));
            }
        }, year, month, day );
        dpd.show();
    }

    private void validarTerminos() {
        boolean activo = chkTerminos.isChecked() ? true : false;
        btnContinuar.setEnabled(activo);
    }

    private void capturarDatos(){
        String dni, correo, clave, nombre, apellido, sexo, distrito, fecha_naci, celular;
        dni = txtDni.getText().toString();
        nombre = txtNombre.getText().toString();
        apellido = txtApellido.getText().toString();
        if(rbtFemenino.isChecked()) sexo = "F";
        else if(rbtMaculino.isChecked()) sexo = "M";
        else sexo = "N";
        correo = txtCorreo.getText().toString();
        clave =  txtClave.getText().toString();
        fecha_naci = txtFechaNac.getText().toString();
        celular = txtCel.getText().toString();



        user = new Usuario(dni, nombre, apellido, correo, clave, fecha_naci, sexo, celular);
    }

    //CONTINUAR
    private void registrar() {

        //validar formulario
        System.out.println("acá se validara el formulario");
        //procesar
        System.out.println("acá se procesara el formulario");
        //mostrar resultados


        capturarDatos();
        dao_usuarios.abrirBD();
        if(dao_usuarios.buscarUsuarioDNI(user.getDNI())){
            MostrarMensaje.mensaje("Usuario ya registrado", this);
        }else{
            //MostrarMensaje.mensaje(dao_usuarios.registrarCliente(user), this, InicioSesionActivity.class);
            MostrarMensaje.mensajeToast(dao_usuarios.registrarCliente(user),this, Login_Activity.class );

        }

        //Toast.makeText(getApplicationContext(),"Usuario Registrado", Toast.LENGTH_SHORT).show();
        //super.onBackPressed();

        /*
        Intent iBienvenido = new Intent(this, BienvenidoActivity.class);
        String txtNombre = null;
        iBienvenido.putExtra("txtNombre", txtNombre);
        startActivity(iBienvenido);
        */


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