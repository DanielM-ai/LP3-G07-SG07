package item;

public class Item {
    private String nombre;
    private int cantidad;
    private String descripcion;
    
    public Item(String nombre, int cantidad, String descripcion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public String getTipo() { return "Genérico"; }
    public String getDescripcion() { return descripcion; }
    
    // Método UsarItem()
    public String UsarItem() {
        if (this.cantidad > 0) {
            this.cantidad--;
            return "Usaste 1 " + this.nombre + ". Cantidad restante: " + this.cantidad;
        } else {
            return "No quedan " + this.nombre + "s para usar.";
        }
    }
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Cantidad: " + cantidad + " | Tipo: " + getTipo();
    }
}