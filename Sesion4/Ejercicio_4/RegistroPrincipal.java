package registro_estudiantes;

import java.util.NoSuchElementException;

/**
 * Clase principal para probar el sistema de Registro de Estudiantes.
 * Contiene el método main que ejecuta todas las pruebas requeridas.
 */
public class RegistroPrincipal {

    public static void main(String[] args) {
        RegistroEstudiantes registro = new RegistroEstudiantes();

        System.out.println("--- PRUEBAS DEL REGISTRO DE ESTUDIANTES ---\n");

        // Prueba 1: Agregar estudiantes válidos
        try {
            registro.agregarEstudiante(new Estudiante("Maria Lopez", "E001"));
            registro.agregarEstudiante(new Estudiante("Carlos Ruiz", "E002"));
        } catch (IllegalArgumentException e) {
            System.out.println("Error inesperado al agregar: " + e.getMessage());
        }

        registro.mostrarEstudiantes();

        // Prueba 2: Intentar crear un estudiante con nombre nulo
        try {
            System.out.println("\nIntentando crear estudiante con nombre nulo...");
            registro.agregarEstudiante(new Estudiante(null, "E003"));
        } catch (IllegalArgumentException e) {
            System.out.println("ÉXITO EN LA PRUEBA - Error capturado: " + e.getMessage());
        }

        // Prueba 3: Buscar un estudiante que sí existe
        try {
            System.out.println("\nBuscando a 'Maria Lopez'...");
            Estudiante encontrada = registro.buscarEstudiante("Maria Lopez");
            System.out.println("ÉXITO EN LA PRUEBA - Estudiante encontrada: " + encontrada);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println("FALLO EN LA PRUEBA - Error en la búsqueda: " + e.getMessage());
        }
        
        // Prueba 4: Buscar un estudiante que NO existe
        try {
            System.out.println("\nBuscando a 'Juan Perez' (no existe)...");
            registro.buscarEstudiante("Juan Perez");
        } catch (NoSuchElementException e) {
            System.out.println("ÉXITO EN LA PRUEBA - Error capturado: " + e.getMessage());
        }

        // Prueba 5: Buscar con un nombre vacío para provocar IllegalArgumentException
        try {
            System.out.println("\nBuscando con un nombre vacío...");
            registro.buscarEstudiante("  ");
        } catch (IllegalArgumentException e) {
            System.out.println("ÉXITO EN LA PRUEBA - Error capturado: " + e.getMessage());
        }

        System.out.println("\n--- FIN DE LAS PRUEBAS ---");
    }
}

