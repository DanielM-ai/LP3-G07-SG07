package Actividad2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena;

        // Se usa try-with-resources para asegurar que el archivo se cierre automáticamente
        try (PrintWriter salida = new PrintWriter("datos.txt")) {
            System.out.println("Introduce texto. Para acabar introduce la cadena FIN:");
            cadena = sc.nextLine();
            while (!cadena.equalsIgnoreCase("FIN")) {
                salida.println(cadena);
                cadena = sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
        System.out.println("Datos guardados en datos.txt");
    }
}