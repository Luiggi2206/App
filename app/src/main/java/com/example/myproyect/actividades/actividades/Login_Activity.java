package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myproyect.R;
import com.example.myproyect.actividades.clases.MostrarMensaje;
import com.example.myproyect.actividades.conexion.ConexionMySQL;
import com.example.myproyect.actividades.entidades.Admin;
import com.example.myproyect.actividades.entidades.App;
import com.example.myproyect.actividades.entidades.Usuario;
import com.example.myproyect.actividades.modelos.DAO_Admins;
import com.example.myproyect.actividades.modelos.DAO_Cliente;
import com.example.myproyect.actividades.modelos.DAO_Usuarios;

import java.util.ArrayList;
import java.util.List;

public class Login_Activity extends AppCompatActivity {

    EditText txtCorreo, txtClave, txtHola;
    CheckBox checkRecordar;
    TextView lblRegistrate, lblRecuperarPass;
    Button btnIngresar, btnSalir;

    DAO_Usuarios dao_usuarios = new DAO_Usuarios(this);
    MostrarMensaje mostrarMensaje = new MostrarMensaje();
    Context context = this;

    public static Usuario usuario;
    private String correo=null, clave=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        asignarReferencias();
        txtClave.setText(null);
        txtCorreo.setText(null);
        txtCorreo.setText(null);
        dao_usuarios.abrirBD(); //abrir Base de datos
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
        });
    }

    void recuperarPass(){
        String correo = txtCorreo.getText().toString(); //guardar el correo ingresado
        if(correo.isEmpty()){

            MostrarMensaje.mensaje("Ingrese su correo",this);
        }else{
            //validar si el correo existe
            dao_usuarios.abrirBD();

            if(dao_usuarios.findUserEmail(correo)) {//usuario encontrado
                //validar su celular
                dao_usuarios.abrirBD();
                String cel="";

                final EditText input = new EditText(context);
                new AlertDialog.Builder(context)
                        //.setTitle("LOGIN ADMIN")
                        .setMessage("Ingrese su CELULAR: ")
                        .setView(input)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dao_usuarios.findUserCel(correo,input.getText().toString())){
                                    //CELL ENCONTRADO
                                    Intent intent = new Intent(context, RecuperarPassword_Activity.class);
                                    intent.putExtra("email", correo );
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(context, "Celular incorrecto", Toast.LENGTH_SHORT).show();
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
    private void validarRS(){ //validar recordar sesión del usuario
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
    private void iniciarSesion(String correo, String clave){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if(DAO_Cliente.ConsultarCLI(correo, clave)){
            //usuario encontrado

            Intent intent = new Intent(this, BienvenidoActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "USUARIO O CLAVE INCORRECTA", Toast.LENGTH_SHORT).show();
        }

    }


    private String buscarUsuario(String correo, String clave){
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios = dao_usuarios.listarUsuarios();
        if(listaUsuarios.size()==0){
            //mostrarMensaje.mensaje("No hay clientes registrados", context);
            return "No hay registro";
        }else{
            for(int i=0; i<listaUsuarios.size(); i++){
                if(listaUsuarios.get(i).getCorreo().equals(correo)
                    && listaUsuarios.get(i).getClave().equals(clave)
                ) {
                    usuario = listaUsuarios.get(i);
                    return "Bienvenido";
                }else if(listaUsuarios.get(i).getCorreo().equals(correo)
                        && !listaUsuarios.get(i).getClave().equals(clave)){
                    return "Correo o clave incorrectos";
                }
            }
        }
        return "Usuario no registrado";
    }
    private boolean buscarAdmin(String correo, String clave){
        List<Admin> listaAdmins = new ArrayList<>();
        DAO_Admins dao_admins = new DAO_Admins(this);
        dao_admins.abrirBD();
        listaAdmins = dao_admins.listarAdmins();
        if(listaAdmins.size()==0){
            //No hay admins registrados
            //mostrarMensaje.mensaje("No hay admins registrados", context);
            return false;
        }else{
            for(int i=0; i<listaAdmins.size(); i++){
                if(listaAdmins.get(i).getCorreo().equals(correo) && listaAdmins.get(i).getClave().equals(clave)) return true;
                else if(listaAdmins.get(i).getCorreo().equals(correo)
                        && !listaAdmins.get(i).getClave().equals(clave)){
                    return false;
                }
            }
        }
        return false;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}