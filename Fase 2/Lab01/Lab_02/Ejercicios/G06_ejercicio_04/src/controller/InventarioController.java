package controller;

import model.InventarioModel;
import view.InventarioView;
import item.Item;
import item.Arma;
import item.Pocion;

/**
 * Este controlador solo gestiona la lógica CRUD (Crear, Leer, Actualizar, Borrar) del inventario.
 */
public class InventarioController {
    
    private final InventarioModel modelo;
    private final InventarioView vista;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    // --- Métodos de Acción del Inventario ---

    public void VerInventario() {
        vista.MostrarInventario(modelo.ObtenerItems());
    }

    public void AgregarItem() {
        String nombre = vista.leerNombreItem();
        int cantidad = vista.leerCantidad();
        String descripcion = vista.leerDescripcion(); 
        String tipo = vista.leerTipoItem();
        
        if (nombre.isEmpty() || cantidad <= 0 || descripcion.isEmpty()) {
            vista.MostrarMensaje("Datos inválidos. El nombre, la cantidad y la descripción deben ser válidos.");
            return;
        }

        Item nuevoItem = null;

        if (tipo.equals("A")) {
             nuevoItem = new Arma(nombre, cantidad, descripcion, 10); 
        } else if (tipo.equals("P")) {
             nuevoItem = new Pocion(nombre, cantidad, descripcion, 50); 
        } else {
             nuevoItem = new Item(nombre, cantidad, descripcion);
        }

        modelo.AgregarItem(nuevoItem);
        vista.MostrarMensaje("Ítem " + nombre + " agregado/actualizado con éxito.");
    }
    
    public void EliminarItem() {
        String nombre = vista.leerNombreItem();
        if (nombre.isEmpty()) {
            vista.MostrarMensaje("El nombre del ítem no puede estar vacío.");
            return;
        }
        
        if (modelo.EliminarItem(nombre)) {
            vista.MostrarMensaje("Ítem '" + nombre + "' eliminado del inventario.");
        } else {
            vista.MostrarMensaje("ERROR: No se pudo eliminar el ítem '" + nombre + "' (no encontrado).");
        }
    }
    
    public void MostrarDetalles() {
        String nombre = vista.leerNombreItem();
        if (nombre.isEmpty()) return;

        Item item = modelo.BuscarItem(nombre);
        vista.MostrarDetallesItem(item);
    }
    
    // NOTA: El bucle de ejecución se quitaría de aquí para que el MainApp pueda elegir qué controlador usar
}