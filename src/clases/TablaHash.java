/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class TablaHash<T> {
    private List<T>[] tabla;
    private int tamaño;

    public TablaHash(int tamaño) {
        this.tamaño = tamaño;
        tabla = new ArrayList[tamaño];
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = new ArrayList<>();
        }
    }

    // Función de hashing personalizada para ID de tipo int
    private int hash(int id) {
        return id % tamaño;
    }

    // Método para agregar un movimiento
    public void agregar(T clase, int id) {
        int indice = hash(id);
        tabla[indice].add(clase);
    }
    
    public T buscarPorId(int id) {
        int indice = hash(id);
        for (T elemento : tabla[indice]) {
            if (elemento instanceof Inventarios && ((Inventarios) elemento).getId() == id) {
                return elemento;
            }
        }
        return null;
    }
    public List<T> buscarPorNombre(String nombre) {
        List<T> resultados = new ArrayList<>();
        for (List<T> lista : tabla) {
            for (T elemento : lista) {
                if (elemento instanceof Inventarios) {
                    Inventarios inventario = (Inventarios) elemento;
                    if (inventario.getProducto().toLowerCase().startsWith(nombre.toLowerCase())) {
                        resultados.add(elemento);
                    }
                }
            }
        }
        return resultados;
    }
}
