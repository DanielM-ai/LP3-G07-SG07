package view;

import item.Item;
import java.util.List;
import java.util.Scanner;

public class InventarioView {
    
    private final Scanner scanner; 

    public InventarioView() {
        this.scanner = new Scanner(System.in);
    }

    // --- Métodos de LECTURA (Input) ---
    
    public void mostrarMenu() {
        System.out.println("\n==================================");
        System.out.println("      MENÚ DE GESTIÓN DE INVENTARIO      ");
        System.out.println("==================================");
        System.out.println("1. Ver Inventario (MostrarInventario)");
        System.out.println("2. Agregar Ítem");
        System.out.println("3. Eliminar Ítem (por nombre)");
        System.out.println("4. Buscar Detalle de Ítem");
        System.out.println("5. Usar Ítem");
        System.out.println("0. Salir");
        System.out.println("==================================");
        System.out.print("Seleccione una opción: ");
    }
    
    public int leerOpcionMenu() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public String leerNombreItem() {
        System.out.print("Ingrese el nombre del ítem: ");
        return scanner.nextLine().trim();
    }
    
    public int leerCantidad() {
        try {
            System.out.print("Ingrese la cantidad: ");
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    // <--- MÉTODO NUEVO PARA LA DESCRIPCIÓN --->
    public String leerDescripcion() {
        System.out.print("Ingrese la descripción del ítem: ");
        return scanner.nextLine().trim();
    }
    
    public String leerTipoItem() {
         System.out.print("Ingrese el tipo de ítem (A: Arma / P: Pocion / O: Otro): ");
         return scanner.nextLine().trim().toUpperCase();
    }
    
    // --- Métodos de ESCRITURA (Output) ---

    public void MostrarInventario(List<Item> items) {
        System.out.println("\n--- INVENTARIO ACTUAL (" + items.size() + " ITEMS ÚNICOS) ---");
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("----------------------------------------");
    }

    public void MostrarMensaje(String mensaje) {
        System.out.println(">>> " + mensaje + " <<<");
    }

    public void MostrarDetallesItem(Item item) {
        if (item == null) {
            MostrarMensaje("ERROR: Ítem no encontrado.");
            return;
        }
        System.out.println("\n--- DETALLES DEL ÍTEM: " + item.getNombre() + " ---");
        System.out.println(" Tipo: " + item.getTipo());
        System.out.println(" Cantidad: " + item.getCantidad());
        System.out.println(" Descripción: " + item.getDescripcion());
        System.out.println("----------------------------------------");
    }
}