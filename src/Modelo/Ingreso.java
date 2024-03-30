package Modelo;

import java.util.Date;

public class Ingreso {
    
    //Atributos
    private  int id;
    private String Categoria;
    private String descripcion;
    private int monto;
    private Date fecha;
    
    
    public Ingreso(){
        
    }
    
    
    // constructor para crear

    public Ingreso(String Categoria, String descripcion, int monto) {
        this.Categoria = Categoria;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    
    //actualizar
    public Ingreso(int id, String Categoria, String descripcion, int monto) {
        this.id = id;
        this.Categoria = Categoria;
        this.descripcion = descripcion;
        this.monto = monto;
    }
    
    
    
    
    
    
    
    //getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
    
    
    
}
