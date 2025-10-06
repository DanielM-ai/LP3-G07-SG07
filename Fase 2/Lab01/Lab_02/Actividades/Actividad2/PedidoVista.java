package Actividades;

import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.stream.Collectors;

public class PedidoVista {
    private Scanner scanner;

    public PedidoVista() {
        scanner = new Scanner(System.in);
    }
    
    public String solicitarDato(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
    
    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos que coincidan.");
        } else {
            System.out.println("--- Lista de Pedidos ---");
            for (int i = 0; i < pedidos.size(); i++) {
                Pedido p = pedidos.get(i);
                System.out.println(i + ". Plato: " + p.getNombrePlato() + " | Tipo: " + p.getTipo());
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("\n--- Opciones del Restaurante ---");
        System.out.println("1. Agregar Pedido");
        System.out.println("2. Mostrar Todos los Pedidos");
        System.out.println("3. Actualizar un Pedido");
        System.out.println("4. Eliminar un Pedido");
        System.out.println("5. Buscar Pedidos");
        System.out.println("6. Contar Pedidos");
        System.out.println("7. Salir");
    }

    public void mostrarContador(int total, Map<String, Long> porTipo) {
        System.out.println("Total de pedidos: " + total);
        System.out.println("Pedidos por tipo:");
        if (porTipo.isEmpty()) {
            System.out.println("  No hay pedidos para contar por tipo.");
        } else {
            porTipo.forEach((tipo, count) -> System.out.println("  - " + tipo + ": " + count));
        }
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public void cerrarScanner() {
        scanner.close();
    }
}