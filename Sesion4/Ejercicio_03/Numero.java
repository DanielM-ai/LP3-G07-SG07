package Ejercicio_03;

public class Numero {

    private double valor;

    /**
     * Establece el valor del atributo.
     *
     * @param nuevoValor El valor que se desea establecer.
     * @throws IllegalArgumentException Si el valor proporcionado es negativo.
     */
    public void setValor(double nuevoValor) {
        if (nuevoValor < 0) {
            throw new IllegalArgumentException("Error: El valor no puede ser negativo.");
        }
        this.valor = nuevoValor;
    }

    /**
     * Obtiene el valor actual del atributo.
     *
     * @return El valor actual.
     */
    public double getValor() {
        return this.valor;
    }

    /**
     * Método principal para demostrar la funcionalidad de la clase y el manejo de excepciones.
     */
    public static void main(String[] args) {
        Numero miNumero = new Numero();

        // --- Escenario 1: Valor válido ---
        try {
            System.out.println("Intentando establecer el valor en 50.0...");
            miNumero.setValor(50.0);
            System.out.println("Valor establecido correctamente: " + miNumero.getValor());
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        System.out.println("\n----------------------------------\n");

        // --- Escenario 2: Valor negativo (se lanza y se captura la excepción) ---
        try {
            System.out.println("Intentando establecer el valor en -10.0...");
            miNumero.setValor(-10.0);
            System.out.println("Valor establecido: " + miNumero.getValor()); // Esta línea no se ejecuta
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
    }
}
