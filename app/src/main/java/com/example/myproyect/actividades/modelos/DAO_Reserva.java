package com.example.myproyect.actividades.modelos;

import com.example.myproyect.actividades.conexion.ConexionMySQL;
import com.example.myproyect.actividades.entidades.Reserva;
import com.example.myproyect.actividades.entidades.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_Reserva {

    public static  ArrayList<Reserva> listarReserva() {//
        ArrayList<Reserva> lista = new ArrayList<>();
        Connection cnx = null;
        try {
            cnx = ConexionMySQL.getConexion();
            CallableStatement csta = cnx.prepareCall("{call sp_ListarRESERVA()}");
            ResultSet rs = csta.executeQuery();
            Reserva reserva;
            while (rs.next()) {
                boolean[] ab = new boolean[3];
                int dia = rs.getInt(1);
                ab[0] = rs.getBoolean(2);
                ab[1] = rs.getBoolean(3);
                ab[2] = rs.getBoolean(4);

                reserva = new Reserva(dia, ab);
                lista.add(reserva);
            }
        } catch (Exception e) {
            System.out.println("ERROR AC listarReserva(): " + e);
        }
        ConexionMySQL.cerrarConexion(cnx);
        return lista;
    }

    public static String insertarRSV(boolean b, int dia, int h){
        //editar
        String msg=null;
        try{
            Connection cnx=ConexionMySQL.getConexion();
            CallableStatement csta=	cnx.prepareCall("{call sp_ReservarH"+h+"(?,?)}");
            csta.setInt(1, dia);
            csta.setBoolean(2, b);

            csta.executeUpdate();
            msg="Reserva registrada correctamente";
            ConexionMySQL.cerrarConexion(cnx);
        }catch(Exception e){
            System.out.println("ERROR AC insertarRSV(): " +e);
            msg= "Error al reservar!";
        }

        return msg;
    }

}
