package Ejercicio_02;

public class Calculadora {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    /**
     * Realiza una división.
     *
     * @param a Dividendo.
     * @param b Divisor.
     * @return El resultado de la división.
     * @throws DivisionPorCeroException Si el divisor es cero.
     * @throws IllegalArgumentException Si alguno de los argumentos es inválido.
     */
    public double dividir(double a, double b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("No se pueden usar números negativos para esta operación.");
        }
        if (b == 0) {
            throw new DivisionPorCeroException("Error: No es posible dividir entre cero.");
        }
        return a / b;
    }

    /**
     * Método principal para demostrar la funcionalidad de la calculadora y el manejo de excepciones.
     */
    public static void main(String[] args) {
        Calculadora miCalculadora = new Calculadora();

        // --- Demo 1: Operaciones exitosas ---
        try {
            System.out.println("Suma: " + miCalculadora.sumar(10, 5));
            System.out.println("Resta: " + miCalculadora.restar(10, 5));
            System.out.println("Multiplicación: " + miCalculadora.multiplicar(10, 5));
            System.out.println("División: " + miCalculadora.dividir(10, 5));
        } catch (IllegalArgumentException e) {
            System.out.println("Error de argumento inválido: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error aritmético: " + e.getMessage());
        }

        System.out.println("\n----------------------------------\n");

        // --- Demo 2: Manejo de DivisionPorCeroException (que es una ArithmeticException) ---
        try {
            System.out.println("Intentando dividir 10 entre 0...");
            miCalculadora.dividir(10, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error de argumento inválido: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Se ha capturado una excepción aritmética: " + e.getMessage());
        }

        System.out.println("\n----------------------------------\n");

        // --- Demo 3: Manejo de IllegalArgumentException ---
        try {
            System.out.println("Intentando dividir un número negativo...");
            miCalculadora.dividir(-5, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha capturado una excepción de argumento inválido: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error aritmético: " + e.getMessage());
        }
    }
}