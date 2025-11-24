package Ejercicio_01;

import javax.swing.SwingUtilities;  // ¡ESTA LÍNEA ES LA QUE FALTABA!

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Producto modelo = new Producto();
            VistaProducto vista = new VistaProducto();
            new ControladorProducto(modelo, vista);

            vista.setVisible(true);
        });
    }
}