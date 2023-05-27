package com.example.myproyect.actividades.modelos;

import com.example.myproyect.actividades.conexion.ConexionMySQL;
import com.example.myproyect.actividades.entidades.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_Cliente {

    public ArrayList<Usuario> listarClientes() {//PARA V. MANTENIMIENTO DE EMPLEADO
        ArrayList<Usuario> lista = new ArrayList<>();
        Connection cnx = null;
        try {
            cnx = ConexionMySQL.getConexion();
            CallableStatement csta = cnx.prepareCall("{call sp_ListarCLI()}");
            ResultSet rs = csta.executeQuery();
            Usuario user;
            while (rs.next()) {
                String DNI = rs.getString(1);
                String nom = rs.getString(2);
                String ape = rs.getString(3);
                String correo = rs.getString(4);
                String clave = rs.getString(5);
                String cel = rs.getString(6);
                user = new Usuario(DNI, nom, ape, correo, clave, cel);
                lista.add(user);
            }
        } catch (Exception e) {
            System.out.println("ERROR AC listarClientes(): " + e);
        }
        ConexionMySQL.cerrarConexion(cnx);
        return lista;
    }

    public static boolean ConsultarCLI(String correo, String pass){
        //PARA V. LOGIN
        //PARA V. RESET PASS
        boolean b= false;
        Connection cnx=ConexionMySQL.getConexion();
        try{

            CallableStatement csta=cnx.prepareCall("{call sp_consultarCLI(?,?)}");
            csta.setString(1, correo);
            csta.setString(2, pass);
            ResultSet rs= csta.executeQuery();
            if(rs.next()) b = true;
        }catch(Exception e){System.out.println("ERROR AC ConsultarDni(): "+e);}
        ConexionMySQL.cerrarConexion(cnx);
        return b;
    }

}
