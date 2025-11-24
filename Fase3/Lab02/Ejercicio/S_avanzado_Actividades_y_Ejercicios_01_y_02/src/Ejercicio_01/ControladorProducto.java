package Ejercicio_01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorProducto implements ActionListener {
    private Producto modelo;
    private VistaProducto vista;

    public ControladorProducto(Producto modelo, VistaProducto vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.addActualizarListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("ACTUALIZAR".equals(e.getActionCommand())) {
            // Actualizar modelo con los datos de la vista
            modelo.setNombre(vista.getNombre());
            modelo.setPrecio(vista.getPrecio());
            modelo.setCantidadStock(vista.getStock());
            modelo.setCategoria(vista.getCategoria());

            // Actualizar vista con el modelo
            vista.mostrarInfo(modelo.toString());
        }
    }
}