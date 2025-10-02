package ejercicio_02;

public class PruebaE2 {

    public static void main(String[] args) {
        
        System.out.println("--- EJERCICIO 2: MÉTODO esIgual ---");
        
        // --- 1. Prueba con tipos estándar (String e Integer) ---
        Par<Integer, String> parA1 = new Par<>(50, "Rojo");
        Par<Integer, String> parA2 = new Par<>(50, "Rojo");
        Par<Integer, String> parA3 = new Par<>(10, "Azul");
        
        System.out.println("\n--- Prueba Tipos Estándar ---");
        System.out.println("A1: " + parA1);
        System.out.println("A2: " + parA2);
        System.out.println("A3: " + parA3);
        
        // parA1 y parA2 son iguales en contenido
        System.out.println("A1.esIgual(A2): " + parA1.esIgual(parA2)); // Esperado: true
        // parA1 y parA3 son diferentes
        System.out.println("A1.esIgual(A3): " + parA1.esIgual(parA3)); // Esperado: false
        
        
        // --- 2. Prueba con objetos complejos (Persona) ---
        Persona p1 = new Persona("Laura", 35);
        Persona p2 = new Persona("Laura", 35); // Mismo contenido
        Persona p3 = new Persona("Marcos", 40);
        
        Par<Persona, Boolean> parB1 = new Par<>(p1, true);
        Par<Persona, Boolean> parB2 = new Par<>(p2, true);
        Par<Persona, Boolean> parB3 = new Par<>(p3, false);

        System.out.println("\n--- Prueba Objetos (Persona) ---");
        // parB1 y parB2 son iguales gracias al equals() sobrescrito en Persona
        System.out.println("B1.esIgual(B2): " + parB1.esIgual(parB2)); // Esperado: true
        // Diferentes
        System.out.println("B1.esIgual(B3): " + parB1.esIgual(parB3)); // Esperado: false
    }
}