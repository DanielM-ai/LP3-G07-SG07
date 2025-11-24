package Ejercicio_01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaProducto extends JFrame {
    private JTextField txtNombre = new JTextField("Laptop Gamer", 20);
    private JTextField txtPrecio = new JTextField("4500.00", 20);
    private JTextField txtStock = new JTextField("15", 20);
    private JTextField txtCategoria = new JTextField("Electrónica", 20);
    private JLabel lblInfo = new JLabel(
        "<html><div style='text-align:center;'>Aquí aparecerá la información del producto</div></html>",
        SwingConstants.CENTER
    );
    private JButton btnActualizar = new JButton("Actualizar Producto");

    public VistaProducto() {
        setTitle("Gestión de Producto - MVC Simple");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Precio (S/):"));
        panelFormulario.add(txtPrecio);
        panelFormulario.add(new JLabel("Stock:"));
        panelFormulario.add(txtStock);
        panelFormulario.add(new JLabel("Categoría:"));
        panelFormulario.add(txtCategoria);
        panelFormulario.add(new JLabel(""));
        panelFormulario.add(btnActualizar);

        // Área de resultado
        lblInfo.setBorder(BorderFactory.createTitledBorder("Información Actual del Producto"));
        lblInfo.setPreferredSize(new Dimension(0, 150));

        add(panelFormulario, BorderLayout.CENTER);
        add(lblInfo, BorderLayout.SOUTH);

        btnActualizar.setActionCommand("ACTUALIZAR");
    }

    // Métodos para leer los campos
    public String getNombre() { return txtNombre.getText().trim(); }
    public double getPrecio() {
        try { return Double.parseDouble(txtPrecio.getText().trim()); }
        catch (Exception e) { return 0.0; }
    }
    public int getStock() {
        try { return Integer.parseInt(txtStock.getText().trim()); }
        catch (Exception e) { return 0; }
    }
    public String getCategoria() { return txtCategoria.getText().trim(); }

    // Mostrar información del producto
    public void mostrarInfo(String texto) {
        lblInfo.setText("<html><pre>" + texto.replace("\n", "<br>") + "</pre></html>");
    }

    // Para conectar el botón con el controlador
    public void addActualizarListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }
}