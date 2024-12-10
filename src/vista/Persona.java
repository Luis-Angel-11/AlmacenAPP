/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botonreporte;

/**
 *
 * @author FAURIZIO
 */
public class Persona {
    //CREAMOS SUS ATRIBUTOS
    String Nombre;
    String Producto;
    String Precio;
    String Categoría;
    int celular;

    public Persona() {
        this.Nombre ="";
        this.Producto ="";
        this.Precio ="";
        this.Categoría ="";
        this.celular =0;
    }

    
    
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    public String getCategoría() {
        return Categoría;
    }

    public void setCategoría(String Categoría) {
        this.Categoría = Categoría;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }
    
    
    
    
}
