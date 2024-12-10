package estructuras;

import java.util.LinkedList;
import clases.Clientes;

public class HashClientes {

    // Tabla hash: cada posición es una lista enlazada que almacena objetos 'Clientes'
    private LinkedList<Clientes>[] tablaClientes;
    private int cantidadElementos;

    public HashClientes() {
        // Inicializar la tabla hash con un tamaño pequeño
        tablaClientes = new LinkedList[16]; // Empezamos con una capacidad inicial
        cantidadElementos = 0;
        for (int i = 0; i < tablaClientes.length; i++) {
            tablaClientes[i] = new LinkedList<>();
        }
    }

    private int calcularHash(String dni, String nombre, String apellido) {
        // Calcular el índice combinando los valores hash de DNI, nombre y apellido
        int hash = (dni.hashCode() + nombre.hashCode() + apellido.hashCode());
        return Math.abs(hash);
    }

    private void redimensionarTabla() {
        // Si la tabla está llena, duplicamos su tamaño
        if (cantidadElementos >= tablaClientes.length) {
            System.out.println("Redimensionando la tabla...");
            LinkedList<Clientes>[] nuevaTabla = new LinkedList[tablaClientes.length * 2]; // Doblamos el tamaño
            for (int i = 0; i < nuevaTabla.length; i++) {
                nuevaTabla[i] = new LinkedList<>();
            }

            // Reasignamos todos los elementos a la nueva tabla
            for (LinkedList<Clientes> lista : tablaClientes) {
                for (Clientes cliente : lista) {
                    int indice = calcularHash(cliente.getDni(), cliente.getNombre(), cliente.getApellido()) % nuevaTabla.length;
                    nuevaTabla[indice].add(cliente);
                }
            }

            tablaClientes = nuevaTabla; // Reemplazamos la tabla vieja por la nueva
        }
    }

    public void agregarCliente(Clientes cliente) {
        redimensionarTabla(); // Verificamos si necesitamos redimensionar

        // Calcular el índice de la tabla hash usando el DNI, nombre y apellido
        int hash = calcularHash(cliente.getDni(), cliente.getNombre(), cliente.getApellido());
        int indice = hash % tablaClientes.length;

        // Imprimir el hash y el índice
        System.out.println("Hash calculado: " + hash);
        System.out.println("Índice en la tabla: " + indice);
        // Obtener la lista enlazada en la posición calculada
        LinkedList<Clientes> lista = tablaClientes[indice];

        // Verificar si el cliente ya existe (para evitar duplicados)
        for (Clientes c : lista) {
            if (c.getDni().equals(cliente.getDni())) {
                System.out.println("El cliente con DNI " + cliente.getDni() + " ya está registrado.");
                return;
            }
        }

        // Si no se encontró un cliente con el mismo DNI, agregamos el nuevo cliente
        lista.add(cliente);
        cantidadElementos++; // Incrementamos la cantidad de elementos
        System.out.println("Cliente con DNI " + cliente.getDni() + " agregado.");
    }

    public Clientes obtenerCliente(String dni) {
        int indice = calcularHash(dni, "", "") % tablaClientes.length; // Calcular el índice usando solo el DNI

        // Obtener la lista enlazada en la posición calculada
        LinkedList<Clientes> lista = tablaClientes[indice];

        // Buscar el cliente en la lista
        for (Clientes c : lista) {
            if (c.getDni().equals(dni)) {
                return c;
            }
        }

        // Si no se encuentra el cliente, retornar null
        return null;
    }

    // Método para imprimir los clientes almacenados en la tabla
    public void imprimirClientes() {
    System.out.println("Clientes registrados en la tabla hash:");
    for (int i = 0; i < tablaClientes.length; i++) {
        LinkedList<Clientes> lista = tablaClientes[i];
        System.out.println("Índice " + i + ":");
        for (Clientes c : lista) {
            int hash = calcularHash(c.getDni(), c.getNombre(), c.getApellido());
            System.out.println("  " + c + " | Hash: " + hash);
        }
    }
}
}
