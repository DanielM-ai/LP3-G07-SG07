// Archivo: ejercicio_02/NoDiscount.java
package ejercicio_02;

import java.util.List;

public class NoDiscount implements DiscountStrategy {

    @Override
    public double calcularPrecioFinal(List<Producto> productos) {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecioBase();
        }
        return total; // Retorna el precio base sin cambios
    }

    @Override
    public String getNombre() {
        return "SinDescuento";
    }
}