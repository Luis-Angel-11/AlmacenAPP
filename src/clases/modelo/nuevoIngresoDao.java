
package clases.modelo;

import clases.nuevoIngreso;
import conexion.SQLConexion;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class nuevoIngresoDao {
    
     // Usamos el patrón Singleton para obtener una sola instancia de SQLConexion
    SQLConexion cn = SQLConexion.getConexion(); // Accede al Singleton
    PreparedStatement ps;
    Connection con;

    public boolean registroProducto(nuevoIngreso ni) {
        String sql = "INSERT INTO nuevo_ingreso (id_ingreso, nombre, cantidad, precio, categoria, id_proveedor)"
                + " VALUES(?,?,?,?,?,?)";
        try {
            // Obtén la conexión utilizando el método conectar() desde SQLConexion
            Connection con = cn.conectar(); // Esto te da la conexión real de JDBC
            
            // Ahora puedes preparar la sentencia SQL con la conexión
            ps = con.prepareStatement(sql);
            ps.setInt(1, ni.getId_codigo());
            ps.setString(2, ni.getNombre());
            ps.setInt(3, ni.getCantidad());
            ps.setDouble(4, ni.getPrecio());
            ps.setString(5, ni.getCategoria());
            ps.setInt(6, ni.getId_proveedor());

            // Ejecutar la actualización (inserción)
            int rowsAffected = ps.executeUpdate();  // Usa executeUpdate() para insertar

            if (rowsAffected > 0) {
                
                JOptionPane.showMessageDialog(null, "Producto Registrado :D");
                
                
                return true; // Retorna true si la inserción fue exitosa
            }
        } catch (SQLException e) {
            // Si hay algún error, muestra el mensaje de error
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return false; // Retorna false si hubo un problema
    }
    
   public List<nuevoIngreso> listarProducto() {
    List<nuevoIngreso> lispro = new ArrayList<>();
    String sql = "SELECT * FROM nuevo_ingreso";

    // Conexión
    try (Connection con = cn.conectar();  // Usamos el try-with-resources para asegurar que la conexión se cierre automáticamente
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        // Procesar los resultados
        while (rs.next()) {
            int id_codigo = rs.getInt("id_ingreso");
            String nombre = rs.getString("nombre");
            int cantidad = rs.getInt("cantidad");
            double precio = rs.getDouble("precio");
            String categoria = rs.getString("categoria");
            int id_proveedor = rs.getInt("id_proveedor");

            // Crear el objeto nuevoIngreso y agregarlo a la lista
            nuevoIngreso ni = new nuevoIngreso(id_codigo, nombre, cantidad, precio, categoria, id_proveedor);
            lispro.add(ni);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }

    return lispro;  // Devolver la lista de productos
}
   
   public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM nuevo_ingreso WHERE id_ingreso = ?";

        try (Connection con = cn.conectar();  // Obtener la conexión aquí
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);  // Establecer el parámetro

            // Ejecutar el comando de eliminación
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;  // Si se eliminó al menos un registro, retorna true
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.toString());
            return false;
        }
    }
    
   public boolean modificarProducto(nuevoIngreso ni) {
    String sql = "UPDATE nuevo_ingreso SET nombre=?, cantidad=?, precio=?, categoria=?, id_proveedor=? WHERE id_ingreso=?";
    
    // Usamos try-with-resources para manejar la conexión y el PreparedStatement automáticamente
    try (Connection con = cn.conectar();  // Obtener la conexión aquí
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros en la consulta
        ps.setString(1, ni.getNombre());
        ps.setInt(2, ni.getCantidad());
        ps.setDouble(3, ni.getPrecio());
        ps.setString(4, ni.getCategoria());
        ps.setInt(5, ni.getId_proveedor());
        ps.setInt(6, ni.getId_codigo());  // El id_ingreso es la clave primaria para actualizar

        // Ejecutar la actualización (modificación)
        int rowsAffected = ps.executeUpdate();

        // Si se afectó al menos una fila, la actualización fue exitosa
        return rowsAffected > 0;
    } catch (SQLException e) {
        // Si hay algún error, imprimir el mensaje
        System.out.println("Error al modificar producto: " + e.toString());
        return false;
    }
}
   
   
   
}
