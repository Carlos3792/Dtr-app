/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author carlos
 */
public class CasoSoporte {
    int id;
    String nombre_cliente;
    String descripcion;
    Date fecha_registro;
    String estado;  // Estado del caso (Abierto, Cerrado, En Proceso)

    public CasoSoporte() {
    }

    public CasoSoporte(int id, String nombre_cliente, String descripcion, Timestamp fecha_registro, String estado) {
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.descripcion = descripcion;
        this.fecha_registro = fecha_registro;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CasoSoporte{" +
               "id=" + id +
               ", nombre_cliente='" + nombre_cliente + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", fecha_registro=" + fecha_registro +
               ", estado='" + estado + '\'' +
               '}';
    }

    // Método para guardar un caso de soporte
    public void guardarCaso() {
        String sql = "INSERT INTO casos_soporte (nombre_cliente, descripcion, fecha_registro, estado) VALUES (?, ?, ?, ?)";
        Conexion c = new Conexion();
        try {
            PreparedStatement stmt = c.getConnection().prepareStatement(sql);
            stmt.setString(1, this.nombre_cliente);
            stmt.setString(2, this.descripcion);
            stmt.setDate(3, new java.sql.Date(this.fecha_registro.getTime()));  // Convertir Date a java.sql.Date
            stmt.setString(4, this.estado);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar el caso de soporte: " + e.getMessage());
        } finally {
            c.closeConnection();
        }
    }

    // Método para actualizar un caso de soporte
    public void actualizarCaso() {
        String sql = "UPDATE casos_soporte SET nombre_cliente = ?, descripcion = ?, fecha_registro = ?, estado = ? WHERE id = ?";
        Conexion c = new Conexion();
        try {
            PreparedStatement stmt = c.getConnection().prepareStatement(sql);
            stmt.setString(1, this.nombre_cliente);
            stmt.setString(2, this.descripcion);
            stmt.setDate(3, new java.sql.Date(this.fecha_registro.getTime()));  // Convertir Date a java.sql.Date
            stmt.setString(4, this.estado);
            stmt.setInt(5, this.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el caso de soporte: " + e.getMessage());
        } finally {
            c.closeConnection();
        }
    }

    // Método para eliminar un caso de soporte
    public void eliminarCaso() {
        String sql = "DELETE FROM casos_soporte WHERE id = ?";
        Conexion c = new Conexion();
        try {
            PreparedStatement stmt = c.getConnection().prepareStatement(sql);
            stmt.setInt(1, this.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el caso de soporte: " + e.getMessage());
        } finally {
            c.closeConnection();
        }
    }

    // Método para listar todos los casos de soporte
    public static ArrayList<CasoSoporte> listarCasos() {
        String sql = "SELECT * FROM casos_soporte";
        Conexion c = new Conexion();
        ResultSet rs = c.executeQuerysql(sql);
        ArrayList<CasoSoporte> casos = new ArrayList<>();
        try {
            while (rs.next()) {
                CasoSoporte caso = new CasoSoporte();
                caso.setId(rs.getInt("id"));
                caso.setNombre_cliente(rs.getString("nombre_cliente"));
                caso.setDescripcion(rs.getString("descripcion"));
                caso.setFecha_registro(rs.getDate("fecha_registro"));
                caso.setEstado(rs.getString("estado"));
                casos.add(caso);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar casos de soporte: " + e.getMessage());
        } finally {
            c.closeConnection();
        }
        return casos;
    }

    // Método para listar casos de soporte filtrados por texto
    public static ArrayList<CasoSoporte> listarCasosFiltrados(String texto) {
        String sql = "SELECT * FROM casos_soporte WHERE LOWER(nombre_cliente) LIKE ? OR LOWER(descripcion) LIKE ?";
        Conexion c = new Conexion();
        ResultSet rs = c.executeQuerysql(sql, "%" + texto.toLowerCase() + "%", "%" + texto.toLowerCase() + "%");
        ArrayList<CasoSoporte> casos = new ArrayList<>();
        try {
            while (rs.next()) {
                CasoSoporte caso = new CasoSoporte();
                caso.setId(rs.getInt("id"));
                caso.setNombre_cliente(rs.getString("nombre_cliente"));
                caso.setDescripcion(rs.getString("descripcion"));
                caso.setFecha_registro(rs.getDate("fecha_registro"));
                caso.setEstado(rs.getString("estado"));
                casos.add(caso);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar casos filtrados: " + e.getMessage());
        } finally {
            c.closeConnection();
        }
        return casos;
    }
}
