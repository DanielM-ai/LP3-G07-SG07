package Ejercicio_02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorTemperatura implements ActionListener {
    private TemperaturaSemana modelo;
    private VistaTemperatura vista;

    public ControladorTemperatura(TemperaturaSemana modelo, VistaTemperatura vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.addMostrarListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("MOSTRAR".equals(e.getActionCommand())) {
            // Leer todas las temperaturas
            for (int i = 0; i < 7; i++) {
                modelo.setTemperatura(i, vista.getTemperatura(i));
            }
            // Actualizar el gráfico
            vista.dibujarGrafico(modelo);
        }
    }
}