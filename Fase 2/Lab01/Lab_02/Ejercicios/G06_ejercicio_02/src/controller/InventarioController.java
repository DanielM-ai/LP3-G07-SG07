package controller;

import model.InventarioModel;
import view.InventarioView;
import item.Item;
import item.Arma;
import item.Pocion;

public class InventarioController {
    
    private final InventarioModel modelo;
    private final InventarioView vista;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    // --- Métodos de Acción ---

    public void VerInventario() {
        vista.MostrarInventario(modelo.ObtenerItems());
    }

    // <--- MÉTODO MODIFICADO PARA LA DESCRIPCIÓN --->
    public void AgregarItem() {
        String nombre = vista.leerNombreItem();
        int cantidad = vista.leerCantidad();
        String descripcion = vista.leerDescripcion(); // <--- Llama al método nuevo de la View
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
    
    public void UsarItem() {
        String nombre = vista.leerNombreItem();
        if (nombre.isEmpty()) return;
        
        String mensaje = modelo.usarItemPorNombre(nombre);
        vista.MostrarMensaje(mensaje);
    }

    // --- Bucle principal de la aplicación ---
    
    public void ejecutarAplicacion() {
        int opcion;
        boolean salir = false;

        // Inicializar con algunos ítems de ejemplo
        modelo.AgregarItem(new Arma("Espada de Hierro", 1, "Arma básica para principiantes.", 10));
        modelo.AgregarItem(new Pocion("Poción de Vida", 3, "Restaura la salud en emergencias.", 50));
        modelo.AgregarItem(new Item("Moneda de Oro", 100, "Moneda antigua y valiosa."));

        while (!salir) {
            vista.mostrarMenu();
            opcion = vista.leerOpcionMenu();

            switch (opcion) {
                case 1:
                    VerInventario();
                    break;
                case 2:
                    AgregarItem();
                    break;
                case 3:
                    EliminarItem();
                    break;
                case 4:
                    MostrarDetalles();
                    break;
                case 5:
                    UsarItem();
                    break;
                case 0:
                    salir = true;
                    vista.MostrarMensaje("¡Saliendo del sistema de inventario!");
                    break;
                default:
                    vista.MostrarMensaje("Opción no válida. Intente de nuevo.");
            }
        }
    }
}