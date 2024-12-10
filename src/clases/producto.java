package clases;

public class producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
    private proveedor proveedor; 
    private String categoria;
    private String estado;
    // Constructor para inicializar todos los atributos, incluyendo el ID
    public producto(int id, String nombre, double precio, int cantidad, proveedor proveedor, String categoria, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    // Método toString() actualizado para incluir el ID
    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio 
               + ", cantidad=" + cantidad + ", categoria=" + categoria + ", estado=" + estado + "]";
    }

    public proveedor getProveedor() {
    return proveedor;
}

    public void setProveedor(proveedor proveedor) {
    this.proveedor = proveedor;
}
}