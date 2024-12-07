
package clases.modelo;


import clases.proveedor;
import conexion.SQLConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class proveedorDao {
    SQLConexion cn = SQLConexion.getConexion(); // Accede al Singleton
    PreparedStatement ps;
    
    public boolean registrarProveedor(proveedor p){
        String sql = "INSERT INTO proveedor (id_proveedor, nombre, ubicacion, telefono, correo)"
                   + " VALUES(?,?,?,?,?)";
        try {
            Connection con = cn.conectar();
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId_proveedor());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getUbicacion());
            ps.setInt(4, p.getTelefono());
            ps.setString(5, p.getCorreo());
            
            int rowAffected = ps.executeUpdate();
            
            if(rowAffected > 0){
              
               return true;
            }
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.toString());
        }
        return false;
    } 
    
    
    public List <proveedor> listarProveedor(){
        List<proveedor> lisproveedor = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        
         // Conexión
    try (Connection con = cn.conectar();  // Usamos el try-with-resources para asegurar que la conexión se cierre automáticamente
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        // Procesar los resultados
        while (rs.next()) {
            int id_proveedor = rs.getInt("id_proveedor");
            String nombre = rs.getString("nombre");  
            String ubicacion = rs.getString("ubicacion");
            int telefono = rs.getInt("telefono");
            String correo = rs.getString("correo");

            // Crear el objeto nuevoIngreso y agregarlo a la lista
            proveedor prove= new proveedor(id_proveedor, nombre, ubicacion, telefono, correo);
            lisproveedor.add(prove);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }

    return lisproveedor;  // Devolver la lista de productos
        
         
    }
    
    
    public boolean eliminarProveedor(int id){
        String sql = "DELETE FROM proveedor WHERE id_proveedor = ?";

        try (Connection con = cn.conectar();  // Obtener la conexión aquí
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);  // Establecer el parámetro

            // Ejecutar el comando de eliminación
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;  // Si se eliminó al menos un registro, retorna true
        } catch (SQLException e) {
            System.out.println("Error al eliminarproveedor: " + e.toString());
            return false;
        }
    }
    
    public boolean modificarProveedor(proveedor prove){
    String sql = "UPDATE proveedor SET nombre=?, ubicacion=?, telefono=?, correo=? WHERE id_proveedor=?";
    
    // Usamos try-with-resources para manejar la conexión y el PreparedStatement automáticamente
    try (Connection con = cn.conectar();  // Obtener la conexión aquí
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros en la consulta
        ps.setString(1, prove.getNombre());
        ps.setString(2, prove.getUbicacion());
        ps.setDouble(3, prove.getTelefono());
        ps.setString(4, prove.getCorreo()); 
        ps.setInt(5, prove.getId_proveedor());  // El id_ingreso es la clave primaria para actualizar

        // Ejecutar la actualización (modificación)
        int rowsAffected = ps.executeUpdate();

        // Si se afectó al menos una fila, la actualización fue exitosa
        return rowsAffected > 0;
    } catch (SQLException e) {
        // Si hay algún error, imprimir el mensaje
        System.out.println("Error al modificar el proveedor: " + e.toString());
        return false;
    }
    
    }
    
    
}
