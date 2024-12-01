/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author carlos
 */
public class OrdenConDomicilio extends Orden {
    String direccion;
    String destinatario;
    String telefono;
    String estado; // "Pendiente", "En Camino", "Entregado"

    public OrdenConDomicilio() {
        super();
        this.estado = "Pendiente"; // Estado inicial
    }

    public OrdenConDomicilio(int id, String cliente, Date fecha, double total, String direccion, String destinatario, String telefono, String estado) {
        super(id, cliente, fecha, total);
        this.direccion = direccion;
        this.destinatario = destinatario;
        this.telefono = telefono;
        this.estado = estado;
    }

    public OrdenConDomicilio(String direccion, String destinatario, String telefono, String estado) {
        super();
        this.direccion = direccion;
        this.destinatario = destinatario;
        this.telefono = telefono;
        this.estado = estado;
    }
    
    

    // Getters y setters
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // Sobrescribir guardarOrden para incluir datos de domicilio
    @Override
public void guardarOrden() {
    // Primero guardar la orden en la tabla principal de 'ordenes'
    String sqlOrden = "INSERT INTO ordenes (cliente, fecha, total) VALUES (?, ?, ?)";
    Conexion c = new Conexion();
    try {
        PreparedStatement stmtOrden = c.getConnection().prepareStatement(sqlOrden, Statement.RETURN_GENERATED_KEYS);
        stmtOrden.setString(1, cliente);
        stmtOrden.setTimestamp(2, new Timestamp(fecha.getTime()));
        stmtOrden.setDouble(3, total);
        stmtOrden.executeUpdate();

        // Obtener el ID generado para la orden
        ResultSet generatedKeys = stmtOrden.getGeneratedKeys();
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);  // Establecer el ID de la orden generada
        }

        // Guardar los productos asociados a la orden
        for (Producto p : productos) {
            guardarProductoOrden(p);
        }

        // Ahora guardar los datos del domicilio asociados a la orden
        String sqlEnvio = "INSERT INTO envios (id_orden, direccion, destinatario, telefono, estado) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmtEnvio = c.getConnection().prepareStatement(sqlEnvio);
        stmtEnvio.setInt(1, id);  // Usamos el ID de la orden recién generada
        stmtEnvio.setString(2, direccion);
        stmtEnvio.setString(3, destinatario);
        stmtEnvio.setString(4, telefono);
        stmtEnvio.setString(5, estado);
        stmtEnvio.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Error al guardar la orden con domicilio: " + e.getMessage());
    } finally {
        c.closeConnection();
    }
}
    
    
    private void guardarProductoOrden(Producto producto) {
    String sql = "INSERT INTO productos_orden (id_orden, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
    Conexion c = new Conexion();
    try {
        // Insertar el producto en la tabla intermedia productos_orden
        PreparedStatement stmt = c.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);  // ID de la orden
        stmt.setInt(2, producto.getId());  // ID del producto
        stmt.setInt(3, producto.getCantidad());  // Cantidad del producto
        stmt.setDouble(4, producto.getPrecio());  // Precio unitario del producto
        stmt.executeUpdate();
        
        // Verificar si hay suficiente cantidad disponible en el inventario antes de actualizar
        if (producto.getCantidad() <= producto.obtenerCantidadProductoDisponible()) {
            // Si hay suficiente cantidad, restar la cantidad del inventario
            producto.sustraerCantidadProducto();
        } else {
            System.out.println("No hay suficiente cantidad de producto disponible.");
        }
    } catch (SQLException e) {
        System.out.println("Error al guardar el producto en la orden: " + e.getMessage());
    } finally {
        c.closeConnection();
    }
}


}
