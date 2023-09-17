package Entidades;

public class EntidadProductos {

    // Atributos
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private int cantidadExistente;
    private int tipo;

    
    // Propiedades
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidadExistente() {
        return cantidadExistente;
    }

    public int getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidadExistente(int cantidadExistente) {
        this.cantidadExistente = cantidadExistente;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    // Constructores
        public EntidadProductos() {
        id = 0;
        nombre = "";
        precio = 0;
        descripcion = "";
        cantidadExistente = 0;
        tipo = 0;
    }

    
     public EntidadProductos(int id, String nombre, double precio, String descripcion, int cantidadExistente, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidadExistente = cantidadExistente;
        this.tipo = tipo;
    }

     
     
    
}
