package Controlador;

import Modelo.Ingreso;
import Vista.ActualizarEliminar;
import Vista.Ingresos;
import Vista.agregar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Controller implements ActionListener {

    //Atributos
    IngresoSql x = new IngresoSql();
    Ingresos inicio;
    agregar agregar;
    ActualizarEliminar ud;

    //Controlador
    public Controller() {
        this.inicio = new Ingresos();
        this.agregar = new agregar();
        this.ud = new ActualizarEliminar();

        //Poner Censor
        //Botones inicio
        inicio.getBtnAgregar().addActionListener(this);
        inicio.getBtnBuscar().addActionListener(this);

        //Botones Agregar
        agregar.getBtnGuardar().addActionListener(this);
        agregar.getBtnCancelar().addActionListener(this);
        
        //botones de ActualizarEliminar
        ud.getBtnCerrar().addActionListener(this);
        ud.getBtnActualizar().addActionListener(this);
        ud.getBtnEliminar().addActionListener(this);

    }

    //Metodo principal
    public void iniciar() {
        this.inicio.setVisible(true);
        listar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Acciones de los botones del inicio
        if (e.getSource() == inicio.getBtnAgregar()) {
            agregar.setVisible(true);
            inicio.dispose();
        }
        
        
        if (e.getSource() == inicio.getBtnBuscar()) {
            listarPorCategoria();
        }
        
        
        
        
        

        //Acciones de los botones de la vista agregar
        if (e.getSource() == agregar.getBtnGuardar()) {
            guardar();
            agregar.dispose();
            iniciar();
        }
        
        if (e.getSource() == agregar.getBtnCancelar()) {
            agregar.dispose();
            iniciar();
        }
        
        
        //Aciciones de la vista de actualizar y eliminar
        if (e.getSource() == ud.getBtnCerrar()) {
            ud.dispose();
        }
        
        if (e.getSource() == ud.getBtnActualizar()) {
            
            
            actualizar();
            ud.dispose();
            iniciar();

        }
        
        if (e.getSource() == ud.getBtnEliminar()) {
            
            
            eliminar();
            ud.dispose();
            iniciar();

        }

    }

    public void obtenerIdSeleccionado(String id) {

        Ingreso ingresoObtenido = x.buscarPorID(id);
        ud.setVisible(true);
        
        
        ud.getTxtId().setText(String.valueOf( ingresoObtenido.getId()));
        ud.getTxtFecha().setText(String.valueOf(ingresoObtenido.getFecha()));
        ud.getTxtCategoria().setText(ingresoObtenido.getCategoria());
        ud.getTxtDescripcion().setText(ingresoObtenido.getDescripcion());
        ud.getTxtMonto().setText(String.valueOf( ingresoObtenido.getMonto()));
       
    }
    
    
    
    public void actualizar(){
        
        
        int id = parseInt(ud.getTxtId().getText());
        String Categoria = ud.getTxtCategoria().getText();
        String descripcion = ud.getTxtDescripcion().getText();
        int monto = parseInt(ud.getTxtMonto().getText());
        

        Ingreso ingreso = new Ingreso(id, Categoria, descripcion, monto);
        x.actualizarIngreso(ingreso);
      
    }
    
    
    public void eliminar(){
        
        String id = ud.getTxtId().getText();
        x.EliminarPorId(id);
        
    }
    
    
    
    
    public void listarPorCategoria( ) {

        
        String busqueda = inicio.getTxtBuscar().getText();
        
        List<Ingreso> listaIngresos = x.BuscarPorCategoria(busqueda);                          //Lista que nos retorna 
        List<Object[]> list = new ArrayList<>();                                //Lista de apoyo

        for (Ingreso ingreso : listaIngresos) {
            list.add(new Object[]{
                ingreso.getId(),
                ingreso.getCategoria(),
                ingreso.getMonto(),
                ingreso.getDescripcion(),
                ingreso.getFecha()
            });
        }

        inicio.getTabla().setModel(new DefaultTableModel(list.toArray(new Object[][]{}),
                new String[]{"id", "Categoria", "Monto", "descripcion", "fecha"}));
    }
    
    
  

    public void listar() {

        List<Ingreso> listaIngresos = x.AllIngresos();                          //Lista que nos retorna 
        List<Object[]> list = new ArrayList<>();                                //Lista de apoyo

        for (Ingreso ingreso : listaIngresos) {
            list.add(new Object[]{
                ingreso.getId(),
                ingreso.getCategoria(),
                ingreso.getMonto(),
                ingreso.getDescripcion(),
                ingreso.getFecha()
            });
        }

        inicio.getTabla().setModel(new DefaultTableModel(list.toArray(new Object[][]{}),
                new String[]{"id", "Categoria", "Monto", "descripcion", "fecha"}));
    }

    public void guardar() {
        String categoria = agregar.getTxtCategoria().getText();
        int monto = parseInt(agregar.getTxtMonto().getText());
        String descripcion = agregar.getTxtDescripcion().getText();

        Ingreso ingreso = new Ingreso(categoria, descripcion, monto);
        x.NewIngreso(ingreso);
    }

}
