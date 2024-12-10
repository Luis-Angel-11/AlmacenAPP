
package conexion;
import java.sql.*;

public class SQLConexion {
    private static SQLConexion conexion;
    public String driver;
    public String url;
    public String login;
    public String clave;

    public SQLConexion() {
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/almacen?useSSL=false&serverTimezone=America/Bogota";
        this.login = "root";
        this.clave = "root";    
    }
    
    public static SQLConexion getConexion(){
        if(conexion == null){
            conexion = new SQLConexion();
        }
        return conexion;
    }
    
    public Connection conectar(){
        Connection cn = null;
        try{
            Class.forName(driver);
            cn = DriverManager.getConnection(url, login, clave);
            System.out.println("Conexion establecida a la BD ");
        }catch( ClassNotFoundException e){
            e.printStackTrace();
            
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return cn;
    }
}
