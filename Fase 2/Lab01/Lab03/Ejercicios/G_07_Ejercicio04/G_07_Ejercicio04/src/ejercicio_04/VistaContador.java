package ejercicio_04;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.FileDialog;
import java.io.File;
import java.util.Scanner;

public class VistaContador {
    private JFileChooser fileChooser;

    public VistaContador() {
        this.fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));
        fileChooser.setDialogTitle("Seleccionar archivo de texto");
    }

    // Seleccionar archivo con JFileChooser (retorna ruta o null si inválido)
    public String seleccionarArchivo() {
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            if (archivo != null && archivo.exists()) {
                return archivo.getAbsolutePath();
            } else {
                mostrarError("Archivo seleccionado no existe o es inválido.");
                return null;
            }
        } else {
            mostrarError("Selección cancelada. El programa terminará.");
            return null;
        }
    }

    // Mostrar resultados principales
    public void mostrarResultados(ContadorPalabras contador) {
        System.out.println("\n=== RESULTADOS DEL ANÁLISIS ===");
        System.out.println("Total de líneas: " + contador.getTotalLineas());
        System.out.println("Total de palabras: " + contador.getTotalPalabras());
        System.out.println("Total de caracteres (sin finales de línea): " + contador.getTotalCaracteres());
        System.out.printf("Promedio de palabras por línea: %.2f%n", contador.getPromedioPalabrasPorLinea());
    }

    // Mostrar mensaje general
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Mostrar error (con reintento implícito en el loop de Main)
    public void mostrarError(String error) {
        System.out.println("ERROR: " + error);
    }
}
