package app;

import dao.EmpleadoDAO;
import logica.GestorEmpleados;
import modelo.Empleado;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmpleadoDAO dao = new EmpleadoDAO();
        GestorEmpleados gestor = new GestorEmpleados(); // Nuevo: Instanciamos el gestor
        
        dao.crearTabla(); 
        
        int opcion = 0;
        
        do {
            mostrarMenu();
            
            try {
                System.out.print("Ingrese una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        insertarEmpleado(scanner, dao);
                        break;
                    case 2:
                        // Opción 2 del Ejercicio 1 (Mostrar todos), ahora usa el DAO para lista
                        mostrarTodosSimples(dao); 
                        break;
                    case 3:
                        actualizarEmpleado(scanner, dao);
                        break;
                    case 4:
                        borrarEmpleado(scanner, dao);
                        break;
                    case 5:
                        // Opción NUEVA: Consulta avanzada
                        consultarAvanzada(scanner, dao, gestor);
                        break; 
                    case 6:
                        System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
                
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Debe ingresar un número para la opción del menú.");
                scanner.nextLine();
                opcion = 0;
            } catch (Exception e) {
                System.err.println("❌ Ocurrió un error inesperado: " + e.getMessage());
            }
            
        } while (opcion != 6); // Cambiado a 6
        
        scanner.close();
    }
    
    // --- LÓGICA DE INTERFAZ Y MENÚ ---
    
    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ CRUD EMPLEADOS (Ejercicio 2) =====");
        System.out.println("1. Ingresar nuevo empleado");
        System.out.println("2. Mostrar TODOS los empleados (Simple)");
        System.out.println("3. Actualizar empleado (Cargo y Sueldo)");
        System.out.println("4. Borrar empleado");
        System.out.println("5. ⭐ Consulta Avanzada (Filtro, Orden, Límite)"); // Opción Nueva
        System.out.println("6. Salir");
        System.out.println("=============================================");
    }
    
    // --- LÓGICA PARA NUEVA OPCIÓN 5 ---
    
    private static void consultarAvanzada(Scanner scanner, EmpleadoDAO dao, GestorEmpleados gestor) {
        System.out.println("\n--- CONSULTA AVANZADA EN ARREGLO DE OBJETOS ---");
        
        // 1. Obtener todos los datos de la BD y guardarlos en el arreglo (List)
        List<Empleado> listaBase = dao.obtenerTodos();
        
        if (listaBase.isEmpty()) {
            System.out.println("🚫 La base de datos está vacía. No se puede realizar la consulta.");
            return;
        }

        // a) Qué campos de la tabla deseo mostrar
        System.out.println("Campos disponibles: ID, NOMBRE, CARGO, SUELDO");
        System.out.print("Ingrese los campos a mostrar (separados por coma, ej: ID,NOMBRE): ");
        String camposMostrar = scanner.nextLine().trim();

        // b) Si algún campo debe cumplir una condición
        System.out.println("\n-- FILTRADO (WHERE) --");
        System.out.print("Campo para filtrar (ej: CARGO, ID, [dejar vacío para sin filtro]): ");
        String campoFiltro = scanner.nextLine().trim();
        
        String valorFiltro = "";
        if (!campoFiltro.isEmpty()) {
            System.out.print("Valor que debe cumplir ese campo: ");
            valorFiltro = scanner.nextLine().trim();
        }

        // c) Ordenamiento
        System.out.println("\n-- ORDENAMIENTO (ORDER BY) --");
        System.out.print("Campo para ordenar (ej: SUELDO, ID, [dejar vacío para sin orden]): ");
        String campoOrdenar = scanner.nextLine().trim();
        
        String orden = "";
        if (!campoOrdenar.isEmpty()) {
            System.out.print("Orden (ASC o DESC, [por defecto ASC]): ");
            orden = scanner.nextLine().trim();
        }
        
        // d) Límite
        System.out.println("\n-- LÍMITE (LIMIT) --");
        int limite = 0;
        try {
            System.out.print("Cantidad máxima de registros a mostrar (0 para sin límite): ");
            limite = scanner.nextInt();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Advertencia: El límite debe ser un número entero. Se usará 0 (sin límite).");
            scanner.nextLine();
        }
        
        // 2. Llamar al Gestor para realizar la consulta en memoria
        List<Empleado> resultados = gestor.consultarArreglo(
            listaBase,
            camposMostrar,
            campoFiltro,
            valorFiltro,
            campoOrdenar,
            orden,
            limite
        );
        
        // 3. Mostrar los resultados usando el método del Gestor
        gestor.mostrarResultados(resultados, camposMostrar);
    }
    
    private static void mostrarTodosSimples(EmpleadoDAO dao) {
        // Obtenemos la lista
        List<Empleado> lista = dao.obtenerTodos();
        System.out.println("\n--- LISTADO COMPLETO (Lectura Simple) ---");
        
        if (lista.isEmpty()) {
            System.out.println("No hay datos registrados en la tabla.");
            return;
        }
        
        // Usamos un bucle for simple para recorrer e imprimir
        for (int i = 0; i < lista.size(); i++) {
            Empleado e = lista.get(i);
            // Usamos el método toString() de la clase Empleado
            System.out.println(e); 
        }
        System.out.println("-----------------------------------------\n");
    }

    // --- Métodos CRUD Existentes (Solo deben llamar al DAO, la lógica es la misma) ---

    private static void insertarEmpleado(Scanner scanner, EmpleadoDAO dao) {
        // (Código de lectura de datos del ejercicio 1, que llama a dao.insertar)
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
            scanner.nextLine();
        }
    }

    private static void actualizarEmpleado(Scanner scanner, EmpleadoDAO dao) {
        // (Código de lectura de datos del ejercicio 1, que llama a dao.actualizar)
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
            scanner.nextLine();
        }
    }

    private static void borrarEmpleado(Scanner scanner, EmpleadoDAO dao) {
        // (Código de lectura de datos del ejercicio 1, que llama a dao.borrar)
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
            scanner.nextLine();
        }
    }
}