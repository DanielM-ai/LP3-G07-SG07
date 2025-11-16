package ejercicio_06_seleccionmultiple;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MarcoSeleccionMultiple extends JFrame
{
    private final JList<String> listaJListColores; // lista para guardar los nombres de los colores
    private final JList<String> listaJListCopia; // lista en la que se van a copiar los nombres de los colores
    private final JButton botonJButtonCopiar; // botón para copiar los nombres seleccionados
    
    private static final String[] nombresColores = {
        "Negro", "Azul", "Cyan", "Gris oscuro", "Gris", "Verde", "Gris claro", 
        "Magenta", "Naranja", "Rosa", "Rojo", "Blanco", "Amarillo"
    };

    // Constructor de MarcoSeleccionMultiple
    public MarcoSeleccionMultiple()
    {
        super("Mansilla-Márquez: Listas de seleccion multiple");
        setLayout(new FlowLayout());

        // --- Lista de Origen (Selección Múltiple) ---
        listaJListColores = new JList<String>(nombresColores); // lista de nombres de colores
        listaJListColores.setVisibleRowCount(5); // muestra cinco filas
        listaJListColores.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // PERMITE SELECCIONAR MÚLTIPLES RANGOS
        add(new JScrollPane(listaJListColores)); // agrega lista con panel de desplazamiento

        // --- Botón Copiar ---
        botonJButtonCopiar = new JButton("Copiar >>>");
        botonJButtonCopiar.addActionListener(
            new ActionListener() // clase interna anónima
            {
                // maneja evento de botón
                @Override
                public void actionPerformed(ActionEvent evento)
                {
                    // Obtiene la lista de elementos seleccionados y la convierte a un arreglo de Strings
                    // y la usa para establecer el contenido de la lista de copia
                    listaJListCopia.setListData(
                        listaJListColores.getSelectedValuesList().toArray(
                            new String[0]));
                }
            }
        );
        add(botonJButtonCopiar); // agrega el botón copiar a JFrame

        // --- Lista de Destino (Solo Copia) ---
        listaJListCopia = new JList<String>(); // lista para guardar nombres de colores copiados
        listaJListCopia.setVisibleRowCount(5); // muestra 5 filas
        listaJListCopia.setFixedCellWidth(100); // establece la anchura
        listaJListCopia.setFixedCellHeight(15); // establece la altura
        listaJListCopia.setSelectionMode(
                ListSelectionModel.SINGLE_INTERVAL_SELECTION); // Permite seleccionar un solo rango (pero aquí solo se usa para visualización)
        add(new JScrollPane(listaJListCopia)); // agrega lista con panel de desplazamiento
    }
} // fin de la clase MarcoSeleccionMultiple