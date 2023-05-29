package com.example.myproyect.actividades.modelos;

import com.example.myproyect.actividades.conexion.ConexionMySQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class DAO_Administrador {

    public static boolean ConsultarAdm(String correo, String pass){ //consultar logueo
        //PARA V. LOGIN
        boolean b = false;
        try{
            Connection cnx= ConexionMySQL.getConexion();
            CallableStatement csta=cnx.prepareCall("{call sp_consultarADM(?,?)}");
            csta.setString(1, correo);
            csta.setString(2, pass);
            ResultSet rs= csta.executeQuery();
            if(rs.next()) {
                //Tienda.setAdmin(true);
                b = true;
            }
        }catch(Exception e){System.out.println("Error AE ConsultarADM(): "+e);}
        return b;
    }

    public static boolean ConsultarCorreoAdm(String correo){ //consultar logueo
        //PARA V. LOGIN
        boolean b = false;
        try{
            Connection cnx= ConexionMySQL.getConexion();
            CallableStatement csta=cnx.prepareCall("{call sp_consultarCorreoADM(?)}");
            csta.setString(1, correo);
            ResultSet rs= csta.executeQuery();
            if(rs.next()) {
                //Tienda.setAdmin(true);
                b = true;
            }
        }catch(Exception e){System.out.println("Error ConsultarCorreoADM(): "+e);}
        return b;
    }

    public static boolean ConsultarDni(String dni){
        //PARA V. LOGIN
        boolean b= false;
        try{
            Connection cnx=ConexionMySQL.getConexion();
            CallableStatement csta=cnx.prepareCall("{call sp_consultarDniADM(?)}");
            csta.setString(1, dni);
            ResultSet rs= csta.executeQuery();
            if(rs.next()) b = true;
        }catch(Exception e){System.out.println("ERROR AE ConsultarDni(): "+e);}
        return b;
    }
}
