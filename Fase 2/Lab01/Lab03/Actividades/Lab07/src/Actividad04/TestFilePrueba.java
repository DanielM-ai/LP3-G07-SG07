package Actividad04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

public class TestFilePrueba {
    public static void main(String[] args) {
        // Asegúrate de crear este archivo para que el programa funcione
        String nombreArchivo = "TestFile.java";

        try (FileInputStream file = new FileInputStream(nombreArchivo)) {
            byte b[] = new byte[file.available()]; // Lee todos los bytes del archivo
            file.read(b);
            String s = new String(b);
            ViewFile view = new ViewFile(s);
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.setSize(600, 400);
            view.setVisible(true);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
            System.out.println("Por favor, crea un archivo llamado '" + nombreArchivo + "' en la carpeta del proyecto.");
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
    }
}