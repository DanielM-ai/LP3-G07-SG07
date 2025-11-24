package Actividad_01;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout; // Importar FlowLayout explícitamente es una buena práctica

public class App {
    public static void main(String[] args) {
        // Inicialización del Marco (Frame)
        JFrame frame = new JFrame("Mancilla-Márquez. Ejemplo de Binding de Datos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);

        // Modelo de datos (Se crea el objeto Persona)
        Persona persona = new Persona("John Doe", 30, "Masculino");

        // Componentes de interfaz
        JTextField nombreField = new JTextField(persona.getNombre(), 15);
        // Nota: Asegúrate de que el String.valueOf(persona.getEdad()) esté bien
        JTextField edadField = new JTextField(String.valueOf(persona.getEdad()), 15); 
        JTextField sexoField = new JTextField(persona.getSexo(), 15);
        JButton button = new JButton("Actualizar Modelo");

        // Listener para actualizar el modelo
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sincronización Manual: Vista -> Modelo (WRITE)
                persona.setNombre(nombreField.getText());
                try {
                    int edad = Integer.parseInt(edadField.getText());
                    persona.setEdad(edad);
                } catch (NumberFormatException ex) {
                    // Manejo de errores para la edad
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número válido para la edad.");
                }
                persona.setSexo(sexoField.getText());

                // Mostrar los valores actualizados en consola para verificación
                System.out.println("Modelo actualizado:");
                System.out.println("Nombre: " + persona.getNombre());
                System.out.println("Edad: " + persona.getEdad());
                System.out.println("Sexo: " + persona.getSexo());
            }
        });

        // Configuración y adición de componentes al marco
        frame.setLayout(new FlowLayout()); // Uso de FlowLayout explícito
        frame.add(new JLabel("Nombre:"));
        frame.add(nombreField);
        frame.add(new JLabel("Edad:"));
        frame.add(edadField);
        frame.add(new JLabel("Sexo:"));
        frame.add(sexoField);
        frame.add(button);

        // Hace visible la ventana
        frame.setVisible(true);
    }
}

// Modelo de datos (Clase Persona)
class Persona {
    private String nombre;
    private int edad;
    private String sexo;

    public Persona(String nombre, int edad, String sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}