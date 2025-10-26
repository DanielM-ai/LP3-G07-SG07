package app;

import dao.EmpleadoDAO;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        // Inicializar el Scanner para leer la consola
        Scanner scanner = new Scanner(System.in);
        // Inicializar la clase que maneja las operaciones de la BD
        EmpleadoDAO dao = new EmpleadoDAO();
        
        // 1. Asegurar la creación de la tabla al inicio
        dao.crearTabla(); 
        
        int opcion = 0;
        
        // Bucle principal para el menú
        do {
            mostrarMenu();
            
            try {
                System.out.print("Ingrese una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente
                
                switch (opcion) {
                    case 1:
                        insertarEmpleado(scanner, dao);
                        break;
                    case 2:
                        dao.mostrarTodos();
                        break;
                    case 3:
                        actualizarEmpleado(scanner, dao);
                        break;
                    case 4:
                        borrarEmpleado(scanner, dao);
                        break;
                    case 5:
                        System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
                
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Debe ingresar un número para la opción.");
                scanner.nextLine(); // Limpiar el buffer para evitar bucles infinitos
                opcion = 0; // Para que el bucle continúe
            } catch (Exception e) {
                System.err.println("❌ Ocurrió un error inesperado: " + e.getMessage());
            }
            
        } while (opcion != 5);
        
        // Cerrar el scanner al finalizar
        scanner.close();
    }
    
    // --- Métodos de Interfaz (Separación de lógica) ---
    
    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ CRUD EMPLEADOS =====");
        System.out.println("1. Ingresar nuevo empleado");
        System.out.println("2. Mostrar todos los empleados");
        System.out.println("3. Actualizar empleado (Cargo y Sueldo)");
        System.out.println("4. Borrar empleado");
        System.out.println("5. Salir");
        System.out.println("===============================");
    }

    private static void insertarEmpleado(Scanner scanner, EmpleadoDAO dao) {
        System.out.println("\n--- INGRESAR NUEVO EMPLEADO ---");
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Cargo: ");
            String cargo = scanner.nextLine();
            
            System.out.print("Sueldo: ");
            double sueldo = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print(">>> Ingrese CLAVE para CONFIRMAR: ");
            String clave = scanner.nextLine();
            
            dao.insertar(id, nombre, cargo, sueldo, clave);
            
        } catch (InputMismatchException e) {
            System.out.println("❌ Error de formato: Asegúrese de que ID y Sueldo sean números válidos.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    private static void actualizarEmpleado(Scanner scanner, EmpleadoDAO dao) {
        System.out.println("\n--- ACTUALIZAR EMPLEADO ---");
        try {
            System.out.print("ID del empleado a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nuevo Cargo: ");
            String cargo = scanner.nextLine();
            
            System.out.print("Nuevo Sueldo: ");
            double sueldo = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print(">>> Ingrese CLAVE para CONFIRMAR: ");
            String clave = scanner.nextLine();
            
            dao.actualizar(id, cargo, sueldo, clave);
            
        } catch (InputMismatchException e) {
            System.out.println("❌ Error de formato: Asegúrese de que ID y Sueldo sean números válidos.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    private static void borrarEmpleado(Scanner scanner, EmpleadoDAO dao) {
        System.out.println("\n--- BORRAR EMPLEADO ---");
        try {
            System.out.print("ID del empleado a borrar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print(">>> Ingrese CLAVE para CONFIRMAR: ");
            String clave = scanner.nextLine();
            
            dao.borrar(id, clave);
            
        } catch (InputMismatchException e) {
            System.out.println("❌ Error de formato: Asegúrese de que el ID sea un número válido.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }
}