package Actividad07;

import java.io.IOException;
import javax.swing.JFrame;

public class PruebaJFileChooser {
    public static void main(String[] args) throws IOException {
        DemoJFileChooser aplicacion = new DemoJFileChooser();
        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplicacion.setSize(500, 500);
        aplicacion.setVisible(true);
    }
}