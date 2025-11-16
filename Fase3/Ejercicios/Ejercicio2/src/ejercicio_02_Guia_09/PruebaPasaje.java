package ejercicio_02_Guia_09;

import javax.swing.JFrame;

public class PruebaPasaje {
    public static void main(String[] args) {
        MarcoPasaje marco = new MarcoPasaje();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.pack(); // Ajusta el tamaño de la ventana al contenido
        marco.setVisible(true);
    }
}
