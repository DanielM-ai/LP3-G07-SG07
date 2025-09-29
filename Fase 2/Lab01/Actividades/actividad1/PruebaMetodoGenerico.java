package actividad1;

public class PruebaMetodoGenerico {

    // --- MÉTODO GENÉRICO ORIGINAL ---
    // Imprime todos los elementos de un arreglo
    public static <E> void imprimirArreglo(E[] arregloEntrada) {
        for (E elemento : arregloEntrada) {
            System.out.printf("%s ", elemento);
        }
        System.out.println();
    }

    // --- MÉTODO GENÉRICO SOBRECARGADO ---
    // Imprime una parte del arreglo y devuelve la cantidad de elementos impresos.
    public static <E> int imprimirArreglo(E[] arregloEntrada, int subindiceInferior, int subindiceSuperior)
            throws InvalidSubscriptException {
        // Validar los índices
        if (subindiceInferior < 0 || subindiceSuperior >= arregloEntrada.length || subindiceSuperior <= subindiceInferior) {
            throw new InvalidSubscriptException(
                    "Los índices están fuera de rango o son inválidos."
            );
        }

        int contador = 0;
        // Imprimir los elementos dentro del rango
        System.out.print("Elementos del arreglo en el rango especificado: ");
        for (int i = subindiceInferior; i <= subindiceSuperior; i++) {
            System.out.printf("%s ", arregloEntrada[i]);
            contador++;
        }
        System.out.println();
        return contador;
    }

    public static void main(String[] args) {
        // Crear arreglos de prueba
        Integer[] arregloInteger = { 1, 2, 3, 4, 5, 6 };
        Double[] arregloDouble = { 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7 };
        Character[] arregloCharacter = { 'H', 'O', 'L', 'A' };

        // --- Prueba del método original ---
        System.out.println("--- Usando la versión original de imprimirArreglo ---");
        System.out.print("El arreglo arregloInteger contiene: ");
        imprimirArreglo(arregloInteger);
        System.out.print("\nEl arreglo arregloDouble contiene: ");
        imprimirArreglo(arregloDouble);
        System.out.print("\nEl arreglo arregloCharacter contiene: ");
        imprimirArreglo(arregloCharacter);

        System.out.println("\n\n--- Usando la versión sobrecargada de imprimirArreglo ---");

        // --- Prueba del método sobrecargado ---
        try {
            // Caso válido
            int cantidad = imprimirArreglo(arregloInteger, 2, 4);
            System.out.println("Se imprimieron " + cantidad + " elementos.");

            // Caso con índice superior fuera de rango
            System.out.println("\nIntentando imprimir con índice superior inválido...");
            imprimirArreglo(arregloDouble, 0, 10);

        } catch (InvalidSubscriptException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            // Caso con subindiceSuperior <= subindiceInferior
             System.out.println("\nIntentando imprimir con rango incorrecto...");
             imprimirArreglo(arregloCharacter, 3, 1);
        } catch (InvalidSubscriptException e) {
             System.err.println("Error: " + e.getMessage());
        }
    }
}