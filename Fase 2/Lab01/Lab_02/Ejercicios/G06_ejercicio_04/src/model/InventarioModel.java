package model;

import item.Item;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la lista de ítems. Es el Model para el subsistema de Inventario.
 */
public class InventarioModel {
    private List<Item> items; 

    public InventarioModel() {
        this.items = new ArrayList<>();
    }

    // AgregarItem(Item item)
    public void AgregarItem(Item newItem) {
        // Lógica de agregado (si ya existe, aumenta la cantidad)
        for (Item item : items) {
            if (item.getNombre().equalsIgnoreCase(newItem.getNombre()) && 
                item.getTipo().equals(newItem.getTipo())) {
                item.setCantidad(item.getCantidad() + newItem.getCantidad());
                return;
            }
        }
        items.add(newItem);
    }

    // EliminarItem(String nombreItem)
    public boolean EliminarItem(String nombreItem) {
        // Elimina el ítem completo (usado cuando la cantidad llega a cero)
        return items.removeIf(item -> item.getNombre().equalsIgnoreCase(nombreItem));
    }
    

    
    // ObtenerItems()
    public List<Item> ObtenerItems() {
        return new ArrayList<>(items); 
    }

    // BuscarItem(String nombre)	
    public Item BuscarItem(String nombre) {
        for (Item item : items) { // 1. Iniciar el recorrido
            
            // 2. Aplicar la condición de filtro
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                
                // 3. Devolver y detener (findFirst)
                return item; 
            }
        }
        // 4. Si el bucle termina sin encontrar nada
        return null; 
    }
    
    // Método auxiliar (usado por el Jugador)
    public String usarItemPorNombre(String nombre) {
        Item item = BuscarItem(nombre);
        if (item != null) {
            String mensajeUso = item.UsarItem();
            // Lógica de negocio: si la cantidad llega a 0, eliminarlo
            if (item.getCantidad() <= 0) {
                this.EliminarItem(nombre); 
            }
            return mensajeUso;
        }
        return "ERROR: Ítem '" + nombre + "' no encontrado en el inventario.";
    }
}