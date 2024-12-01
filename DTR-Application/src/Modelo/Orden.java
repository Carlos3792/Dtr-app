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
public class Orden {
    int id;
    String cliente;
    Date fecha;
    double total;
    ArrayList<Producto> productos; // Relación con productos

    public Orden() {
        this.productos = new ArrayList<>();
    }

    public Orden(int id, String cliente, Date fecha, double total) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.productos = new ArrayList<>();
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public ArrayList<Producto> getProductos() { return productos; }
    public void setProductos(ArrayList<Producto> productos) { this.productos = productos; }

    // Calcular el total basado en los productos asociados
    public void calcularTotal() {
        total = productos.stream().mapToDouble(p -> p.getPrecio() * p.getCantidad()).sum();
    }

    // Métodos de base de datos
    public void guardarOrden() {
        String sql = "INSERT INTO ordenes (cliente, fecha, total) VALUES (?, ?, ?)";
        Conexion c = new Conexion();
        try {
            PreparedStatement stmt = c.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente);
            stmt.setTimestamp(2, new Timestamp(fecha.getTime()));
            stmt.setDouble(3, total);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            // Guardar productos asociados
            for (Producto p : productos) {
                guardarProductoOrden(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar la orden: " + e.getMessage());
        } finally {
            c.closeConnection();
        }
    }

    public void eliminarOrden() {
    Conexion c = new Conexion();
    
    // Paso 1: Restablecer las cantidades de los productos
    for (Producto producto : productos) {
        producto.devolverCantidadProducto(); // Devolver la cantidad de los productos al inventario
    }

    // Paso 2: Eliminar las relaciones de los productos en la tabla intermedia
    String sqlEliminarRelacion = "DELETE FROM productos_orden WHERE id_orden = ?";
    try (PreparedStatement stmtRelacion = c.getConnection().prepareStatement(sqlEliminarRelacion)) {
        stmtRelacion.setInt(1, this.id);
        stmtRelacion.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al eliminar la relación de productos con la orden: " + e.getMessage());
    }

    // Paso 3: Eliminar la orden en la tabla principal
    String sqlEliminarOrden = "DELETE FROM ordenes WHERE id = ?";
    try (PreparedStatement stmtOrden = c.getConnection().prepareStatement(sqlEliminarOrden)) {
        stmtOrden.setInt(1, this.id);
        stmtOrden.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al eliminar la orden: " + e.getMessage());
    } finally {
        c.closeConnection();
    }

    System.out.println("Orden y relaciones eliminadas correctamente.");
}
    
    private void guardarProductoOrden(Producto producto) {
        String sql = "INSERT INTO productos_orden (id_orden, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        Conexion c = new Conexion();
        try {
            PreparedStatement stmt = c.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, producto.getId());
            stmt.setInt(3, producto.getCantidad());
            stmt.setDouble(4, producto.getPrecio());
            stmt.executeUpdate();
            
            // Verificar si hay suficiente cantidad antes de actualizar
        if (producto.getCantidad() <= producto.obtenerCantidadProductoDisponible()) {
            // Actualizar la cantidad del producto
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
    
    public static ArrayList<Orden> listarOrdenes() {
    // SQL para obtener todas las órdenes
    String sqlOrdenes = "SELECT * FROM ordenes";
    
    // SQL para obtener los productos relacionados con una orden
    String sqlProductosOrden = "SELECT po.id_producto, po.cantidad, po.precio_unitario, p.nombre_producto, p.descripcion, p.categoria, p.marca, p.precio " +
                               "FROM productos_orden po " +
                               "JOIN Producto p ON po.id_producto = p.id " +
                               "WHERE po.id_orden = ?";
    
    Conexion c = new Conexion();
    ArrayList<Orden> ordenes = new ArrayList<>();
    
    try {
        // Consulta las órdenes
        ResultSet rsOrdenes = c.executeQuerysql(sqlOrdenes);
        while (rsOrdenes.next()) {
            Orden orden = new Orden();
            orden.setId(rsOrdenes.getInt("id"));
            orden.setCliente(rsOrdenes.getString("cliente"));
            orden.setFecha(rsOrdenes.getTimestamp("fecha"));
            orden.setTotal(rsOrdenes.getDouble("total"));

            // Consultar productos de esta orden
            PreparedStatement stmtProductos = c.getConnection().prepareStatement(sqlProductosOrden);
            stmtProductos.setInt(1, orden.getId());
            ResultSet rsProductos = stmtProductos.executeQuery();

            ArrayList<Producto> productos = new ArrayList<>();
            while (rsProductos.next()) {
                Producto producto = new Producto();
                producto.setId(rsProductos.getInt("id_producto"));
                producto.setNombre_producto(rsProductos.getString("nombre_producto"));
                producto.setDescripcion(rsProductos.getString("descripcion"));
                producto.setCategoria(rsProductos.getString("categoria"));
                producto.setMarca(rsProductos.getString("marca"));
                producto.setPrecio(rsProductos.getInt("precio"));
                producto.setCantidad(rsProductos.getInt("cantidad")); // Cantidad comprada en la orden
                productos.add(producto);
            }

            // Asignar productos a la orden
            orden.setProductos(productos);
            ordenes.add(orden);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar las órdenes: " + e.getMessage());
    } finally {
        c.closeConnection();
    }

    return ordenes;
}
    //Metedos experimentales
    public void actualizarOrden() {
    Conexion c = new Conexion();
    // Actualizar la información general de la orden en la base de datos
    String queryOrden = "UPDATE ordenes SET cliente = ?, fecha = ?, total = ? WHERE id = ?";
    try (PreparedStatement stmtOrden = c.getConnection().prepareStatement(queryOrden)) {
        stmtOrden.setString(1, this.cliente);
        stmtOrden.setDate(2, new java.sql.Date(this.fecha.getTime()));
        stmtOrden.setDouble(3, this.total);
        stmtOrden.setInt(4, this.id);
        stmtOrden.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Eliminar los productos existentes de la orden en la base de datos
    String queryEliminarProductos = "DELETE FROM productos_orden WHERE id_orden = ?";
    try (PreparedStatement stmtEliminar = c.getConnection().prepareStatement(queryEliminarProductos)) {
        stmtEliminar.setInt(1, this.id);
        stmtEliminar.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Insertar los productos actualizados
    String queryInsertarProducto = "INSERT INTO productos_orden (id_orden, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmtProducto = c.getConnection().prepareStatement(queryInsertarProducto)) {
        for (Producto producto : this.productos) {
            stmtProducto.setInt(1, this.id);
            stmtProducto.setInt(2, producto.getId());
            stmtProducto.setInt(3, producto.getCantidad());
            stmtProducto.setDouble(4, producto.getPrecio());
            stmtProducto.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}