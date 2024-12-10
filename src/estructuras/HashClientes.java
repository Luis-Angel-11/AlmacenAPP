package estructuras;

import java.util.LinkedList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import clases.Clientes;
import conexion.SQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class HashClientes {

    private LinkedList<Clientes>[] tablaClientes;
    private int cantidadElementos;

    public HashClientes() {
        tablaClientes = new LinkedList[16]; // Tamaño inicial
        cantidadElementos = 0;
        for (int i = 0; i < tablaClientes.length; i++) {
            tablaClientes[i] = new LinkedList<>();
        }
    }

    public void cargarClientesDesdeBD() {
        SQLConexion conexion = SQLConexion.getConexion();
        Connection conn = conexion.conectar();

        String query = "SELECT dni, nombre, apellido, sexo, telefono, ciudad, email FROM clientes";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String sexo = rs.getString("sexo");
                String telefono = rs.getString("telefono");
                String ciudad = rs.getString("ciudad");
                String email = rs.getString("email");

                Clientes cliente = new Clientes(dni, nombre, apellido, sexo, telefono, ciudad, email);

                agregarCliente(cliente);
            }
            System.out.println("Datos de clientes cargados desde la base de datos.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String calcularHash(String dni, String nombre, String apellido) {
        try {
            String input = dni + nombre + apellido;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString().substring(0, 8); 

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void agregarCliente(Clientes cliente) {
        redimensionarTabla(); 

        String hash = calcularHash(cliente.getDni(), cliente.getNombre(), cliente.getApellido());
        int indice = Math.abs(hash.hashCode()) % tablaClientes.length;  

        LinkedList<Clientes> lista = tablaClientes[indice];

        for (Clientes c : lista) {
            if (c.getDni().equals(cliente.getDni())) {
                System.out.println("El cliente con DNI " + cliente.getDni() + " ya está registrado.");
                return;
            }
        }

        lista.add(cliente);
        cantidadElementos++;
        System.out.println("Cliente con DNI " + cliente.getDni() + " agregado.");
    }

    private void redimensionarTabla() {
        if (cantidadElementos >= tablaClientes.length) {
            System.out.println("Redimensionando la tabla...");
            LinkedList<Clientes>[] nuevaTabla = new LinkedList[tablaClientes.length * 2];
            for (int i = 0; i < nuevaTabla.length; i++) {
                nuevaTabla[i] = new LinkedList<>();
            }

            for (LinkedList<Clientes> lista : tablaClientes) {
                for (Clientes cliente : lista) {
                    String hash = calcularHash(cliente.getDni(), cliente.getNombre(), cliente.getApellido());
                    int indice = Math.abs(hash.hashCode()) % nuevaTabla.length;
                    nuevaTabla[indice].add(cliente);
                }
            }

            tablaClientes = nuevaTabla; 
        }
    }

    public void imprimirClientes() {
        System.out.println("Clientes registrados en la tabla hash:");
        for (int i = 0; i < tablaClientes.length; i++) {
            LinkedList<Clientes> lista = tablaClientes[i];
            System.out.println("Índice " + i + ":");
            for (Clientes c : lista) {
                String hash = calcularHash(c.getDni(), c.getNombre(), c.getApellido());
                System.out.println("  " + c + " | Hash: " + hash);
            }
        }
    }

    public void eliminarCliente(String dni) {
        String hash = calcularHash(dni, "", "");
        int indice = Math.abs(hash.hashCode()) % tablaClientes.length;

        LinkedList<Clientes> lista = tablaClientes[indice];

        boolean eliminado = false;

        Clientes clienteAEliminar = null;
        for (Clientes cliente : lista) {
            if (cliente.getDni().equals(dni)) {
                clienteAEliminar = cliente;
                break;
            }
        }

        if (clienteAEliminar != null) {
            lista.remove(clienteAEliminar);
            cantidadElementos--;
            System.out.println("Cliente eliminado de la tabla hash.");
        } else {
            System.out.println("Cliente no encontrado en la tabla hash.");
        }
    }

    public Clientes buscarCliente(String dni, String nombre, String apellido) {
        // Calcular el índice utilizando la misma función de hash
        String hash = calcularHash(dni, nombre, apellido);
        int indice = Math.abs(hash.hashCode()) % tablaClientes.length;  // Aseguramos que el índice no sea negativo

        LinkedList<Clientes> lista = tablaClientes[indice];  // Acceder a la lista en ese índice

        // Recorrer la lista para encontrar el cliente
        for (Clientes cliente : lista) {
            // Verificamos si el DNI, nombre o apellido coinciden
            if ((!dni.isEmpty() && cliente.getDni().equals(dni)) ||
                (!nombre.isEmpty() && cliente.getNombre().equalsIgnoreCase(nombre)) ||
                (!apellido.isEmpty() && cliente.getApellido().equalsIgnoreCase(apellido))) {
                return cliente;
            }
        }

        // Si no encontramos el cliente
        return null;
    }
    /*public void eliminarClientePorHash(String hash) {
    // Calcular el índice utilizando el hash
    int indice = Math.abs(hash.hashCode()) % tablaClientes.length;
    
    // Obtener la lista en el índice calculado
    LinkedList<Clientes> lista = tablaClientes[indice];
    
    // Buscar y eliminar el cliente de la lista usando el hash
    Iterator<Clientes> iterator = lista.iterator();
    while (iterator.hasNext()) {
        Clientes cliente = iterator.next();
        // Verificamos si el cliente tiene el mismo hash
        if (calcularHash(cliente.getDni(), cliente.getNombre(), cliente.getApellido()).equals(hash)) {
            iterator.remove();  // Eliminar el cliente de la lista
            cantidadElementos--;
            System.out.println("Cliente con hash " + hash + " eliminado de la tabla hash.");
            return;
        }
    }

    // Si no encontramos el cliente
    System.out.println("Cliente con hash " + hash + " no encontrado en la tabla hash.");
}*/


    // Método para obtener la tabla hash (opcional, si necesitas acceder desde la interfaz)
    public LinkedList<Clientes>[] getTablaClientes() {
        return tablaClientes;
    }
}
