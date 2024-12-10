package clases;

public class Clientes {
    private String dni;
    private String nombre;
    private String apellido;
    private String sexo;
    private String telefono;
    private String ciudad;
    private String email;

    public Clientes(String dni, String nombre, String apellido, String sexo, String telefono, String ciudad, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {  
        return "Cliente [nombre=" + nombre + ", dni=" + dni + ", apellido=" + apellido + ", sexo=" + sexo 
                + ", telefono=" + telefono + ", ciudad=" + ciudad + ", email=" + email + "]";
    }
}
