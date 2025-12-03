// Archivo: ejercicio_02/DiscountStrategy.java
package ejercicio_02;

import java.util.List;

public interface DiscountStrategy {
    // El método define el contrato: calcular el precio final de una lista de productos
    double calcularPrecioFinal(List<Producto> productos);
    String getNombre();
}