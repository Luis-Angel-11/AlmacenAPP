/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author bruno
 */
public class Nodo {
    public proveedor info; // El objeto de tipo proveedor
    public Nodo siguiente; // Puntero al siguiente nodo

    // Constructor para inicializar el nodo con un proveedor
    public Nodo(proveedor info) {
        this.info = info;
        this.siguiente = null; // El siguiente nodo es nulo por defecto
    }
}
