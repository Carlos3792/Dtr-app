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

    // Paso 3: Eliminar la información de envío (domicilio) de la tabla envios
    String sqlEliminarEnvio = "DELETE FROM envios WHERE id_orden = ?";
    try (PreparedStatement stmtEnvio = c.getConnection().prepareStatement(sqlEliminarEnvio)) {
        stmtEnvio.setInt(1, this.id);
        stmtEnvio.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al eliminar la información de envío: " + e.getMessage());
    }

    // Paso 4: Eliminar la orden en la tabla principal
    String sqlEliminarOrden = "DELETE FROM ordenes WHERE id = ?";
    try (PreparedStatement stmtOrden = c.getConnection().prepareStatement(sqlEliminarOrden)) {
        stmtOrden.setInt(1, this.id);
        stmtOrden.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al eliminar la orden: " + e.getMessage());
    } finally {
        c.closeConnection();
    }

    System.out.println("Orden, relaciones de productos y información de envío eliminadas correctamente.");
}

    public static ArrayList<OrdenConDomicilio> listarOrdenescondomicilio() {
        // SQL para obtener todas las órdenes
        String sqlOrdenes = "SELECT * FROM ordenes";

        // SQL para obtener los productos relacionados con una orden
        String sqlProductosOrden = "SELECT po.id_producto, po.cantidad, po.precio_unitario, p.nombre_producto, p.descripcion, p.categoria, p.marca, p.precio " +
                                   "FROM productos_orden po " +
                                   "JOIN Producto p ON po.id_producto = p.id " +
                                   "WHERE po.id_orden = ?";

        // SQL para obtener la información de envío (domicilio)
        String sqlEnvio = "SELECT direccion, destinatario, telefono, estado FROM envios WHERE id_orden = ?";

        Conexion c = new Conexion();
        ArrayList<OrdenConDomicilio> ordenes = new ArrayList<>();

        try {
            // Consulta las órdenes
            ResultSet rsOrdenes = c.executeQuerysql(sqlOrdenes);
            while (rsOrdenes.next()) {
                OrdenConDomicilio orden = new OrdenConDomicilio();
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

                // Consultar información de domicilio (envío) de esta orden
                PreparedStatement stmtEnvio = c.getConnection().prepareStatement(sqlEnvio);
                stmtEnvio.setInt(1, orden.getId());
                ResultSet rsEnvio = stmtEnvio.executeQuery();
                if (rsEnvio.next()) {
                    orden.setDireccion(rsEnvio.getString("direccion"));
                    orden.setDestinatario(rsEnvio.getString("destinatario"));
                    orden.setTelefono(rsEnvio.getString("telefono"));
                    orden.setEstado(rsEnvio.getString("estado"));
                    ordenes.add(orden); // Solo agregar si tiene domicilio
            }
        }            
            
        } catch (SQLException e) {
            System.out.println("Error al listar las órdenes: " + e.getMessage());
        } finally {
            c.closeConnection();
        }

        return ordenes;
    }

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

    // Actualizar la información de domicilio (envío)
    String queryEnvio = "UPDATE envios SET direccion = ?, destinatario = ?, telefono = ?, estado = ? WHERE id_orden = ?";
    try (PreparedStatement stmtEnvio = c.getConnection().prepareStatement(queryEnvio)) {
        stmtEnvio.setString(1, this.direccion);
        stmtEnvio.setString(2, this.destinatario);
        stmtEnvio.setString(3, this.telefono);
        stmtEnvio.setString(4, this.estado);
        stmtEnvio.setInt(5, this.id);
        stmtEnvio.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}
