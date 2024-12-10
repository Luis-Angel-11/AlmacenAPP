package clases.modelo;

import clases.producto;
import clases.proveedor;
import conexion.SQLConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class productoBD {
    SQLConexion cn = SQLConexion.getConexion(); // Accede al Singleton
    PreparedStatement ps;

    // Método para registrar un producto
    public boolean agregarProducto(producto nuevoProducto) {
    String sql = "INSERT INTO producto (id_producto, nombre, precio, cantidad, id_proveedor, categoria, estado) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection con = cn.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, nuevoProducto.getId());
        ps.setString(2, nuevoProducto.getNombre());
        ps.setDouble(3, nuevoProducto.getPrecio());
        ps.setInt(4, nuevoProducto.getCantidad());
        ps.setInt(5, nuevoProducto.getProveedor().getId_proveedor());
        ps.setString(6, nuevoProducto.getCategoria());
        ps.setString(7, nuevoProducto.getEstado());

        int filasInsertadas = ps.executeUpdate();
        return filasInsertadas > 0; // Retorna true si se insertó correctamente
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al agregar el producto: " + e.getMessage());
        return false;
    }
    }

    // Método para listar todos los productos
    public List<producto> listarProductos() {
    List<producto> listaProductos = new ArrayList<>(); // Declarar la lista aquí

    String sql = "SELECT p.id_producto, p.nombre, p.precio, p.cantidad, p.categoria, p.estado, " +
                 "pr.id_proveedor, pr.nombre AS nombre_proveedor " +
                 "FROM producto p " +  
                 "JOIN proveedor pr ON p.id_proveedor = pr.id_proveedor";  

    try (Connection con = cn.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id_producto");
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            int cantidad = rs.getInt("cantidad");
            String categoria = rs.getString("categoria");
            String estado = rs.getString("estado");

            // Crear el proveedor
            int idProveedor = rs.getInt("id_proveedor");
            String nombreProveedor = rs.getString("nombre_proveedor");
            proveedor proveedorSeleccionado = new proveedor(idProveedor, nombreProveedor);

            // Crear el producto
            producto p = new producto(id, nombre, precio, cantidad, proveedorSeleccionado, categoria, estado);
            listaProductos.add(p);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al listar productos: " + e.toString());
    }

    return listaProductos;
    }


    // Método para eliminar un producto por nombre
    public boolean eliminarProducto(String nombre) {
        String sql = "DELETE FROM producto WHERE nombre = ?";
        try (Connection con = cn.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.toString());
            return false;
        }
    }

    // Método para modificar un producto
    public boolean modificarProducto(producto p) {
        String sql = "UPDATE producto SET precio = ?, cantidad = ?, categoria = ?, estado = ? WHERE nombre = ?";
        try (Connection con = cn.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, p.getPrecio());
            ps.setInt(2, p.getCantidad());
            ps.setString(3, p.getCategoria());
            ps.setString(4, p.getEstado());
            ps.setString(5, p.getNombre());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al modificar producto: " + e.toString());
            return false;
        }
    }

    public List<proveedor> listarProveedores() {
    List<proveedor> listaProveedores = new ArrayList<>();
    String sql = "SELECT id_proveedor, nombre FROM proveedor";  // Cambié 'proveedores' a 'proveedor'

    try (Connection con = cn.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id_proveedor");
            String nombre = rs.getString("nombre");
            listaProveedores.add(new proveedor(id, nombre));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al listar proveedores: " + e.toString());
    }
    return listaProveedores;  // Cambié 'proveedores' a 'listaProveedores'
    }
    
    public List<producto> ordenarProductosPorPrecio() {
    List<producto> listaProductos = new ArrayList<>();
    String sql = "SELECT p.id_producto, p.nombre, p.precio, p.cantidad, p.categoria, p.estado, " +
                 "pr.id_proveedor, pr.nombre AS nombre_proveedor " +
                 "FROM producto p " +
                 "JOIN proveedor pr ON p.id_proveedor = pr.id_proveedor " +
                 "ORDER BY p.precio ASC";  // Orden ascendente por precio

    try (Connection con = cn.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id_producto");
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            int cantidad = rs.getInt("cantidad");
            String categoria = rs.getString("categoria");
            String estado = rs.getString("estado");

            // Crear proveedor
            int idProveedor = rs.getInt("id_proveedor");
            String nombreProveedor = rs.getString("nombre_proveedor");
            proveedor proveedorSeleccionado = new proveedor(idProveedor, nombreProveedor);

            // Crear producto
            producto p = new producto(id, nombre, precio, cantidad, proveedorSeleccionado, categoria, estado);
            listaProductos.add(p);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al ordenar productos: " + e.toString());
    }

    return listaProductos;
    }
    
    public boolean eliminarProductoPorNombre(String nombre) {
    String sql = "DELETE FROM producto WHERE nombre = ?";
    try (Connection con = cn.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);

        int filasEliminadas = ps.executeUpdate();
        return filasEliminadas > 0; // Retorna true si se eliminó correctamente
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.toString());
        return false;
    }
    }

    public producto buscarProductoPorNombre(String nombre) {
    String sql = "SELECT p.id_producto, p.nombre, p.precio, p.cantidad, p.categoria, p.estado, " +
                 "pr.id_proveedor, pr.nombre AS nombre_proveedor " +
                 "FROM producto p " +
                 "JOIN proveedor pr ON p.id_proveedor = pr.id_proveedor " +
                 "WHERE p.nombre = ?";

    try (Connection con = cn.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int id = rs.getInt("id_producto");
                String nombreProducto = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String categoria = rs.getString("categoria");
                String estado = rs.getString("estado");

                // Crear proveedor
                int idProveedor = rs.getInt("id_proveedor");
                String nombreProveedor = rs.getString("nombre_proveedor");
                proveedor proveedorSeleccionado = new proveedor(idProveedor, nombreProveedor);

                // Crear producto
                return new producto(id, nombreProducto, precio, cantidad, proveedorSeleccionado, categoria, estado);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar el producto: " + e.toString());
    }

    return null; // Retorna null si no se encontró
    }

}