/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this actuallate
 */
package clases;

/**
 *
 * @author bruno
 */
public class listaEnlazada {
     private Nodo cabeza;
     
         // Constructor
    public listaEnlazada() {
        this.cabeza = null;
    }
        
        public void agregar (proveedor p){
            Nodo nuevo = new Nodo(p);
            if (cabeza == null) {
            cabeza = nuevo; // Si la lista está vacía, el nuevo Nodo es la cabeza
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo; // Agregar al final
        }
        }
        
        public Nodo getCabeza() {
        return cabeza;
        }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }
     
        
        

}
