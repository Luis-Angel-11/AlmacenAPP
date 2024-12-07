
package clases;

public class nuevoIngreso {
    private int id_codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    private String categoria;
    private int id_proveedor;
    

    public nuevoIngreso() {
    }

    public nuevoIngreso(int id_codigo, String nombre, int cantidad, double precio, String categoria, int id_proveedor) {
        this.id_codigo = id_codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categoria = categoria;
        this.id_proveedor = id_proveedor;
    }

    public int getId_codigo() {
        return id_codigo;
    }

    public void setId_codigo(int id_codigo) {
        this.id_codigo = id_codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    

     


    
    
    
}
