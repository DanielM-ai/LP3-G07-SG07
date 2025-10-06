package model;

import modelData.ItemCarrito;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistorialCompras {
    private static int nextId = 1;
    private int id;
    private LocalDateTime fecha;
    private double montoTotal;
    private List<ItemCarrito> itemsComprados;

    public HistorialCompras(double montoTotal, List<ItemCarrito> items) {
        this.id = nextId++;
        this.fecha = LocalDateTime.now();
        this.montoTotal = montoTotal;
        this.itemsComprados = new ArrayList<>(items); 
    }

    // Getters para la View/Controller
    public int getId() { return id; }
    public LocalDateTime getFecha() { return fecha; }
    public double getMontoTotal() { return montoTotal; }
    public List<ItemCarrito> getItemsComprados() { return itemsComprados; }
    
    @Override
    public String toString() {
        return "Compra #" + id + " - Fecha: " + fecha.toLocalDate() + " - Total: $" + String.format("%.2f", montoTotal);
    }
}