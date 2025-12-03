// Archivo: ejercicio_02/Main.java
package ejercicio_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculadoraDePrecios calculadora = new CalculadoraDePrecios();

        // Lista de productos para la compra
        List<Producto> compra = new ArrayList<>();
        compra.add(new Producto("Laptop", 1000.0));
        compra.add(new Producto("Mouse", 20.0));
        compra.add(new Producto("Mouse", 20.0)); // Producto duplicado para Estrategia C y D
        compra.add(new Producto("Teclado", 50.0)); // Producto extra para Estrategia D

        double totalBase = compra.stream().mapToDouble(Producto::getPrecioBase).sum();

        System.out.println("--- 🛒 Simulación de Compra ---");
        System.out.println("Productos en el carrito (Total Base: " + totalBase + "):");
        for (Producto p : compra) {
            System.out.println("- " + p.getNombre() + " (" + p.getPrecioBase() + ")");
        }
        System.out.println("--------------------------------");

        while (true) {
            System.out.println("\nSeleccione una Estrategia de Descuento:");
            System.out.println("1. Sin Descuento");
            System.out.println("2. Descuento Fijo (10% al total)");
            System.out.println("3. Descuento Porcentual (30% a productos duplicados)");
            System.out.println("4. Descuento Porcentual Acumulado (50% al más barato si hay 3+)");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                DiscountStrategy estrategiaSeleccionada = null;

                switch (opcion) {
                    case 1:
                        estrategiaSeleccionada = new NoDiscount();
                        break;
                    case 2:
                        estrategiaSeleccionada = new DescuentoFijo();
                        break;
                    case 3:
                        estrategiaSeleccionada = new DescuentoPorcentual();
                        break;
                    case 4:
                        estrategiaSeleccionada = new DescuentoPorcentualAcumulado();
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación.");
                        scanner.close();
                        return;
                    default:
                        System.err.println("Opción no válida. Intente de nuevo.");
                        continue;
                }

                // **ACCIÓN DEL PATRÓN STRATEGY**: Cambiar la estrategia en tiempo de ejecución
                calculadora.setEstrategia(estrategiaSeleccionada);
                
                // Calcular y mostrar el resultado
                double precioFinal = calculadora.calcular(compra);
                System.out.printf("✅ Precio Final: %.2f (Descuento aplicado: %.2f)\n", 
                                  precioFinal, (totalBase - precioFinal));

            } else {
                System.err.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar el buffer
            }
        }
    }
}