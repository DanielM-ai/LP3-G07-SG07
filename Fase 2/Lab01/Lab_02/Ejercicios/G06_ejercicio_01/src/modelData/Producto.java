package modelData;

public class Producto {
    private int id;
    private String nombre;
    private double precio;

    // Constructor
    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + nombre + " ($" + String.format("%.2f", precio) + ")";
    }
}