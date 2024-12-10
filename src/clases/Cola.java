/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Cola<T> {
    private Nodo1<T> inicio;
    private Nodo1<T> fin;
    private int numElementos;
    private TablaHash<T> tablaHash;
    
    public Cola(int longitud) {
        inicio = null;
        fin = null;
        numElementos = 0;
        tablaHash = new TablaHash<>(longitud);
    }

    // Método para agregar un nuevo elemento a la cola y al hash
    public void agregar(T clase, int id) {
        Nodo1<T> nuevoNodo = new Nodo1<>(clase);
        if (fin != null) {
            fin.siguiente = nuevoNodo;
        }
        fin = nuevoNodo;
        if (inicio == null) {
            inicio = nuevoNodo;
        }
        numElementos++;

        // Agregar el movimiento a la tabla hash
        tablaHash.agregar(clase, id);
    }

    // Método para eliminar un elemento de la cola
    

    public T eliminar() {
        if (inicio == null) {
            JOptionPane.showMessageDialog(null,"La cola está vacía. No se puede eliminar.");
        }
        T datoEliminado = inicio.clase; // Obtener el elemento del frente
        inicio = inicio.siguiente; // Mover el inicio al siguiente nodo
        if (inicio == null) {
            fin = null; // Si la cola queda vacía, el último nodo también es null
        }
        numElementos--;
        return datoEliminado;
    }
    
    public T buscarPorId(int id) {
        return tablaHash.buscarPorId(id);
    }
    
    public List<T> buscarPorNombre(String nombre) {
    List<T> resultados = new ArrayList<>();
    Nodo1<T> actual = inicio; // Comenzar desde el nodo inicial

    while (actual != null) {
        if (actual.getClase() instanceof Inventarios) {
            Inventarios inventario = (Inventarios) actual.getClase();
            if (inventario.getProducto().toLowerCase().startsWith(nombre.toLowerCase())) {
                resultados.add(actual.getClase()); // Agregar el elemento que coincide
            }
        }
        actual = actual.getSiguiente(); // Mover al siguiente nodo
    }

    return resultados; // Retornar todos los resultados encontrados
}
    
    public int getNumElementos() {
        return numElementos;
    }

    
    public boolean estaVacia() {
        return inicio == null;
    }
    
    public Nodo1<T> getInicio() {
        return inicio;
    }
}
