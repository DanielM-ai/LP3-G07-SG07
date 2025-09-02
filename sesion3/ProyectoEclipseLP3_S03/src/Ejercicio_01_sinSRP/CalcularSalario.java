package Ejercicio_01_sinSRP;

import java.util.Scanner;

public class CalcularSalario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Ingrese la cantidad de horas trabajadas: ");
        int horasTrabajadas = scanner.nextInt();
        System.out.print("Ingrese el monto que se pagará por hora: ");
        double pagoPorHora = scanner.nextDouble();
        Empleado empleado = new Empleado(nombre, departamento, horasTrabajadas, pagoPorHora);
        System.out.println("Pago mensual de " + empleado.getNombre() + ": " + empleado.calcularPagoMensual());
        scanner.close();
    }
}

	class Empleado {
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
    public double calcularPagoMensual() {
        return horasTrabajadas * pagoPorHora;
    }
    // Getters
    public String getNombre() {
        return nombre;
    }
    public String getDepartamento() {
        return departamento;
    }
}



