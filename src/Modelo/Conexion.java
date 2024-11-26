/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
/**
 *
 * @author carlos
 */
public class Conexion {
    // Variables.
    String Driver = "org.postgresql.Driver";
    String Stringcon = "jdbc:postgresql:tienda";
    String User = "postgres";
    String Password = "3792";
    Connection con;

    // Constructor.
    public Conexion() {
        try {
            Class.forName(Driver);
            con = DriverManager.getConnection(Stringcon, User, Password);
        } catch (Exception e) {
            System.out.println("No se pudo realizar la conexión a la base de datos");
            e.printStackTrace();  // Agrega esto para depurar errores.
        }
    }

    // Método para obtener la conexión.
    public Connection getConnection() {
        return con;
    }

    // Para insertar, eliminar o modificar.
    public boolean executesql(String sql) {
        boolean guardo = false;
        try {
            Statement stms = con.createStatement();
            guardo = stms.execute(sql);
        } catch (Exception e) {
            System.out.println("No se pudo realizar la acción");
            e.printStackTrace();  // Agrega esto para depurar errores.
        }
        return guardo;
    }

    public ResultSet executeQuerysql(String sql) {
        ResultSet rs = null;
        try {
            Statement stms = con.createStatement();
            rs = stms.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
            e.printStackTrace();  // Agrega esto para depurar errores.
        }
        return rs;
    }
    // Modificación: Método para consultar con parámetros
    public ResultSet executeQuerysql(String sql, String par, String par1) {
        ResultSet rs = null;
        try {
            // Usamos PreparedStatement para pasar los parámetros
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, par);   // Establecemos el primer parámetro
            pst.setString(2, par1);  // Establecemos el segundo parámetro
            rs = pst.executeQuery(); // Ejecutamos la consulta
        } catch (SQLException e) {
            System.out.println("No se pudo realizar la consulta");
            e.printStackTrace();  // Agrega esto para depurar errores.
        }
        return rs;
    }

    // Para cerrar la conexión.
    public void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("No se pudo cerrar la conexión");
            e.printStackTrace();  // Agrega esto para depurar errores.
        }
    }
}