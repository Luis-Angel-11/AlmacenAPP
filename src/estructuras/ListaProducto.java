package estructuras;

import clases.producto;

public class ListaProducto {

    // Clase Nodo para la lista doblemente enlazada
    private class Nodo {
        producto prod;
        Nodo siguiente;
        Nodo anterior;

        public Nodo(producto prod) {
            this.prod = prod;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    private Nodo cabeza;
    private Nodo cola; // Referencia al último nodo de la lista

    public ListaProducto() {
        this.cabeza = null;
        this.cola = null;
    }

    // Método para agregar un producto al final de la lista
    public void agregarProducto(producto nuevoProducto) {
        Nodo nuevoNodo = new Nodo(nuevoProducto);

        if (cabeza == null) {
            cabeza = cola = nuevoNodo; // Primer elemento
        } else {
            cola.siguiente = nuevoNodo;
            nuevoNodo.anterior = cola;
            cola = nuevoNodo; // Actualizar la cola
        }
    }

    // Método para listar productos
    public void listarProductos() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.println(temp.prod);
            temp = temp.siguiente;
        }
    }

    // Método para buscar un producto por nombre
    public producto buscarProductoPorNombre(String nombre) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.prod.getNombre().equalsIgnoreCase(nombre)) {
                return temp.prod;
            }
            temp = temp.siguiente;
        }
        return null; // Producto no encontrado
    }

    // Método para eliminar un producto por nombre
    public boolean eliminarProductoPorNombre(String nombre) {
        Nodo temp = cabeza;

        while (temp != null) {
            if (temp.prod.getNombre().equalsIgnoreCase(nombre)) {
                if (temp == cabeza) {
                    cabeza = temp.siguiente; // Actualizar cabeza
                    if (cabeza != null) cabeza.anterior = null;
                } else if (temp == cola) {
                    cola = temp.anterior; // Actualizar cola
                    if (cola != null) cola.siguiente = null;
                } else {
                    temp.anterior.siguiente = temp.siguiente;
                    temp.siguiente.anterior = temp.anterior;
                }
                return true; // Producto eliminado
            }
            temp = temp.siguiente;
        }
        return false; // Producto no encontrado
    }

    // Método para ordenar productos por precio (Bubble Sort)
    public void ordenarProductosPorPrecio() {
        if (cabeza == null) return;

        boolean swapped;
        do {
            swapped = false;
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                if (temp.prod.getPrecio() > temp.siguiente.prod.getPrecio()) {
                    // Intercambiar productos
                    producto aux = temp.prod;
                    temp.prod = temp.siguiente.prod;
                    temp.siguiente.prod = aux;
                    swapped = true;
                }
                temp = temp.siguiente;
            }
        } while (swapped);
    }
}