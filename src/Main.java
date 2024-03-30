
import Controlador.Controller;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Controller x = new Controller();
        x.iniciar();
    }
}




/*

QUERY SQL
-- querys para crea la base de datos , la tabla y algunos registros





create database contabilidad;

create table ingresos (
	id int not null auto_increment primary key,
    categoria varchar(50),
    monto int,
    descripcion varchar(250),
    fecha datetime default current_timestamp()
);


insert into ingresos (categoria, monto , descripcion) values 
("Venta" , 50000 , "1 camisa"),
("Venta" , 20000 , "1 pantaloneta"),
("Venta" , 100000 , "1 hoddie"),
("Venta" , 100000 , "2 camisas"),
("Venta" , 45000 , "1 bermuda"),

("Contrato" , 3000000 , "250 camisas negras con estampado"),
("Contrato" , 500000 , "30 camisas para niño");


*/
