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
                System.out.println("datos cargaodsss");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private String calcularHash(String dni, String nombre, String apellido) {
            try {
                String input = dni + nombre + apellido;
                MessageDigest digest = MessageDigest.getInstance("SHA-256"); //genera un hash de 256 bits
                byte[] hashBytes = digest.digest(input.getBytes());// genera el hash de entrada en formato de bytes.

                StringBuilder hexString = new StringBuilder();
                for (byte b : hashBytes) {
                    hexString.append(String.format("%02x", b)); // convierte cada byte a su representación hexadecima
                }

                return hexString.toString().substring(0, 8); 

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return "";
            }
        }

        public void agregarCliente(Clientes cliente) {

            String hash = calcularHash(cliente.getDni(), cliente.getNombres(), cliente.getApellido());
            int indice = Math.abs(hash.hashCode()) % tablaClientes.length;  

            LinkedList<Clientes> lista = tablaClientes[indice];

            for (Clientes c : lista) {
                if (c.getDni().equals(cliente.getDni())) {
                    System.out.println("El cliente con DNI " + cliente.getDni() + " ya esta registrado.");
                    return;
                }
            }

            lista.add(cliente);
            cantidadElementos++;
            System.out.println("Cliente con DNI " + cliente.getDni() + " agregado.");
        }


        public void imprimirClientes() {
            System.out.println("Clientes registrados en la tabla hash:");
            for (int i = 0; i < tablaClientes.length; i++) {
                LinkedList<Clientes> lista = tablaClientes[i];
                System.out.println("Índice " + i + ":");
                for (Clientes c : lista) {
                    String hash = calcularHash(c.getDni(), c.getNombres(), c.getApellido());
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
                System.out.println("Cliente eliminado de la tabla");
            } else {
                System.out.println("Cliente no encontrado en la tabla ");
            }
        }

        public Clientes buscarCliente(String dni, String nombre, String apellido) {
            String hash = calcularHash(dni, nombre, apellido);
            int indice = Math.abs(hash.hashCode()) % tablaClientes.length;  

            LinkedList<Clientes> lista = tablaClientes[indice];  

            for (Clientes cliente : lista) {
                if ((!dni.isEmpty() && cliente.getDni().equals(dni)) ||
                    (!nombre.isEmpty() && cliente.getNombres().equalsIgnoreCase(nombre)) ||
                    (!apellido.isEmpty() && cliente.getApellido().equalsIgnoreCase(apellido))) {
                    return cliente;
                }
            }

            return null;
        }

        public LinkedList<Clientes>[] getTablaClientes() {
            return tablaClientes;
        }
    }
