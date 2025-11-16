package ejercicio_05_listas;

import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

public class MarcoLista extends JFrame
{
    private final JList<String> listaJListColores; // lista para mostrar colores
    
    private static final String[] nombresColores = {
        "Negro", "Azul", "Cyan", "Gris oscuro", "Gris", "Verde", "Gris claro", 
        "Magenta", "Naranja", "Rosa", "Rojo", "Blanco", "Amarillo"
    };
    
    private static final Color[] colores = {
        Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, 
        Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, 
        Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
    };

    // El constructor de MarcoLista agrega a JFrame el JScrollPane que contiene a JList
    public MarcoLista()
    {
        super("Mansilla-Márquez: Prueba de JList");
        setLayout(new FlowLayout());

        // La lista se inicializa con los nombres de los colores
        listaJListColores = new JList<String>(nombresColores); 
        listaJListColores.setVisibleRowCount(5); // muestra cinco filas a la vez

        // no permite selecciones múltiples (solo una a la vez)
        listaJListColores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // agrega al marco un objeto JScrollPane que contiene a JList
        // NOTA CLAVE: JScrollPane asegura que la lista tenga barras de desplazamiento
        add(new JScrollPane(listaJListColores));

        listaJListColores.addListSelectionListener(
            new ListSelectionListener() // clase interna anónima
            {
                // maneja los eventos de selección de la lista
                @Override
                public void valueChanged(ListSelectionEvent evento)
                {
                    // Obtiene el panel de contenido de la ventana y establece su color de fondo
                    getContentPane().setBackground(
                        colores[listaJListColores.getSelectedIndex()]);
                }
            }
        );
    }
} // fin de la clase MarcoLista