
package clases.modelo;


import clases.Nodo;
import clases.listaEnlazada;
import clases.proveedor;
import conexion.SQLConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import vista.Proveedores;

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
    
    //enlazado con listas enlazadas 
    public listaEnlazada listarProveedor() {
     listaEnlazada listaenlazada = new listaEnlazada();
    List<proveedor> lisproveedor = new ArrayList<>();
    String sql = "SELECT * FROM proveedor";
    try (Connection con = cn.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            int id_proveedor = rs.getInt("id_proveedor");
            String nombre = rs.getString("nombre");  
            String ubicacion = rs.getString("ubicacion");
            int telefono = rs.getInt("telefono");
            String correo = rs.getString("correo");

            proveedor prove = new proveedor(id_proveedor, nombre, ubicacion, telefono, correo);
            lisproveedor.add(prove);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
    // Convertir la lista ArrayList a una lista enlazada personalizada
    for (proveedor p : lisproveedor) {
        listaenlazada.agregar(p); // Asegúrate de tener un método `agregar` en `listaEnlazada`
    }
    return listaenlazada;
}
    
    
    public boolean eliminarProveedor(int id){
        // Eliminar de la base de datos
        String sql = "DELETE FROM proveedor WHERE id_proveedor = ?";
    try (Connection con = cn.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();

        //si la eliminacion no pasa en la base entonces elimina de la lista enlazada
        if (rowsAffected > 0) {
            listaEnlazada listaProveedores = listarProveedor(); //obtiene la lista actualizada en la bd
            Nodo actual = listaProveedores.getCabeza();
            Nodo anterior = null;

            // Recorrer la lista para buscar el nodo a eliminar
            while (actual != null) {
                if (actual.info.getId_proveedor() == id) { //busqueda
                    if (anterior == null) {
                        //si es la cabeza 
                        listaProveedores.setCabeza(actual.siguiente);
                    } else {
                        // Saltear el nodo actual
                        anterior.siguiente = actual.siguiente;
                    }
                    return true; //Eliminacion correcta
                }
                // Avanza al siguiente  nodo
                anterior = actual;
                actual = actual.siguiente;
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al eliminar proveedor: " + e.toString());
    }
    return false;
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
    
    //ordenamiento shell sort
    public void  ordenarCodigoPro(){
        //convertimos la lista en array 
        List<proveedor> lista = new ArrayList<>();
        Nodo actual = listarProveedor().getCabeza();
        while (actual != null) {
        lista.add(actual.info);
        actual = actual.siguiente;
        }
        
        // ordenamiento shell
        int n = lista.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
           for (int i = gap; i < n; i++) {
               proveedor temp = lista.get(i);
               int j = i;
               while (j >= gap && lista.get(j - gap).getId_proveedor() > temp.getId_proveedor()) {
                  lista.set(j, lista.get(j - gap));
                  j -= gap;
               }
               lista.set(j, temp);
           }
        }

        // covertimos la lista en lista enlazada
        Nodo cabeza = new Nodo(lista.get(0));
        actual = cabeza;
        for (int i = 1; i < lista.size(); i++) {
           Nodo nuevoNodo = new Nodo(lista.get(i));
           actual.siguiente = nuevoNodo;
           actual = nuevoNodo;
        }
        
        listarProveedor().setCabeza(cabeza);   
        
        Proveedores lim = new Proveedores();
        lim.limpiarTabla();
        listarProveedor();
    }
    
    
}
