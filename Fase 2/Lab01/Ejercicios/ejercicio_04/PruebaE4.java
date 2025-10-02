package ejercicio_04;

import java.util.List;

public class PruebaE4 {

    public static void main(String[] args) {
        
        System.out.println("--- EJERCICIO 4: CLASE GENÉRICA CONTENEDOR ---");
        
        // --- 1. Contenedor de String, Integer (ej. Inventario) ---
        Contenedor<String, Integer> inventario = new Contenedor<>();
        System.out.println("\n-> Llenando Contenedor de Inventario (String, Integer):");
        
        inventario.agregarPar("Laptop", 1200);
        inventario.agregarPar("Mouse", 25);
        inventario.agregarPar("Teclado", 75);
        
        inventario.mostrarPares();
        
        // Acceder y obtener un elemento con seguridad de tipo
        Par<String, Integer> parObtenido = inventario.obtenerPar(0);
        System.out.println("Obteniendo Par[0]: " + parObtenido.getPrimero() + " - " + parObtenido.getSegundo());

        
        // --- 2. Contenedor de Persona, Double (ej. Empleados) ---
        Contenedor<Persona, Double> empleados = new Contenedor<>();
        System.out.println("\n-> Llenando Contenedor de Empleados (Persona, Double):");
        
        Persona p1 = new Persona("Alicia", 30);
        Persona p2 = new Persona("Benito", 45);
        
        empleados.agregarPar(p1, 65000.50); // Persona, Salario
        empleados.agregarPar(p2, 72000.75); // Persona, Salario
        
        empleados.mostrarPares();
        
        // Obtener la lista completa
        List<Par<Persona, Double>> listaEmpleados = empleados.obtenerTodosLosPares();
        System.out.println("Lista total obtenida. Tamaño: " + listaEmpleados.size());
    }
}