package model;

import modelData.ItemCarrito;
import java.util.ArrayList;
import java.util.List;

public class HistorialManager {
    private List<HistorialCompras> historial;

    public HistorialManager() {
        this.historial = new ArrayList<>();
    }

    // Lógica del negocio: guardar una compra
    public void guardarCompra(List<ItemCarrito> items, double total) {
        HistorialCompras nuevaCompra = new HistorialCompras(total, items);
        historial.add(nuevaCompra);
    }

    // Lógica del negocio: obtener el historial
    public List<HistorialCompras> getHistorial() {
        return historial;
    }
}