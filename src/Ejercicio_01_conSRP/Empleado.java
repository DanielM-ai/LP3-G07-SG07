package Ejercicio_01_conSRP;

// Clase abstracta porque luego podríamos heredar de ella diferentes tipos de empleados
public abstract class Empleado {
    private String nombre;
    private String departamento;
    private int horasTrabajadas;
    private double pagoPorHora;

    public Empleado(String nombre, String departamento, int horasTrabajadas, double pagoPorHora) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.horasTrabajadas = horasTrabajadas;
        this.pagoPorHora = pagoPorHora;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }
    public String getDepartamento() {
        return departamento;
    }
    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }
    public double getPagoPorHora() {
        return pagoPorHora;
    }
}