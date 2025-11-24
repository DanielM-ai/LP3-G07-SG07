package Ejercicio_02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaTemperatura extends JFrame {
    private JTextField[] campos = new JTextField[7];
    private GraficoLineas panelGrafico;
    private JButton btnMostrar;

    private final String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    public VistaTemperatura() {
        setTitle("Temperatura Semanal - Soporta Temperaturas Negativas (-20 a +50 °C)");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // Panel de entrada de temperaturas
        JPanel panelEntrada = new JPanel(new GridLayout(8, 2, 10, 10));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        for (int i = 0; i < 7; i++) {
            panelEntrada.add(new JLabel(dias[i] + ":"));
            campos[i] = new JTextField("25.0", 10);
            campos[i].setHorizontalAlignment(JTextField.CENTER);
            panelEntrada.add(campos[i]);
        }

        btnMostrar = new JButton("Mostrar Gráfico");
        btnMostrar.setFont(new Font("Arial", Font.BOLD, 18));
        btnMostrar.setBackground(new Color(0, 120, 215));
        btnMostrar.setForeground(Color.WHITE);
        btnMostrar.setActionCommand("MOSTRAR");

        panelEntrada.add(new JLabel(""));
        panelEntrada.add(btnMostrar);

        // Panel del gráfico
        panelGrafico = new GraficoLineas();
        panelGrafico.setBackground(Color.WHITE);
        panelGrafico.setBorder(BorderFactory.createLoweredBevelBorder());

        add(panelEntrada, BorderLayout.NORTH);
        add(panelGrafico, BorderLayout.CENTER);
    }

    public double getTemperatura(int dia) {
        try {
            return Double.parseDouble(campos[dia].getText().trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public void dibujarGrafico(TemperaturaSemana modelo) {
        panelGrafico.actualizarDatos(modelo);
    }

    public void addMostrarListener(ActionListener listener) {
        btnMostrar.addActionListener(listener);
    }
}

// ====================================================================
// GRÁFICO QUE SOPORTA TEMPERATURAS NEGATIVAS (-20 a +50 °C)
// ====================================================================
class GraficoLineas extends JPanel {
    private TemperaturaSemana datos;

    // Rango de temperaturas que queremos mostrar
    private static final double TEMP_MIN = -20.0;
    private static final double TEMP_MAX = 50.0;
    private static final double RANGO = TEMP_MAX - TEMP_MIN;

    public void actualizarDatos(TemperaturaSemana datos) {
        this.datos = datos;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (datos == null) return;

        int width = getWidth();
        int height = getHeight();
        int left = 80;
        int right = 50;
        int top = 50;
        int bottom = 80;

        int areaAncho = width - left - right;
        int areaAlto = height - top - bottom;

        // Fondo suave
        g.setColor(new Color(245, 245, 255));
        g.fillRect(left, top, areaAncho, areaAlto);

        // Ejes
        g.setColor(Color.BLACK);
        g.drawLine(left, top, left, height - bottom);                    // Eje Y
        g.drawLine(left, height - bottom, width - right, height - bottom); // Eje X

        // Línea del 0°C destacada
        int yCero = height - bottom - (int)(areaAlto * (0 - TEMP_MIN) / RANGO);
        g.setColor(new Color(0, 100, 200));
        g.drawLine(left, yCero, width - right, yCero);
        g.drawString("0°C", left - 60, yCero + 5);

        // Etiquetas en el eje Y (-20 a 50 de 10 en 10)
        g.setColor(Color.DARK_GRAY);
        for (int t = -20; t <= 50; t += 10) {
            int y = height - bottom - (int)(areaAlto * (t - TEMP_MIN) / RANGO);
            g.drawString(t + "°C", left - 65, y + 5);
            g.drawLine(left - 10, y, left, y);
        }

        // Etiquetas de los días
        for (int i = 0; i < 7; i++) {
            int x = left + areaAncho * i / 6;
            g.setColor(Color.BLACK);
            g.drawString(datos.getDia(i), x - 25, height - bottom + 25);
            g.drawLine(x, height - bottom, x, height - bottom + 10);
        }

        // Dibujar línea y puntos
        int[] xPuntos = new int[7];
        int[] yPuntos = new int[7];

        for (int i = 0; i < 7; i++) {
            xPuntos[i] = left + areaAncho * i / 6;
            double temp = datos.getTemperatura(i);
            yPuntos[i] = height - bottom - (int)(areaAlto * (temp - TEMP_MIN) / RANGO);

            // Asegurar que no se salga del área
            if (yPuntos[i] < top) yPuntos[i] = top;
            if (yPuntos[i] > height - bottom) yPuntos[i] = height - bottom;
        }

        // Línea conectando los puntos
        g.setColor(new Color(220, 20, 60));
        ((Graphics2D)g).setStroke(new BasicStroke(4));
        for (int i = 0; i < 6; i++) {
            g.drawLine(xPuntos[i], yPuntos[i], xPuntos[i + 1], yPuntos[i + 1]);
        }

        // Puntos y valores
        for (int i = 0; i < 7; i++) {
            g.setColor(Color.RED);
            g.fillOval(xPuntos[i] - 10, yPuntos[i] - 10, 20, 20);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString(String.format("%.1f°", datos.getTemperatura(i)), xPuntos[i] - 18, yPuntos[i] + 5);
        }

        // Título del gráfico
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Temperatura Diaria de la Semana", width / 2 - 120, 30);
    }
}