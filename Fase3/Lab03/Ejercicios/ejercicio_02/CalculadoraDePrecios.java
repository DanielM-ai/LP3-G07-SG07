// Archivo: ejercicio_02/CalculadoraDePrecios.java
package ejercicio_02;

import java.util.List;

// El Contexto
public class CalculadoraDePrecios {
    private DiscountStrategy estrategia;

    // Permite cambiar la estrategia en tiempo de ejecución
    public void setEstrategia(DiscountStrategy estrategia) {
        this.estrategia = estrategia;
    }

    // Delega el trabajo del cálculo a la estrategia configurada
    public double calcular(List<Producto> productos) {
        if (estrategia == null) {
            System.err.println("Error: No se ha configurado una estrategia de descuento.");
            return productos.stream().mapToDouble(Producto::getPrecioBase).sum();
        }
        System.out.println("Aplicando estrategia: " + estrategia.getNombre());
        return estrategia.calcularPrecioFinal(productos);
    }
}