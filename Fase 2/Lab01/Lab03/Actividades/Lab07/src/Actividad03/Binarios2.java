package Actividad03;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Binarios2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileOutputStream fos = null;
        DataOutputStream salida = null;
        double[][] matriz;
        int filas, columnas, i, j;

        do {
            System.out.print("Número de filas: ");
            filas = sc.nextInt();
        } while (filas <= 0);
        do {
            System.out.print("Número de columnas: ");
            columnas = sc.nextInt();
        } while (columnas <= 0);

        matriz = new double[filas][columnas]; // Se crea la matriz

        for (i = 0; i < filas; i++) {
            for (j = 0; j < columnas; j++) {
                // Lectura de datos por teclado
                System.out.printf("matriz[%d][%d]: ", i, j);
                matriz[i][j] = sc.nextDouble();
            }
        }

        try {
            // Crear el fichero de salida
            fos = new FileOutputStream("matriz.dat");
            salida = new DataOutputStream(fos);

            // Escribir el número de filas y columnas en el fichero
            salida.writeInt(filas);
            salida.writeInt(columnas);

            // Escribir la matriz en el fichero
            for (i = 0; i < filas; i++) {
                for (j = 0; j < columnas; j++) {
                    salida.writeDouble(matriz[i][j]);
                }
            }
            System.out.println("Matriz guardada en matriz.dat");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
}