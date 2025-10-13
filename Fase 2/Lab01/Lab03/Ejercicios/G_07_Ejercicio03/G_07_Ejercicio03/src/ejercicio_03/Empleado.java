package ejercicio_03;

public class Empleado {
    private int numero;
    private String nombre;
    private double sueldo;

    // Constructor
    public Empleado(int numero, String nombre, double sueldo) {
        this.numero = numero;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    // Getters y Setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }

    // toString para mostrar legible
    @Override
    public String toString() {
        return "Número: " + numero + ", Nombre: " + nombre + ", Sueldo: " + sueldo;
    }

    // Método para formato CSV (usado en guardar/leer)
    public String toCsv() {
        return numero + "," + nombre + "," + sueldo;
    }
}
