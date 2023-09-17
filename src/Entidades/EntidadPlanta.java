package Entidades;

// Entidad persona hereda de entidad productos
public class EntidadPlanta extends EntidadProductos {
    
    // Atributos
    public EntidadPlanta(int categoria) {
        this.categoria = categoria;
    }
    private int categoria;
    
    // Constructores
    public EntidadPlanta() {
        super(); // Llama al constructor vacío de la clase base (EntidadProductos)
        categoria = 0;
    }

    public EntidadPlanta(int id, String nombre, double precio, String descripcion, int cantidadExistente, int tipo, int categoria) {
        super(id, nombre, precio, descripcion, cantidadExistente, tipo); // Llama al constructor lleno de la clase base (EntidadProductos)
        this.categoria = categoria;
    }
    
   
    
    // Propiedades
    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    
    
}
