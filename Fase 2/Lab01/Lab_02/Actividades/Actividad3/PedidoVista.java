
package Actividades;
import java.util.List;
import java.util.Scanner;

public class PedidoVista {
    private Scanner scanner;

    public PedidoVista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n--- Gestión de Pedidos del Restaurante ---");
        System.out.println("1. Agregar Pedido");
        System.out.println("2. Mostrar Pedidos Activos (Pendientes/Completados)");
        System.out.println("3. Marcar Pedido como Completado");
        System.out.println("4. Eliminar Pedido");
        System.out.println("5. Mostrar Pedidos Pendientes");
        System.out.println("6. Mostrar Contador de Pedidos Pendientes");
        System.out.println("7. Ver Historial (Completados/Eliminados)");
        System.out.println("8. Salir");
    }

    public String solicitarDato(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public void mostrarPedidos(String titulo, List<Pedido> pedidos) {
        System.out.println("\n--- " + titulo + " ---");
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en esta lista.");
        } else {
            for (int i = 0; i < pedidos.size(); i++) {
                Pedido p = pedidos.get(i);
                System.out.println(i + ". Plato: " + p.getNombrePlato() +
                        " | Tipo: " + p.getTipo() +
                        " | Estado: " + p.getEstado());
            }
        }
    }
    
    public void mostrarContador(long cantidad) {
        System.out.println("Total de pedidos pendientes: " + cantidad);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}