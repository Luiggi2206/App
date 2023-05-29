package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myproyect.R;
import com.example.myproyect.actividades.clases.MostrarMensaje;
import com.example.myproyect.actividades.entidades.App;
import com.example.myproyect.actividades.entidades.Usuario;
import com.example.myproyect.actividades.modelos.DAO_Administrador;
import com.example.myproyect.actividades.modelos.DAO_Cliente;


public class Login_Activity extends AppCompatActivity {

    EditText txtCorreo, txtClave, txtHola;
    CheckBox checkRecordar;
    TextView lblRegistrate, lblRecuperarPass;
    Button btnIngresar, btnSalir;

    MostrarMensaje mostrarMensaje = new MostrarMensaje();
    Context context = this;

    public static Usuario usuario = new Usuario("72673554");
    private String correo=null, clave=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        asignarReferencias();
        txtClave.setText(null);
        txtCorreo.setText(null);
        txtCorreo.setText(null);

        App.loadtDatos(this);
        validarRS();

    }
    private void asignarReferencias(){
        txtCorreo = findViewById(R.id.logTxtCorreo);
        txtClave = findViewById(R.id.logTxtClave);
        checkRecordar = findViewById(R.id.logChkRecordar);
        lblRegistrate = findViewById(R.id.logLblRegistrar);
        btnIngresar = findViewById(R.id.logBtnIngresar);
        btnSalir = findViewById(R.id.logBtnSalir);

        lblRegistrate.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegistroActivity.class);
            startActivity(intent);
        });

        lblRecuperarPass = findViewById(R.id.lblRecuperarPass_Login);
        lblRecuperarPass.setText("Recuperar Contraseña");
        lblRecuperarPass.setOnClickListener(view -> {
            recuperarPass();
        });

        btnIngresar.setOnClickListener(view -> {
            validarFormulari();
        });
        btnSalir.setOnClickListener(view -> {
            finishAffinity();
            finish();
        });
    }

    void recuperarPass(){
        String correo = txtCorreo.getText().toString(); //guardar el correo ingresado
        if(correo.isEmpty()){
            MostrarMensaje.mensaje("Ingrese su correo",this);
        }else{
            //validar si el correo existe
            //dao_usuarios.abrirBD();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            if(DAO_Cliente.ConsultarCorreo(correo)){
                //usuario encontrado
                //validar dni como verificación

                final EditText input = new EditText(context);
                new AlertDialog.Builder(context)
                        //.setTitle("LOGIN ADMIN")
                        .setMessage("Ingrese su DNI: ")
                        .setView(input)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String dni = input.getText().toString();
                                if(DAO_Cliente.ConsultarDni(dni) ){
                                    //DNI ENCONTRADO
                                    Intent intent = new Intent(context, RecuperarPassword_Activity.class);
                                    intent.putExtra("dni", dni);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(context, "DNI incorrecto", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel",(dialogInterface, i) -> {
                            txtCorreo.setText(null);
                            txtClave.setText(null);
                        })
                        .show();
            }else{
                MostrarMensaje.mensaje("Correo no registrado", this);
            }

        }

    }
    private void validarRS(){
        //validar recordar sesión del usuario
        if(App.recordarS) {
            checkRecordar.setChecked(true);
            txtCorreo.setText(App.correo);
            txtClave.setText(App.clave);
        } else {
            checkRecordar.setChecked(false);
        }

    }
    public static Usuario getUsuario(){
        return usuario;
    }


    private void cargarActividadRegistrate() {
        Intent iRegistro = new Intent(this, RegistroActivity.class);
        startActivity(iRegistro);
        finish();
    }

    private void validarFormulari() {
        String correo = txtCorreo.getText().toString().trim();
        String clave = txtClave.getText().toString().trim();

        if(correo.isEmpty() || clave.isEmpty()) {
            Toast.makeText(this, "Ingrese correctamente sus datos", Toast.LENGTH_SHORT).show();
            return;
        }
        iniciarSesion(correo, clave);

    }
    private void guardarUsuario(String correo, String clave){
        usuario = DAO_Cliente.ObtenerCLI(correo,clave);

    }
    private void iniciarSesion(String correo, String clave){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if(DAO_Cliente.ConsultarCLI(correo, clave)){
            //usuario encontrado
            guardarUsuario(correo,clave);
            //validar recordar sesion
            if(checkRecordar.isChecked()){
                App.uploadDatos(this, true, correo, clave);
                Toast.makeText(context, "Sesión guardada", Toast.LENGTH_SHORT).show();
            }
            else{
                if(App.recordarS){
                    Toast.makeText(context, "Sesión dejada de recordar", Toast.LENGTH_SHORT).show();
                }
                App.uploadDatos(this, false, null, null);
            }
            Intent intent = new Intent(this, BienvenidoActivity.class);
            startActivity(intent);
        }else{
            //buscar admin
            if(DAO_Administrador.ConsultarAdm(correo, clave)){
                final EditText input = new EditText(context);
                new AlertDialog.Builder(context)
                        //.setTitle("LOGIN ADMIN")
                        .setMessage("Ingrese su DNI: ")
                        .setView(input)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String dni = input.getText().toString();
                                if(DAO_Administrador.ConsultarDni(dni) ){
                                    //DNI ENCONTRADO
                                    Intent intent = new Intent(context, MenuAdmin_Activity.class);
                                    //intent.putExtra("dni", dni);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(context, "DNI incorrecto", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel",(dialogInterface, i) -> {
                            txtCorreo.setText(null);
                            txtClave.setText(null);
                        })
                        .show();
            }else{
                Toast.makeText(this, "USUARIO O CLAVE INCORRECTA", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}