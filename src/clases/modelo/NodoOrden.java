/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.modelo;

import clases.Orden;

/**
 *
 * @author Joel
 */
public class NodoOrden {
    private Orden orden;
    private NodoOrden izquierdo, derecho;

    // Constructor
    public NodoOrden(Orden orden) {
        this.orden = orden;
        this.izquierdo = this.derecho = null;
    }

    // Getters y Setters
    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public NodoOrden getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoOrden izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoOrden getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoOrden derecho) {
        this.derecho = derecho;
    }
}

