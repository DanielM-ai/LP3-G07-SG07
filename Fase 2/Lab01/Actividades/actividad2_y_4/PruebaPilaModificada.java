package actividad2_y_4;

public class PruebaPilaModificada {

    public static void main(String[] args) {
        // --- Prueba de Actividad 2: contains ---
        System.out.println("--- Probando el método contains ---");
        Pila<Integer> pilaInt = new Pila<>(5);
        pilaInt.push(10);
        pilaInt.push(20);
        pilaInt.push(30);

        System.out.println("¿La pila contiene el número 20? " + pilaInt.contains(20)); // Debería ser true
        System.out.println("¿La pila contiene el número 50? " + pilaInt.contains(50)); // Debería ser false
        
        // --- Prueba de Actividad 4: esIgual ---
        System.out.println("\n--- Probando el método esIgual ---");
        Pila<String> pilaStr1 = new Pila<>(5);
        pilaStr1.push("Hola");
        pilaStr1.push("Mundo");

        Pila<String> pilaStr2 = new Pila<>(5);
        pilaStr2.push("Hola");
        pilaStr2.push("Mundo");

        Pila<String> pilaStr3 = new Pila<>(5);
        pilaStr3.push("Hola");
        pilaStr3.push("Java");
        
        Pila<String> pilaStr4 = new Pila<>(3); // Tamaño diferente
        pilaStr4.push("Hola");
        pilaStr4.push("Mundo");


        System.out.println("¿pilaStr1 es igual a pilaStr2? " + pilaStr1.esIgual(pilaStr2)); // Debería ser true
        System.out.println("¿pilaStr1 es igual a pilaStr3? " + pilaStr1.esIgual(pilaStr3)); // Debería ser false
        System.out.println("¿pilaStr1 es igual a pilaStr4? " + pilaStr1.esIgual(pilaStr4)); // Debería ser false, aunque tengan los mismos elementos, su 'superior' es igual.

    }
}