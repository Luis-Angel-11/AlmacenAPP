package clases;

import java.time.LocalDate;

public class Orden {
    private int idOrden;
    private LocalDate fecha;
    private String dniCliente;
    private int cantidad;

    // Constructor
    public Orden(int idOrden, LocalDate fecha, String dniCliente, int cantidad) {
        this.idOrden = idOrden;
        this.fecha = fecha;
        this.dniCliente = dniCliente;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Sobrescribir el método toString()
    @Override
    public String toString() {
        return "Orden { " +
               "ID: " + idOrden + 
               ", Fecha: " + fecha + 
               ", Cliente DNI: '" + dniCliente + '\'' + 
               ", Cantidad: " + cantidad + 
               " }";
    }
}
