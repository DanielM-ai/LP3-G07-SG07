package Actividad_03;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class AppGraficos2D extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Convertimos Graphics a Graphics2D para aplicar transformaciones
        Graphics2D g2d = (Graphics2D) g;

        // Activamos el antialiasing para suavizar los bordes
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Definimos el color y dibujamos un rectángulo original en gris
        g2d.setColor(Color.GRAY);
        int rectWidth = 100;
        int rectHeight = 50;
        g2d.fillRect(50, 50, rectWidth, rectHeight);

        // Guardamos el estado original de la transformación
        AffineTransform originalTransform = g2d.getTransform();

        // Traslación (Rectángulo Azul)
        g2d.translate(200, 0); // Traslada 200 píxeles a la derecha
        g2d.setColor(Color.BLUE);
        g2d.fillRect(50, 50, rectWidth, rectHeight);

        // Restauramos la transformación original
        g2d.setTransform(originalTransform);

        // Rotación (Rectángulo Verde)
        g2d.translate(0, 150); // Movemos hacia abajo para evitar superposición
        // Rota 45 grados (Math.toRadians(45)) alrededor del centro del rectángulo (100, 75)
        g2d.rotate(Math.toRadians(45), 100, 75); 
        g2d.setColor(Color.GREEN);
        g2d.fillRect(50, 50, rectWidth, rectHeight);

        // Restauramos la transformación original
        g2d.setTransform(originalTransform);

        // Escalado (Rectángulo Naranja)
        g2d.translate(200, 150); // Movemos hacia la derecha y abajo
        g2d.scale(1.5, 0.5); // Escala 1.5x en X y 0.5x en Y
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(50, 50, rectWidth, rectHeight);

        // Restauramos la transformación original
        g2d.setTransform(originalTransform);

        // Sesgado (Shear) (Rectángulo Magenta)
        g2d.translate(0, 300); // Movemos hacia abajo para evitar superposición
        g2d.shear(0.5, 0); // Aplica un sesgado horizontal
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect(50, 50, rectWidth, rectHeight);

        // Restauramos la transformación original al final para no afectar futuras operaciones
        g2d.setTransform(originalTransform);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mancilla-Márquez-Ejemplo de Transformaciones con Graphics2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Añadimos el panel de dibujo al frame
        frame.add(new AppGraficos2D());
        frame.setVisible(true);
    }
}