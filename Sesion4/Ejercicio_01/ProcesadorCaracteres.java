package Ejercicio_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ProcesadorCaracteres {

    private final LeerEntrada lector;

    /**
     * Constructor para inicializar el lector de entrada.
     */
    public ProcesadorCaracteres() {
        this.lector = new LeerEntrada(System.in);
    }

    /**
     * Procesa un carácter y lanza la excepción correspondiente.
     *
     * @param caracter el carácter a procesar.
     * @throws ExcepcionVocal si el carácter es una vocal.
     * @throws ExcepcionNumero si el carácter es un número.
     * @throws ExcepcionBlanco si el carácter es un espacio en blanco.
     * @throws ExcepcionSalida si el carácter es 'x'.
     */
    public void procesar(char caracter) throws ExcepcionVocal, ExcepcionNumero, ExcepcionBlanco, ExcepcionSalida {
        if (esVocal(caracter)) {
            throw new ExcepcionVocal("Error: Se ha introducido una vocal.");
        } else if (esNumero(caracter)) {
            throw new ExcepcionNumero("Error: Se ha introducido un número.");
        } else if (esBlanco(caracter)) {
            throw new ExcepcionBlanco("Error: Se ha introducido un espacio en blanco.");
        } else if (esSalida(caracter)) {
            throw new ExcepcionSalida("Saliendo del programa. Se ha introducido el carácter de salida.");
        }
    }

    /**
     * Método principal que gestiona la lectura de caracteres y el manejo de excepciones.
     *
     * @param args argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        ProcesadorCaracteres procesador = new ProcesadorCaracteres();
        System.out.println("Introduce caracteres. 'x' para salir.");

        while (true) {
            try {
                System.out.print("Introduce un caracter: ");
                char caracter = procesador.lector.getChar();
                procesador.procesar(caracter);
                System.out.println("Caracter procesado: '" + caracter + "'");

            } catch (ExcepcionVocal | ExcepcionNumero | ExcepcionBlanco e) {
                // Maneja las excepciones para las que el programa debe continuar.
                System.out.println(e.getMessage());
            } catch (ExcepcionSalida e) {
                // Maneja la excepción de salida y termina el programa.
                System.out.println(e.getMessage());
                break;
            } catch (IOException e) {
                // Maneja la excepción de entrada/salida.
                System.out.println("Error de lectura: " + e.getMessage());
                break;
            }
        }

        System.out.println("Programa finalizado.");
    }

    // Métodos auxiliares para la lógica de validación
    private static boolean esVocal(char c) {
        char caracterMinuscula = Character.toLowerCase(c);
        return "aeiou".indexOf(caracterMinuscula) != -1;
    }

    private static boolean esNumero(char c) {
        return Character.isDigit(c);
    }

    private static boolean esBlanco(char c) {
        return Character.isWhitespace(c);
    }

    private static boolean esSalida(char c) {
        return c == 'x' || c == 'X';
    }
}