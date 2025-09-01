package Ejercicio_01_conSRP;

import java.util.Scanner;

// Clase que maneja la ejecución (entrada/salida y flujo del programa)
public class Principal {
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

        // Creamos un empleado anónimo (porque Empleado es abstracta)
        Empleado empleado = new Empleado(nombre, departamento, horasTrabajadas, pagoPorHora) {
            // Clase anónima temporal para este ejemplo
        };

        CalcularSalario calculadora = new CalcularSalario();
        double pagoMensual = calculadora.calcular(empleado);

        System.out.println("Pago mensual de " + empleado.getNombre() + ": " + pagoMensual);

        scanner.close();
    }
}