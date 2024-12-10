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
public class BotonReporte {
    
    private static frmformulario formulario;
    private static Persona persona[];
    private static int contador;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        formulario=new frmformulario();
        persona=new Persona[100];
        //CREAMOS UN ARREGLO PARA AGREGAR 0 - 99 ELEMENTOS
        //DEL ARREGLO PERSONA
        
        for (int i = 0; i < 100; i++) {
            persona[i]=new Persona();
            
        }
        contador=0;
        
        formulario.setVisible(true);
        //AHORA CREAMOS LOS MÉTODOS GUARDAR Y BUSCAR
    }
    
    public static void guardar(String n, String a, String c, String e, int cel){
        //ESTAMOS CREANDO 4 VARIABLES
        //NOMBRE n, PRODUCTO a, PRECIO c, CATEGORÍA e, CELULAR cel
        persona[contador].setNombre(n);
        persona[contador].setProducto(a);
        persona[contador].setPrecio(c);
        persona[contador].setCategoría(e);
        persona[contador].setCelular(cel);
        
        contador++;
    }
    
    public static void buscar(String bus){
        //CREAMOS LA VARIABLE
        //BUSCAR bus
        for(int j=0; j < persona.length; j++) {
            if(persona[j].getNombre().equals(bus)){
                formulario.cargardatos(persona[j]);
            }
        }
    }
    
}
