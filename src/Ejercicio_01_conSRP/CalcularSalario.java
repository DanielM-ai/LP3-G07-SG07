package Ejercicio_01_conSRP;

// Esta clase solo calcula salario:
public class CalcularSalario {

    public double calcular(Empleado empleado) {
        return empleado.getHorasTrabajadas() * empleado.getPagoPorHora();
    }

}