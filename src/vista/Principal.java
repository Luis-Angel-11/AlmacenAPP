
package vista;

import conexion.SQLConexion;
import interfaces.FrameLogin;

public class Principal {

    public static void main(String[] args) {
        SQLConexion conexion= new SQLConexion();
        conexion.conectar();
        
        FrameLogin framelogin = new FrameLogin();
        framelogin.setVisible(true);
        framelogin.setLocationRelativeTo(null);
    }
    
}
