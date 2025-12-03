// Archivo: ejercicio_02/DescuentoPorcentualAcumulado.java
package ejercicio_02;

import java.util.List;
import java.util.Comparator;
import java.util.Optional;

public class DescuentoPorcentualAcumulado implements DiscountStrategy {

    @Override
    public double calcularPrecioFinal(List<Producto> productos) {
        double total = productos.stream().mapToDouble(Producto::getPrecioBase).sum();

        if (productos.size() >= 3) {
            // Encontrar el producto con el precio más bajo
            Optional<Producto> productoMasBajo = productos.stream()
                .min(Comparator.comparingDouble(Producto::getPrecioBase));

            if (productoMasBajo.isPresent()) {
                double precioMasBajo = productoMasBajo.get().getPrecioBase();
                double descuentoAplicado = precioMasBajo * 0.50; // 50% de descuento
                return total - descuentoAplicado;
            }
        }
        
        return total; // No hay 3 productos, no hay descuento.
    }

    @Override
    public String getNombre() {
        return "DescuentoPorcentualAcumulado (50% al más barato si hay 3+)";
    }
}