package model;

import modelData.Producto;
import modelData.ItemCarrito;
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> items;
    private double descuentoPorcentaje = 0.0;
    private final double COSTO_ENVIO = 5.0;

    public Carrito() {
        this.items = new ArrayList<>();
    }
    
    // --- Lógica de Negocio Pura ---

    public void agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) return;

        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == producto.getId()) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void eliminarProducto(int productoId) {
        // Elimina el item si el ID del producto coincide
        items.removeIf(item -> item.getProducto().getId() == productoId);
    }
    
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje >= 0 && porcentaje <= 100) {
            this.descuentoPorcentaje = porcentaje / 100.0;
        }
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }
    
    public double calcularDescuento() {
        return calcularSubtotal() * descuentoPorcentaje;
    }
    
    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        double totalSinEnvio = subtotal - calcularDescuento();
        return totalSinEnvio + COSTO_ENVIO;
    }
    
    public List<ItemCarrito> getItems() {
        return new ArrayList<>(items); // Devolvemos una copia
    }

    public double getCostoEnvio() {
        return COSTO_ENVIO;
    }
    
    public double getDescuentoAplicado() {
        return this.descuentoPorcentaje * 100;
    }
    
    public boolean estaVacio() {
        return items.isEmpty();
    }
    
    public void vaciar() {
        items.clear(); // Limpia el carrito después de la compra
        this.descuentoPorcentaje = 0.0; // Resetea el descuento
    }
}