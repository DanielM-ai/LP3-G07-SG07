// Archivo: ejercicio_02/DescuentoFijo.java
package ejercicio_02;

import java.util.List;

public class DescuentoFijo implements DiscountStrategy {
    private static final double PORCENTAJE = 0.10; // 10% de descuento

    @Override
    public double calcularPrecioFinal(List<Producto> productos) {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecioBase();
        }
        // Aplica 10% al total
        return total * (1.0 - PORCENTAJE);
    }

    @Override
    public String getNombre() {
        return "DescuentoFijo (10% al Total)";
    }
}