package Controlador;

import Modelo.Ingreso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BaseDeDatos {

    Connection con;
    String bd = "contabilidad";
    String url = "jdbc:mysql://localhost:3306/" + bd + "?useUnicode=true&use" + "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" + "serverTimezone-UTC";

    String usuario = "root";
    String pwd = "Ah1021312907";
    String driver = "com.mysql.cj.jdbc.Driver";

    public Connection conectarBaseDeDatos() {
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, usuario, pwd);
            System.out.println("Conexion exitosa a la base de datos : " + bd);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se pudo conectar a la base de datos");
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public static void main(String[] args) {

        
        
        //PRUEBAS por consola
        
        IngresoSql x = new IngresoSql();
        
        

        List<Ingreso> listaIngresos = x.AllIngresos();
        for (Ingreso ingreso : listaIngresos) {
            System.out.println("Id: " + ingreso.getId());
            System.out.println("Categoria: " + ingreso.getCategoria());
            System.out.println("Descripcion: " + ingreso.getDescripcion()); // Call getDescripcion method
            System.out.println("Monto: " + ingreso.getMonto());
            System.out.println("Fecha: " + ingreso.getFecha());
            System.out.println();
        }
        
        
        //--------------------------------------------
        
        Ingreso ingreso = new Ingreso("Nuevo", "Prueba", 0);
        x.NewIngreso(ingreso);
        
        
        
        //--------------------------------------------
        
        Ingreso ingreso2 = new Ingreso("Actualizacion", "Prueba", 0);
        x.NewIngreso(ingreso);
        
        
        
    }
}
