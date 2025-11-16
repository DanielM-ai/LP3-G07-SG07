package ejercicio_01_botones;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Image; // IMPORTACIÓN AÑADIDA PARA MANIPULACIÓN DE IMAGENES

public class MarcoBoton extends JFrame
{
    private final JButton botonJButtonSimple; // botón con texto solamente
    private final JButton botonJButtonElegante; // botón con iconos

    // MarcoBoton agrega objetos JButton a JFrame
    public MarcoBoton()
    {
        super("Mansilla-Márquez: Prueba de botones");
        setLayout(new FlowLayout());

        // Configuración del botón simple
        botonJButtonSimple = new JButton("Boton simple"); // botón con texto
        add(botonJButtonSimple); // agrega botonJButtonSimple a JFrame

        // --- INICIO DE LA LÓGICA DE REDIMENSIONAMIENTO ---

        // 1. Cargar las imágenes grandes y obtener el objeto Image
        Image imagen1 = new ImageIcon(getClass().getResource("insecto1.gif")).getImage();
        Image imagen2 = new ImageIcon(getClass().getResource("insecto2.gif")).getImage();

        // 2. Redimensionar las imágenes a 16x16 píxeles con suavizado
        Image imagenRedimensionada1 = imagen1.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        Image imagenRedimensionada2 = imagen2.getScaledInstance(32, 32, Image.SCALE_SMOOTH);

        // 3. Convertir las imágenes redimensionadas de nuevo a ImageIcon (Icon)
        Icon insecto1 = new ImageIcon(imagenRedimensionada1);
        Icon insecto2 = new ImageIcon(imagenRedimensionada2);

        // --- FIN DE LA LÓGICA DE REDIMENSIONAMIENTO ---

        // Creación del botón elegante con el icono redimensionado
        botonJButtonElegante = new JButton("Boton elegante", insecto1); // establece la imagen

        botonJButtonElegante.setRolloverIcon(insecto2); // establece la imagen de sustitución
        add(botonJButtonElegante); // agrega botonJButtonElegante a JFrame

        // crea nuevo ManejadorBoton para manejar los eventos de botón
        ManejadorBoton manejador = new ManejadorBoton();
        botonJButtonElegante.addActionListener(manejador);
        botonJButtonSimple.addActionListener(manejador);
    }

    // clase interna para manejar eventos de botón
    private class ManejadorBoton implements ActionListener
    {
        // maneja evento de botón
        @Override
        public void actionPerformed(ActionEvent evento)
        {
            JOptionPane.showMessageDialog(MarcoBoton.this, String.format(
                    "Usted oprimio: %s", evento.getActionCommand()));
        }
    }
} // fin de la clase MarcoBoton