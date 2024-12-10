/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author ASUS
 */
public class Nodo1<T> {
    
    T clase;
    Nodo1<T> siguiente;

    public Nodo1(T clase) {
        this.clase = clase;
        this.siguiente = null;
    }
    
    // Método para obtener el dato del nodo
    public T getClase() {
        return clase;
    }

    // Método para obtener el siguiente nodo
    public Nodo1<T> getSiguiente() {
        return siguiente;
    }
    
}
