/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Joel
 */
import java.util.Scanner;
import java.util.Stack;

class Producto {
    private String id;
    private String nombre;
    private int cantidad;

    public Producto(String id, String nombre, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Cantidad: " + cantidad;
    }
}

class PilaInventario {
    private Stack<Producto> pila;

    public PilaInventario() {
        pila = new Stack<>();
    }

    public void agregarProducto(Producto producto) {
        pila.push(producto);
    }

    public void mostrarInventario() {
        if (pila.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario actual:");
            for (Producto producto : pila) {
                System.out.println(producto);
            }
        }
    }

    public boolean realizarPedido(String id, int cantidadPedido) {
        for (Producto producto : pila) {
            if (producto.getId().equals(id)) {
                if (producto.getCantidad() >= cantidadPedido) {
                    Producto estadoAnterior = new Producto(producto.getId(), producto.getNombre(), producto.getCantidad());
                    pila.push(estadoAnterior);

                    producto.setCantidad(producto.getCantidad() - cantidadPedido);
                    System.out.println("Pedido realizado. Se restaron " + cantidadPedido + " unidades del producto " + producto.getNombre());
                    return true;
                } else {
                    System.out.println("No hay suficiente cantidad en el inventario para realizar el pedido.");
                    return false;
                }
            }
        }
        System.out.println("Producto no encontrado en el inventario.");
        return false;
    }

    public boolean cancelarPedido() {
        if (!pila.isEmpty()) {
            Producto estadoAnterior = pila.pop();
            for (Producto producto : pila) {
                if (producto.getId().equals(estadoAnterior.getId())) {
                    producto.setCantidad(estadoAnterior.getCantidad());
                    System.out.println("Pedido cancelado. El inventario ha sido restaurado.");
                    return true;
                }
            }
        }
        System.out.println("No se puede cancelar el pedido, pila vacía.");
        return false;
    }

    public boolean eliminarProducto(String id) {
        for (Producto producto : pila) {
            if (producto.getId().equals(id)) {
                pila.remove(producto);
                System.out.println("Producto " + id + " eliminado.");
                return true;
            }
        }
        System.out.println("Producto no encontrado.");
        return false;
    }
}

public class Pedido {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PilaInventario inventario = new PilaInventario();

        inventario.agregarProducto(new Producto("001", "Laptop", 20));
        inventario.agregarProducto(new Producto("002", "Smartphone", 30));
        inventario.agregarProducto(new Producto("003", "Tablet", 15));

        while (true) {
            System.out.println("\n----- MENU DE GESTION DE INVENTARIO -----");
            System.out.println("1. Mostrar Inventario");
            System.out.println("2. Realizar Pedido");
            System.out.println("3. Cancelar Pedido");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.println("----- Inventario Actual -----");
                    inventario.mostrarInventario();
                    break;

                case 2:
                    System.out.println("----- Realizar Pedido -----");
                    System.out.print("Ingrese ID del producto: ");
                    String idProducto = scanner.nextLine();
                    System.out.print("Ingrese cantidad a pedir: ");
                    int cantidadPedido = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer
                    boolean pedidoRealizado = inventario.realizarPedido(idProducto, cantidadPedido);
                    if (!pedidoRealizado) {
                        System.out.println("No se pudo realizar el pedido.");
                    }
                    break;

                case 3:
                    System.out.println("----- Cancelar Pedido -----");
                    boolean pedidoCancelado = inventario.cancelarPedido();
                    if (!pedidoCancelado) {
                        System.out.println("No se pudo cancelar el pedido.");
                    }
                    break;

                case 4:
                    System.out.println("----- Eliminar Producto -----");
                    System.out.print("Ingrese ID del producto a eliminar: ");
                    String idEliminar = scanner.nextLine();
                    boolean eliminado = inventario.eliminarProducto(idEliminar);
                    if (!eliminado) {
                        System.out.println("No se pudo eliminar el producto.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no valida, intente de nuevo.");
                    break;
            }
        }
    }
}

