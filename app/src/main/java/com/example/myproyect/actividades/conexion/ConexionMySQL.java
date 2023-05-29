package com.example.myproyect.actividades.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    public static Connection getConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://mysql-android.mysql.database.azure.com:3306/app_losjardines?useSSL=false", "admin2023", "P@$$w0rd");
            System.out.println("Conexión exitosa a la base de datos.");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Connection conexion = getConexion();
        // Aquí puedes realizar operaciones en la base de datos utilizando la conexión
        cerrarConexion(conexion);
    }
}

