package ejercicio_03;

import java.util.List;
import java.util.Scanner;

public class VistaEmpleado {
    private Scanner scanner;

    public VistaEmpleado() {
        this.scanner = new Scanner(System.in);
    }

    // Mostrar menú principal
    public void mostrarMenu() {
        System.out.println("\n=== GESTOR DE EMPLEADOS ===");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar un nuevo empleado");
        System.out.println("3. Buscar un empleado por su número");
        System.out.println("4. Eliminar un empleado por su número");
        System.out.println("5. Salir del programa");
        System.out.print("Elija una opción: ");
    }

    // Pedir datos para nuevo empleado
    public int pedirNumero() {
        System.out.print("Ingrese el número del empleado (>0): ");
        int num = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer
        return num;
    }

    public String pedirNombre() {
        System.out.print("Ingrese el nombre del empleado: ");
        return scanner.nextLine().trim();
    }

    public double pedirSueldo() {
        System.out.print("Ingrese el sueldo del empleado (>0): ");
        return scanner.nextDouble();
    }

    // Mostrar lista de empleados
    public void mostrarEmpleados(List<Empleado> empleados) {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }

    // Pedir número para buscar/eliminar
    public int pedirNumeroBusqueda() {
        System.out.print("Ingrese el número del empleado a buscar/eliminar: ");
        return scanner.nextInt();
    }

    // Mensajes de éxito/error
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Obtener opción del menú
    public int obtenerOpcion() {
        return scanner.nextInt();
    }

    // Cerrar scanner al final (opcional, pero buena práctica)
    public void cerrar() {
        scanner.close();
    }
}
