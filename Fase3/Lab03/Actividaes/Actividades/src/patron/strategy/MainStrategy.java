package patron.strategy;



//1. IMPORTS (Siempre al inicio)
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//2. CLASES Y ESTRATEGIAS (Fuera de la clase principal)

//Modelo Producto
class Producto {
 String nombre;
 double precio;

 public Producto(String nombre, double precio) {
     this.nombre = nombre;
     this.precio = precio;
 }
}

//Interfaz Estrategia
interface EstrategiaDescuento {
 double calcularTotal(List<Producto> productos);
}

//Estrategia 1: Sin descuento
class SinDescuento implements EstrategiaDescuento {
 @Override
 public double calcularTotal(List<Producto> productos) {
     return productos.stream().mapToDouble(p -> p.precio).sum();
 }
}

//Estrategia 2: Descuento Fijo (10% del total)
class DescuentoFijo implements EstrategiaDescuento {
 @Override
 public double calcularTotal(List<Producto> productos) {
     double total = productos.stream().mapToDouble(p -> p.precio).sum();
     return total * 0.90; // Aplica 10% descuento
 }
}

//Estrategia 3: Descuento Porcentual (30% si hay 2 productos)
class DescuentoPorcentual implements EstrategiaDescuento {
 @Override
 public double calcularTotal(List<Producto> productos) {
     double total = productos.stream().mapToDouble(p -> p.precio).sum();
     // Lógica simplificada: si son exactamente 2 productos, aplica 30%
     if (productos.size() == 2) {
         System.out.println("(Promoción aplicada: 30% por llevar 2 productos)");
         return total * 0.70;
     }
     return total;
 }
}

//Estrategia 4: Descuento Acumulado (Si son 3+, 50% al más barato)
class DescuentoAcumulado implements EstrategiaDescuento {
 @Override
 public double calcularTotal(List<Producto> productos) {
     double total = productos.stream().mapToDouble(p -> p.precio).sum();
     
     if (productos.size() >= 3) {
         double minPrecio = Double.MAX_VALUE;
         for(Producto p : productos) {
             if(p.precio < minPrecio) minPrecio = p.precio;
         }
         System.out.println("(Promoción aplicada: 50% de descuento al producto de precio " + minPrecio + ")");
         return total - (minPrecio * 0.50);
     }
     return total;
 }
}

//Contexto: La Calculadora
class CalculadoraPrecios {
 private EstrategiaDescuento estrategia;
 private List<Producto> productos;

 public CalculadoraPrecios() {
     this.productos = new ArrayList<>();
     this.estrategia = new SinDescuento(); // Estrategia por defecto
 }

 public void agregarProducto(Producto p) {
     productos.add(p);
 }

 public void setEstrategia(EstrategiaDescuento estrategia) {
     this.estrategia = estrategia;
 }

 public double ejecutarCalculo() {
     return estrategia.calcularTotal(productos);
 }
}

//3. CLASE PRINCIPAL (Debe llamarse MainStrategy)
public class MainStrategy {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     CalculadoraPrecios calculadora = new CalculadoraPrecios();
     
     // Cargamos 3 productos para probar las condiciones
     calculadora.agregarProducto(new Producto("Camisa", 50.0));
     calculadora.agregarProducto(new Producto("Pantalón", 100.0));
     calculadora.agregarProducto(new Producto("Zapatos", 80.0)); 

     System.out.println("--- Sistema de Ventas (Strategy) ---");
     System.out.println("Productos en carrito: 3");
     System.out.println("Seleccione tipo de cliente/promoción:");
     System.out.println("1. Normal (Sin Descuento)");
     System.out.println("2. Cliente Frecuente (10% Fijo)");
     System.out.println("3. Promoción Pareja (30% si lleva 2)");
     System.out.println("4. Promoción Mayorista (50% en el 3ro si lleva 3+)");
     
     int opcion = scanner.nextInt();

     switch (opcion) {
         case 1: calculadora.setEstrategia(new SinDescuento()); break;
         case 2: calculadora.setEstrategia(new DescuentoFijo()); break;
         case 3: calculadora.setEstrategia(new DescuentoPorcentual()); break;
         case 4: calculadora.setEstrategia(new DescuentoAcumulado()); break;
         default: System.out.println("Opción no válida.");
     }

     System.out.printf("Total a pagar: %.2f\n", calculadora.ejecutarCalculo());
     scanner.close();
 }
}