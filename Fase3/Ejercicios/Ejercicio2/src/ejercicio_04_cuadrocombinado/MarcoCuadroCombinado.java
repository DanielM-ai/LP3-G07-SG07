package ejercicio_04_cuadrocombinado;

import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image; // Importación necesaria

public class MarcoCuadroCombinado extends JFrame
{
    private final JComboBox<String> imagenesJComboBox; // contiene los nombres de los iconos
    private final JLabel etiqueta; // muestra el icono seleccionado
    
    // Nombres de los archivos de imagen
    private static final String nombres[] =
        {"insecto1.gif", "insecto2.gif", "insectviaje.gif", "insectanim.gif"};
    
    // Función auxiliar para crear Iconos redimensionados
    private Icon crearIconoEscalado(String nombreArchivo) {
        // Carga la imagen desde el recurso
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(nombreArchivo));
        // Obtiene el objeto Image de AWT para manipulación
        Image imagen = iconoOriginal.getImage();
        
        // Redimensiona la imagen a 32x32 (tamaño de ejemplo) con suavizado
        Image imagenEscalada = imagen.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        
        // Retorna el nuevo ImageIcon
        return new ImageIcon(imagenEscalada);
    }
    
    // Carga los iconos de las imágenes (usando la función de escalado)
    private final Icon[] iconos = {
        crearIconoEscalado(nombres[0]),
        crearIconoEscalado(nombres[1]),
        crearIconoEscalado(nombres[2]),
        crearIconoEscalado(nombres[3])
    };
    
    // El constructor de MarcoCuadroCombinado agrega un objeto JComboBox a JFrame
    public MarcoCuadroCombinado()
    {
        super("Mansilla-Márquez: Prueba de JComboBox");
        setLayout(new FlowLayout()); // establece el esquema del marco
        
        // El JComboBox se inicializa con el arreglo de Strings 'nombres'
        imagenesJComboBox = new JComboBox<String>(nombres); 
        imagenesJComboBox.setMaximumRowCount(3); // muestra tres filas
        
        // Registra el componente de escucha (Listener)
        imagenesJComboBox.addItemListener(
            new ItemListener() // clase interna anónima
            {
                // maneja evento de JComboBox
                @Override
                public void itemStateChanged(ItemEvent evento)
                {
                    // determina si el elemento está seleccionado (no deseleccionado)
                    if (evento.getStateChange() == ItemEvent.SELECTED)
                        // Establece el ícono de la etiqueta usando el índice seleccionado en JComboBox
                        etiqueta.setIcon(iconos[
                            imagenesJComboBox.getSelectedIndex()]);
                }
            } // fin de la clase interna anónima
        ); // fin de la llamada a addItemListener
        
        add(imagenesJComboBox); // agrega cuadro combinado a JFrame
        
        // La etiqueta (JLabel) se inicializa mostrando el primer icono por defecto
        etiqueta = new JLabel(iconos[0]); 
        add(etiqueta); // agrega etiqueta a JFrame
    }
} // fin de la clase MarcoCuadroCombinado