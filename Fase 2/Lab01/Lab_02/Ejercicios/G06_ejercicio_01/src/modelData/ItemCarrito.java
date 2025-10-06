package modelData;

// Importa Producto del mismo paquete modelData
import modelData.Producto; 

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    // Constructor
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Getters
    public Producto getProducto() {
        return producto;
    }
    public int getCantidad() {
        return cantidad;
    }

    // Setter (necesario para actualizar la cantidad)
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Lógica de Negocio Pura (Model)
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
    
    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad + " = $" + String.format("%.2f", getSubtotal());
    }
}