package Actividad04;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ViewFile extends JFrame {
    private JTextArea areaTexto;

    public ViewFile(String s) {
        super("Mostrando el contenido de un archivo");
        areaTexto = new JTextArea(s, 20, 50);
        areaTexto.setEditable(false); // Para que no se pueda editar
        add(new JScrollPane(areaTexto)); // Añade barras de desplazamiento
    }
}