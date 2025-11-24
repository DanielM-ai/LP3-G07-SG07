package Ejercicio_01;

public class Producto {
    private String nombre = "Sin nombre";
    private double precio = 0.0;
    private int cantidadStock = 0;
    private String categoria = "General";

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return String.format(
            "Producto: %s\nPrecio: S/ %.2f\nStock: %d unidades\nCategoría: %s",
            nombre, precio, cantidadStock, categoria
        );
    }
}