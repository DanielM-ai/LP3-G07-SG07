package model;

import item.Item;
import java.util.ArrayList;
import java.util.List;

public class InventarioModel {
    private List<Item> items; 

    public InventarioModel() {
        this.items = new ArrayList<>();
    }

    // AgregarItem(Item item)
    public void AgregarItem(Item newItem) {
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
        // Elimina el ítem completo si la cantidad es 0 o si se pide eliminar explícitamente.
        return items.removeIf(item -> item.getNombre().equalsIgnoreCase(nombreItem));
    }
    
    // ObtenerItems()
    public List<Item> ObtenerItems() {
        return new ArrayList<>(items); 
    }

    // BuscarItem(String nombre)
    public Item BuscarItem(String nombre) {
        return items.stream()
            .filter(item -> item.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElse(null);
    }
    
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