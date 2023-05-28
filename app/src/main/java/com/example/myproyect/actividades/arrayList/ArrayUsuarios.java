package com.example.myproyect.actividades.arrayList;

import android.util.Log;

import com.example.myproyect.actividades.conexion.ConexionMySQL;
import com.example.myproyect.actividades.entidades.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ArrayUsuarios {
    public String insertar(Usuario usuario) {
        Connection connection = null;
        String msg= null;
        try {
            connection = null;
            CallableStatement callableStatement = connection.prepareCall("{call sp_InsertarCLI(?,?,?,?,?,?)}");
            callableStatement.setString(1, usuario.getDNI());
            callableStatement.setString(2, usuario.getNombre());
            callableStatement.setString(3, usuario.getApellido());
            callableStatement.setString(4, usuario.getCorreo());
            callableStatement.setString(5, usuario.getClave());
            callableStatement.setString(6, usuario.getCelular());
            callableStatement.executeUpdate();
            msg = "Se registró correctamente";
        } catch (Exception e) {
            msg = "Error al registrarse";
            Log.d("BD", "Error InsertarUsuarios: "+e);

        }
       return msg;
    }

    public ArrayList<Usuario> listarClientes(){//PARA V. MANTENIMIENTO DE EMPLEADO
        ArrayList<Usuario> lista=new ArrayList<>();


        return lista;
    }


}
