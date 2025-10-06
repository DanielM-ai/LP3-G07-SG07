package view;

import modelData.Producto;
import modelData.ItemCarrito;
import model.HistorialCompras;
import java.util.List;
import java.util.Scanner; // Importante para la interacción

public class CarritoView {
    
    private final Scanner scanner; 
    
    // Constructor
    public CarritoView() {
        this.scanner = new Scanner(System.in);
    }
    
    // --- Métodos de LECTURA (Input) ---

    public void mostrarMenu() {
        System.out.println("\n==================================");
        System.out.println("      MENÚ DE COMPRAS EN LÍNEA      ");
        System.out.println("==================================");
        System.out.println("1. Listar Productos disponibles");
        System.out.println("2. Agregar Producto al carrito");
        System.out.println("3. Eliminar Producto del carrito");
        System.out.println("4. Aplicar Descuento");
        System.out.println("5. Ver Carrito y Total");
        System.out.println("6. Realizar Compra");
        System.out.println("7. Ver Historial de Compras");
        System.out.println("0. Salir de la Aplicación");
        System.out.println("==================================");
        System.out.print("Seleccione una opción: ");
    }
    
    public int leerOpcionMenu() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }
    
    public int leerIdProducto() {
        try {
            System.out.print("Ingrese el ID del producto: ");
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; 
        }
    }
    
    public int leerCantidad() {
        try {
            System.out.print("Ingrese la cantidad: ");
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public double leerPorcentajeDescuento() {
        try {
            System.out.print("Ingrese el porcentaje de descuento a aplicar (0-100): ");
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }

    // --- Métodos de ESCRITURA (Output) ---

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n--- Mensaje ---");
        System.out.println(mensaje);
        System.out.println("---------------");
    }

    public void mostrarProductos(List<Producto> productos) {
        System.out.println("\n--- LISTA DE PRODUCTOS DISPONIBLES ---");
        for (Producto p : productos) {
            System.out.println(p);
        }
        System.out.println("----------------------------------------");
    }

    public void mostrarCarrito(List<ItemCarrito> items, double subtotal, double descuento, double envio, double total) {
        System.out.println("\n--- ESTADO ACTUAL DEL CARRITO ---");
        
        if (items.isEmpty()) {
            System.out.println("El carrito está vacío.");
            System.out.println("----------------------------------------");
            return;
        }

        for (ItemCarrito item : items) {
            System.out.println(" - " + item);
        }
        
        System.out.println("----------------------------------------");
        System.out.println(" Subtotal:      $" + String.format("%.2f", subtotal));
        System.out.println(" Descuento:     -$" + String.format("%.2f", descuento));
        System.out.println(" Costo Envío:   +$" + String.format("%.2f", envio));
        System.out.println("----------------------------------------");
        System.out.println(" TOTAL A PAGAR: $" + String.format("%.2f", total));
        System.out.println("----------------------------------------");
    }
    
    public void mostrarHistorial(List<HistorialCompras> historial) {
        System.out.println("\n--- HISTORIAL DE COMPRAS ---");
        if (historial.isEmpty()) {
            System.out.println("No hay compras registradas.");
            return;
        }
        for (HistorialCompras compra : historial) {
            System.out.println(compra);
            for (ItemCarrito item : compra.getItemsComprados()) {
                 System.out.println("    [Detalle] " + item.getProducto().getNombre() + " x" + item.getCantidad());
             }
        }
        System.out.println("----------------------------------------");
    }
}

