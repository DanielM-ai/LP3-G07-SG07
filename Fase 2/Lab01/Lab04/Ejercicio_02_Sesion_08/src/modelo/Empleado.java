package modelo;

// La clase Empleado representa un registro de la tabla 'empleado'
public class Empleado {
    
    private int id;
    private String nombre;
    private String cargo;
    private double sueldo;

    // Constructor que usamos para crear el objeto desde el DAO
    public Empleado(int id, String nombre, String cargo, double sueldo) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.sueldo = sueldo;
    }

    // --- Métodos Getters (necesarios para acceder a los datos) ---
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSueldo() {
        return sueldo;
    }
    
    // --- Método toString (útil para imprimir el objeto) ---

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Cargo: %s | Sueldo: %.2f", 
                             id, nombre, cargo, sueldo);
    }
}