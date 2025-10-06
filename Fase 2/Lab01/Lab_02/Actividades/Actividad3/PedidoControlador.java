
package Actividades;
public class PedidoControlador {
    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarDato("Selecciona una opción: ");
            switch (opcion) {
                case "1": agregarPedido(); break;
                case "2": mostrarPedidosActivos(); break;
                case "3": marcarComoCompletado(); break;
                case "4": eliminarPedido(); break;
                case "5": mostrarPedidosPendientes(); break;
                case "6": mostrarContadorPendientes(); break;
                case "7": mostrarHistorial(); break;
                case "8": vista.mostrarMensaje("Saliendo del sistema..."); break;
                default: vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo."); break;
            }
        } while (!opcion.equals("8"));
        vista.cerrarScanner();
    }

    private void agregarPedido() {
        String nombre = vista.solicitarDato("Introduce el nombre del plato: ");
        String tipo = vista.solicitarDato("Introduce el tipo (Ej: Entrada, Principal, Postre): ");
        if (!nombre.isEmpty() && !tipo.isEmpty()) {
            modelo.agregarPedido(new Pedido(nombre, tipo));
            vista.mostrarMensaje("Pedido '" + nombre + "' agregado como PENDIENTE.");
        } else {
            vista.mostrarMensaje("El nombre y el tipo no pueden estar vacíos.");
        }
    }

    private void mostrarPedidosActivos() {
        vista.mostrarPedidos("Pedidos Activos", modelo.getPedidosActivos());
    }

    private void marcarComoCompletado() {
        mostrarPedidosActivos();
        try {
            int indice = Integer.parseInt(vista.solicitarDato("Índice del pedido a marcar como completado: "));
            if (modelo.marcarComoCompletado(indice)) {
                vista.mostrarMensaje("Pedido marcado como COMPLETADO.");
            } else {
                vista.mostrarMensaje("Índice no válido.");
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Por favor, introduce un número válido.");
        }
    }

    private void eliminarPedido() {
        mostrarPedidosActivos();
        try {
            int indice = Integer.parseInt(vista.solicitarDato("Índice del pedido a eliminar: "));
            if (modelo.eliminarPedido(indice)) {
                vista.mostrarMensaje("Pedido movido al historial como ELIMINADO.");
            } else {
                vista.mostrarMensaje("Índice no válido.");
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Por favor, introduce un número válido.");
        }
    }

    private void mostrarPedidosPendientes() {
        vista.mostrarPedidos("Pedidos Pendientes", modelo.getPedidosPorEstado(Pedido.Estado.PENDIENTE));
    }

    private void mostrarContadorPendientes() {
        vista.mostrarContador(modelo.contarPedidosPendientes());
    }

    private void mostrarHistorial() {
        vista.mostrarPedidos("Historial de Pedidos", modelo.getHistorialPedidos());
    }
}