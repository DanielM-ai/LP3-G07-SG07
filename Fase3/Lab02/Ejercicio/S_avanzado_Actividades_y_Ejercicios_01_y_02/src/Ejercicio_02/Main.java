package Ejercicio_02;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperaturaSemana modelo = new TemperaturaSemana();
            VistaTemperatura vista = new VistaTemperatura();
            new ControladorTemperatura(modelo, vista);

            vista.setVisible(true);
        });
    }
}