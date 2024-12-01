/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.*;
import java.io.*;

/**
 *
 * @author carlos
 */
public class Producto {
    int id;
    String nombre_producto;
    String descripcion;
    String categoria;
    String marca;
    int precio;
    int cantidad;
    byte[] imagen;  // Almacenamos la imagen como un array de bytes

    public Producto() {
    }

    public Producto(int id, String nombre_producto, String descripcion, String categoria, String marca, int precio, int cantidad, byte[] imagen) {
        this.id = id;
        this.nombre_producto = nombre_producto;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre_producto=" + nombre_producto + ", descripcion=" + descripcion + ", categoria=" + categoria + ", marca=" + marca + ", precio=" + precio + ", cantidad=" + cantidad + '}';
    }

    
    // Método para guardar el producto, incluyendo la imagen
    public void guardarProducto() {
        String sql = "INSERT INTO Producto (nombre_producto, descripcion, categoria, marca, precio, cantidad, imagen) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Conexion c = new Conexion();
        try {
            PreparedStatement stmt = c.getConnection().prepareStatement(sql);
            stmt.setString(1, this.nombre_producto);
            stmt.setString(2, this.descripcion);
            stmt.setString(3, this.categoria);
            stmt.setString(4, this.marca);
            stmt.setInt(5, this.precio);
            stmt.setInt(6, this.cantidad);
            stmt.setBytes(7, this.imagen); // Guardar imagen como BLOB
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar el producto: " + e.getMessage());
        } finally {
            c.closeConnection();
        }
    }
    
    public void actualizarProducto() {
    String sql = "UPDATE Producto SET nombre_producto = ?, descripcion = ?, categoria = ?, marca = ?, precio = ?, cantidad = ?, imagen = ? WHERE id = ?";
    Conexion c = new Conexion();
    try {
        PreparedStatement stmt = c.getConnection().prepareStatement(sql);
        stmt.setString(1, this.nombre_producto);
        stmt.setString(2, this.descripcion);
        stmt.setString(3, this.categoria);
        stmt.setString(4, this.marca);
        stmt.setInt(5, this.precio);
        stmt.setInt(6, this.cantidad);
        stmt.setBytes(7, this.imagen); // Actualizar imagen como BLOB
        stmt.setInt(8, this.id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al actualizar el producto: " + e.getMessage());
    } finally {
        c.closeConnection();
    }
}

    public void eliminarProducto() {
    String sql = "DELETE FROM Producto WHERE id = ?";
    Conexion c = new Conexion();
    try {
        PreparedStatement stmt = c.getConnection().prepareStatement(sql);
        stmt.setInt(1, this.id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al eliminar el producto: " + e.getMessage());
    } finally {
        c.closeConnection();
    }
}

    // Método para recuperar la imagen de la base de datos
    /*public byte[] obtenerImagen() {
        byte[] img = null;
        String sql = "SELECT imagen FROM Producto WHERE id = ?";
        Conexion c = new Conexion();
        try {
            PreparedStatement stmt = c.getConnection().prepareStatement(sql);
            stmt.setInt(1, this.id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                img = rs.getBytes("imagen"); // Recupera la imagen (BLOB)
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la imagen: " + e.getMessage());
        } finally {
            c.closeConnection();
        }
        return img;
    }*/

    // Método para listar productos, incluyendo su imagen
    public static ArrayList<Producto> listarProductos() {
        String sql = "SELECT * FROM Producto";
        Conexion c = new Conexion();
        ResultSet rs = c.executeQuerysql(sql);
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre_producto(rs.getString("nombre_producto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setCategoria(rs.getString("categoria"));
                p.setMarca(rs.getString("marca"));
                p.setPrecio(rs.getInt("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setImagen(rs.getBytes("imagen")); // Recupera la imagen
                productos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        } finally {
            c.closeConnection();
        }
        return productos;
    }
    
    // Método de la clase Producto para obtener productos filtrados
public static ArrayList<Producto> listarProductosFiltrados(String texto) {
    String sql = "SELECT * FROM Producto WHERE LOWER(nombre_producto) LIKE ? OR LOWER(descripcion) LIKE ?";
    Conexion c = new Conexion();
    ResultSet rs = c.executeQuerysql(sql, "%" + texto.toLowerCase() + "%", "%" + texto.toLowerCase() + "%");
    ArrayList<Producto> productos = new ArrayList<>();
    try {
        while (rs.next()) {
            Producto p = new Producto();
            p.setId(rs.getInt("id"));
            p.setNombre_producto(rs.getString("nombre_producto"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setCategoria(rs.getString("categoria"));
            p.setMarca(rs.getString("marca"));
            p.setPrecio(rs.getInt("precio"));
            p.setCantidad(rs.getInt("cantidad"));
            p.setImagen(rs.getBytes("imagen"));
            productos.add(p);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar productos filtrados: " + e.getMessage());
    } finally {
        c.closeConnection();
    }
    return productos;
}
    //nuevo
public void cambiarCantidadProducto() {
    String sql = "UPDATE Producto SET cantidad = ? WHERE id = ?";
    Conexion c = new Conexion();
    try (PreparedStatement stmt = c.getConnection().prepareStatement(sql)) {
        stmt.setInt(1, this.cantidad);  // Asignar la cantidad directamente desde el atributo de la clase
        stmt.setInt(2, this.id);        // Usar el id de la clase actual
        stmt.executeUpdate();           // Ejecutar la actualización
    } catch (SQLException e) {
        System.out.println("Error al establecer la nueva cantidad del producto: " + e.getMessage());
    } finally {
        c.closeConnection();
    }
}


public void sustraerCantidadProducto() {
    String sql = "UPDATE Producto SET cantidad = cantidad - ? WHERE id = ?";
    Conexion c = new Conexion();
    try (PreparedStatement stmt = c.getConnection().prepareStatement(sql)) {
        stmt.setInt(1, this.cantidad);  // Restar la cantidad del atributo de la clase
        stmt.setInt(2, this.id);        // Usar el id de la clase actual
        stmt.executeUpdate();           // Ejecutar la actualización
    } catch (SQLException e) {
        System.out.println("Error al actualizar la cantidad del producto: " + e.getMessage());
    } finally {
        c.closeConnection();
    }
}

public void devolverCantidadProducto() {
    String sql = "UPDATE Producto SET cantidad = cantidad + ? WHERE id = ?";
    Conexion c = new Conexion();
    try (PreparedStatement stmt = c.getConnection().prepareStatement(sql)) {
        stmt.setInt(1, this.cantidad);  // Sumar la cantidad del atributo de la clase
        stmt.setInt(2, this.id);        // Usar el id de la clase actual
        stmt.executeUpdate();           // Ejecutar la actualización
    } catch (SQLException e) {
        System.out.println("Error al devolver la cantidad del producto: " + e.getMessage());
    } finally {
        c.closeConnection();
    }
}



public int obtenerCantidadProductoDisponible() {
    String sql = "SELECT cantidad FROM Producto WHERE id = ?";
    Conexion c = new Conexion();
    try (PreparedStatement stmt = c.getConnection().prepareStatement(sql)) {
        stmt.setInt(1, this.id); // Usar directamente el id del objeto actual
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("cantidad");
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener la cantidad disponible del producto: " + e.getMessage());
    } finally {
        c.closeConnection();
    }
    return 0; // Devuelve 0 si no se encuentra el producto o hay un error
}

}
