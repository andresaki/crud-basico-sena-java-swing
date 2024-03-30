package Controlador;

// IMPORTACIONES
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.Ingreso;

public class IngresoSql {

    BaseDeDatos conexionBD = new BaseDeDatos();
    Connection con;
    PreparedStatement preparedStatement;
    ResultSet ResultSet;

    public List AllIngresos() {

        String sql = "select * from ingresos";                                  //Query 

        List<Ingreso> listaIngresos = new ArrayList<>();                        //Nuevo array para guardar el resultado de la query
        try {
            con = conexionBD.conectarBaseDeDatos();                             //hace la conexion a la base de datos

            preparedStatement = con.prepareStatement(sql);                //Envia la query a la base de datos
            ResultSet = preparedStatement.executeQuery();                       //obtiene el resultado de la Query

            while (ResultSet.next()) {                                          //Hace una iteraccion del resultado de la Query hasta el ultimo
                Ingreso ingreso = new Ingreso();                                //Creamos un objeto de tipo Ingreso para guardar cada registro , el array solo acepta objeto de tipo Ingreso para eso se crea
                ingreso.setId(ResultSet.getInt(1));                        //Utilizamos el metodo SET de ingreso para guardar con GET lo que nos trae el resultado de la Query y le indicamos el numero de columna de la base datos
                ingreso.setCategoria(ResultSet.getString(2));
                ingreso.setDescripcion(ResultSet.getString(4));
                ingreso.setMonto(ResultSet.getInt(3));
                ingreso.setFecha(ResultSet.getDate(5));

                listaIngresos.add(ingreso);                                   //Agregamos el objeto Ingreso que cree al array
            }

        } catch (SQLException e) {                                              //Catch para saber si hubo un error
            System.out.println("Error : " + e);
        } finally {

            //Cerramos la conexion , preraredStament , ResultSet para liberar memoria y concecciones abiertas
            try {
                if (ResultSet != null) {
                    ResultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e);
            }
        }

        System.out.println("listar sql : OK");
        return listaIngresos;                                                   //Retorna el array 
    }
    
    
    public List BuscarPorCategoria(String busqueda) {

        String sql = "select * from ingresos where categoria = ? ";                                  //Query 

        List<Ingreso> listaIngresos = new ArrayList<>();                        //Nuevo array para guardar el resultado de la query
        try {
            con = conexionBD.conectarBaseDeDatos();                             //hace la conexion a la base de datos

            preparedStatement = con.prepareStatement(sql);   
            preparedStatement.setString(1, busqueda);
            ResultSet = preparedStatement.executeQuery();                       //obtiene el resultado de la Query

            while (ResultSet.next()) {                                          //Hace una iteraccion del resultado de la Query hasta el ultimo
                Ingreso ingreso = new Ingreso();                                //Creamos un objeto de tipo Ingreso para guardar cada registro , el array solo acepta objeto de tipo Ingreso para eso se crea
                ingreso.setId(ResultSet.getInt(1));                        //Utilizamos el metodo SET de ingreso para guardar con GET lo que nos trae el resultado de la Query y le indicamos el numero de columna de la base datos
                ingreso.setCategoria(ResultSet.getString(2));
                ingreso.setDescripcion(ResultSet.getString(4));
                ingreso.setMonto(ResultSet.getInt(3));
                ingreso.setFecha(ResultSet.getDate(5));

                listaIngresos.add(ingreso);                                   //Agregamos el objeto Ingreso que cree al array
            }

        } catch (SQLException e) {                                              //Catch para saber si hubo un error
            System.out.println("Error : " + e);
        } finally {

            //Cerramos la conexion , preraredStament , ResultSet para liberar memoria y concecciones abiertas
            try {
                if (ResultSet != null) {
                    ResultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e);
            }
        }

        System.out.println("listar por categoria  : OK");
        return listaIngresos;                                                   //Retorna el array 
    }
    
    

    public void NewIngreso(Ingreso ingreso) {

        String sql = "INSERT INTO ingresos (categoria, monto, descripcion) VALUES (?, ?, ?)";               // Usamos ? para poner despues los valores

        try {
            con = conexionBD.conectarBaseDeDatos();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, ingreso.getCategoria());
            preparedStatement.setInt(2, ingreso.getMonto());
            preparedStatement.setString(3, ingreso.getDescripcion());

            preparedStatement.executeUpdate();                                                              // Ejecutamos

        } catch (SQLException e) {
            System.out.println("Error : " + e);
        } finally {

            //Cerramos la conexion , preraredStament , ResultSet para liberar memoria y concecciones abiertas
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e);
            }
        }

        System.out.println("Agregar sql : OK");
    }

    public void actualizarIngreso(Ingreso ingreso) {

        String sql = "UPDATE ingresos SET categoria = ?, monto = ?, descripcion = ? WHERE id = ?";

        try {
            con = conexionBD.conectarBaseDeDatos();
            preparedStatement = con.prepareStatement(sql);

            // Establecer valores con métodos de prepared statement
            preparedStatement.setString(1, ingreso.getCategoria());
            preparedStatement.setInt(2, ingreso.getMonto());
            preparedStatement.setString(3, ingreso.getDescripcion());
            preparedStatement.setInt(4, ingreso.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e);
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e);
            }
        }

        System.out.println("Actualizar : OK");
    }

    public Ingreso buscarPorID(String id) {

        String sql = "select * from ingresos where id=?;";

        Ingreso ingreso = new Ingreso();
        try {
            con = conexionBD.conectarBaseDeDatos();

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet = preparedStatement.executeQuery();

            if (ResultSet.next()) { // Mover el cursor al primer resultado
                ingreso.setId(ResultSet.getInt(1));
                ingreso.setCategoria(ResultSet.getString(2));
                ingreso.setDescripcion(ResultSet.getString(4));
                ingreso.setMonto(ResultSet.getInt(3));
                ingreso.setFecha(ResultSet.getDate(5));
            }

        } catch (SQLException e) {
            System.out.println("Error : " + e);
        } finally {

            try {
                if (ResultSet != null) {
                    ResultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e);
            }
        }

        System.out.println("Ingreso obtenido por ID : OK");
        return ingreso;
    }
    
    
    
    
    public void EliminarPorId(String id) {

        String sql = "delete from ingresos where id= ? ";

        try {
            con = conexionBD.conectarBaseDeDatos();

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id);
            
            preparedStatement.execute();


        } catch (SQLException e) {
            System.out.println("Error : " + e);
        } finally {

            try {
                if (ResultSet != null) {
                    ResultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e);
            }
        }

        System.out.println("Eliminat por ID : OK");
        
    }

}
