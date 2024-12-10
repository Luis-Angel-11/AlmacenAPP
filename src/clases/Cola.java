/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author ASUS
 */
public class Cola<T> {
    private Nodo1<T> frente;
    private Nodo1<T> fin;
    private int numElementos;

    public Cola() {
        frente = null;
        fin = null;
        numElementos = 0;
    }

    // Método para agregar un nuevo elemento a la cola
    public void agregar(T clase) {
        Nodo1<T> nuevoNodo = new Nodo1<>(clase);
        if (fin != null) {
            fin.siguiente = nuevoNodo; // El último nodo apunta al nuevo nodo
        }
        fin = nuevoNodo; // El nuevo nodo es el último
        if (frente == null) {
            frente = nuevoNodo; // Si la cola estaba vacía, el primer nodo es el nuevo nodo
        }
        numElementos++;
        System.out.println("Elemento agregado a la cola: " + clase);
    }

    // Método para eliminar un elemento de la cola
    public void eliminar() {
        if (frente != null) {
            T datoEliminado = frente.clase;
            frente = frente.siguiente; // El primer nodo es eliminado
            if (frente == null) {
                fin = null; // Si la cola queda vacía, el último nodo también es null
            }
            numElementos--;
            System.out.println("Elemento eliminado de la cola: " + datoEliminado);
        } else {
            System.out.println("La cola está vacía. No se puede eliminar.");
        }
    }

    // Ver el primer elemento de la cola sin eliminarlo
    public void verFrente() {
        if (frente != null) {
            System.out.println("Primer elemento en la cola: " + frente.clase);
        } else {
            System.out.println("La cola está vacía.");
        }
    }

    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // Método para obtener el número de elementos en la cola
    public int getNumElementos() {
        return numElementos;
    }

    // Método para imprimir todos los elementos de la cola
    public void imprimir() {
        if (frente == null) {
            System.out.println("La cola está vacía.");
            return;
        }
        Nodo1<T> actual = frente;
        while (actual != null) {
            System.out.println(actual.clase);
            actual = actual.siguiente;
        }
    }
}
