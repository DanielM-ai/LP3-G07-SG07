package ejercicio_02_Guia_09;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MarcoPasaje extends JFrame {

    // --- Componentes para la Capa 2: Declaración ---

    // 1. Etiqueta y Campos de Texto (Nombre, Documento, Fecha)
    private final JLabel etiquetaNombre;
    private final JTextField campoNombre;
    private final JLabel etiquetaDocumento;
    private final JTextField campoDocumento;
    private final JLabel etiquetaFecha;
    private final JTextField campoFecha;

    // 2. Cuadros Combinados (Origen y Destino)
    private final JLabel etiquetaOrigen;
    private final JComboBox<String> comboOrigen;
    private final JLabel etiquetaDestino;
    private final JComboBox<String> comboDestino;
    private static final String[] CIUDADES = {"Arequipa", "Lima", "Cusco", "Puno", "Tacna"};

    // 3. Botones de Opción (Piso)
    private final JRadioButton radioPiso1;
    private final JRadioButton radioPiso2;
    private final ButtonGroup grupoPisos;

    // 4. Lista (Calidad de Servicio)
    private final JLabel etiquetaCalidad;
    private final JList<String> listaCalidad;
    private final JScrollPane scrollCalidad;
    private static final String[] SERVICIOS = {"Económico", "Standard", "VIP"};

    // 5. Casillas de Verificación (Servicios Opcionales)
    private final JLabel etiquetaOpcional;
    private final JCheckBox checkAudifonos;
    private final JCheckBox checkManta;
    private final JCheckBox checkRevistas;

    // 6. Botón de Comando (Acción)
    private final JButton botonComando;


    // --- Constructor: Creación y Organización ---
    public MarcoPasaje() {
        super("Simulación de Compra de Pasajes - Mansilla_Márquez");
        
        // Usamos BorderLayout para el marco principal
        setLayout(new BorderLayout(5, 5)); // 5px de espacio entre regiones

        // ------------------ PANEL NORTE (Datos de Pasajero) ------------------
        JPanel panelPasajero = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 filas, 2 columnas
        panelPasajero.setBorder(BorderFactory.createTitledBorder("Información del Pasajero"));
        
        etiquetaNombre = new JLabel("Nombre Completo:");
        campoNombre = new JTextField(20);
        etiquetaDocumento = new JLabel("Documento de Identidad:");
        campoDocumento = new JTextField(20);
        etiquetaFecha = new JLabel("Fecha de Viaje (DD/MM/AAAA):");
        campoFecha = new JTextField(20);
        
        panelPasajero.add(etiquetaNombre);
        panelPasajero.add(campoNombre);
        panelPasajero.add(etiquetaDocumento);
        panelPasajero.add(campoDocumento);
        panelPasajero.add(etiquetaFecha);
        panelPasajero.add(campoFecha);
        
        add(panelPasajero, BorderLayout.NORTH);


        // ------------------ PANEL CENTRAL (Opciones de Viaje) ------------------
        JPanel panelOpciones = new JPanel(new GridLayout(1, 3, 10, 5));
        
        // A. Origen/Destino (JComboBox)
        JPanel panelUbicacion = new JPanel(new GridLayout(4, 1));
        panelUbicacion.setBorder(BorderFactory.createTitledBorder("Origen y Destino"));
        etiquetaOrigen = new JLabel("Origen:");
        comboOrigen = new JComboBox<>(CIUDADES);
        etiquetaDestino = new JLabel("Destino:");
        comboDestino = new JComboBox<>(CIUDADES);
        
        panelUbicacion.add(etiquetaOrigen);
        panelUbicacion.add(comboOrigen);
        panelUbicacion.add(etiquetaDestino);
        panelUbicacion.add(comboDestino);
        panelOpciones.add(panelUbicacion);


        // B. Pisos (JRadioButton)
        JPanel panelPisos = new JPanel(new GridLayout(3, 1));
        panelPisos.setBorder(BorderFactory.createTitledBorder("Piso del Bus"));
        radioPiso1 = new JRadioButton("1er Piso", true); // Seleccionado por defecto
        radioPiso2 = new JRadioButton("2do Piso", false);
        
        grupoPisos = new ButtonGroup();
        grupoPisos.add(radioPiso1);
        grupoPisos.add(radioPiso2);
        
        panelPisos.add(new JLabel("Seleccionar piso:")); // Etiqueta de encabezado
        panelPisos.add(radioPiso1);
        panelPisos.add(radioPiso2);
        panelOpciones.add(panelPisos);
        
        
        // C. Calidad de Servicio (JList)
        JPanel panelCalidad = new JPanel(new BorderLayout());
        panelCalidad.setBorder(BorderFactory.createTitledBorder("Calidad de Servicio"));
        etiquetaCalidad = new JLabel("Elija una calidad:");
        listaCalidad = new JList<>(SERVICIOS);
        listaCalidad.setVisibleRowCount(3); // Muestra todas las opciones
        listaCalidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Solo una opción
        listaCalidad.setSelectedIndex(0); // Seleccionar Económico por defecto
        scrollCalidad = new JScrollPane(listaCalidad);
        
        panelCalidad.add(etiquetaCalidad, BorderLayout.NORTH);
        panelCalidad.add(scrollCalidad, BorderLayout.CENTER);
        panelOpciones.add(panelCalidad);

        add(panelOpciones, BorderLayout.CENTER);


        // ------------------ PANEL SUR (Opcionales y Botón) ------------------
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Botón y Checkboxes
        
        // Casillas de Verificación (Opcionales)
        JPanel panelChecks = new JPanel(new GridLayout(1, 4));
        etiquetaOpcional = new JLabel("Servicios Opcionales:");
        checkAudifonos = new JCheckBox("Audífonos");
        checkManta = new JCheckBox("Manta");
        checkRevistas = new JCheckBox("Revistas");
        
        panelChecks.add(etiquetaOpcional);
        panelChecks.add(checkAudifonos);
        panelChecks.add(checkManta);
        panelChecks.add(checkRevistas);

        // Botón
        botonComando = new JButton("Reiniciar y Mostrar Resumen"); // El texto del botón incluye la acción de la guía

        // --- CAPA 3: IMPLEMENTACIÓN DEL EVENTO DEL BOTÓN ---
        botonComando.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarResumenYReiniciar();
                }
            }
        );
        // --- FIN CAPA 3 ---

        panelSur.add(panelChecks);
        panelSur.add(botonComando);
        
        add(panelSur, BorderLayout.SOUTH);
    }
    
    // Método que recopila datos, muestra el resumen y blanquea los campos
    private void mostrarResumenYReiniciar() {
        // 1. Recopilar Datos
        String nombre = campoNombre.getText();
        String documento = campoDocumento.getText();
        String fecha = campoFecha.getText();
        
        String origen = (String) comboOrigen.getSelectedItem();
        String destino = (String) comboDestino.getSelectedItem();
        
        String piso = radioPiso1.isSelected() ? "1er Piso" : "2do Piso";
        
        String calidad = listaCalidad.getSelectedValue();
        if (calidad == null) {
            calidad = "No Seleccionada";
        }
        
        // 2. Servicios Opcionales
        StringBuilder opcionales = new StringBuilder();
        if (checkAudifonos.isSelected()) opcionales.append("Audífonos, ");
        if (checkManta.isSelected()) opcionales.append("Manta, ");
        if (checkRevistas.isSelected()) opcionales.append("Revistas, ");
        
        String serviciosOpcionales = opcionales.length() > 0 ? opcionales.substring(0, opcionales.length() - 2) : "Ninguno";
        
        // 3. Crear el Resumen
        String resumen = String.format(
            "--- RESUMEN DE COMPRA DE PASAJE ---\n" +
            "PASAJERO:\n" +
            "  Nombre: %s\n" +
            "  Documento: %s\n" +
            "  Fecha: %s\n\n" +
            "DETALLES DEL VIAJE:\n" +
            "  Ruta: %s -> %s\n" +
            "  Piso: %s\n" +
            "  Calidad: %s\n" +
            "  Opcionales: %s",
            nombre, documento, fecha, origen, destino, piso, calidad, serviciosOpcionales
        );
        
        // 4. Mostrar el Resumen en Cuadro de Diálogo
        JOptionPane.showMessageDialog(this, resumen, "Resumen del Pasaje", JOptionPane.INFORMATION_MESSAGE);
        
        // 5. Reiniciar o Blanquear Componentes (según la guía)
        campoNombre.setText("");
        campoDocumento.setText("");
        campoFecha.setText("");
        
        // Resetear ComboBox (opcional, deja el primero)
        comboOrigen.setSelectedIndex(0);
        comboDestino.setSelectedIndex(0);
        
        // Resetear RadioButtons
        radioPiso1.setSelected(true); // Vuelve al valor por defecto
        
        // Resetear Lista
        listaCalidad.clearSelection();
        
        // Resetear CheckBoxes
        checkAudifonos.setSelected(false);
        checkManta.setSelected(false);
        checkRevistas.setSelected(false);
    }
}