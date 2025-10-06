package Actividades;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1": agregarPedido(); break;
                case "2": mostrarPedidos(); break;
                case "3": actualizarPedido(); break;
                case "4": eliminarPedido(); break;
                case "5": buscarPedidos(); break;
                case "6": contarPedidos(); break;
                case "7": vista.mostrarMensaje("Saliendo..."); break;
                default: vista.mostrarMensaje("Opción no válida."); break;
            }
        } while (!opcion.equals("7"));
        vista.cerrarScanner();
    }
    
    private void agregarPedido() {
        String nombre = vista.solicitarDato("Introduce el nombre del plato: ");
        String tipo = vista.solicitarDato("Introduce el tipo (Entrada, Principal, Postre): ");
        if (!nombre.isEmpty() && !tipo.isEmpty()) {
            modelo.agregarPedido(new Pedido(nombre, tipo));
            vista.mostrarMensaje("Pedido agregado.");
        } else {
            vista.mostrarMensaje("El nombre y el tipo no pueden estar vacíos.");
        }
    }

    private void mostrarPedidos() {
        vista.mostrarPedidos(modelo.getPedidos());
    }

    private void actualizarPedido() {
        int indice = Integer.parseInt(vista.solicitarDato("Índice del pedido a actualizar: "));
        String nuevoNombre = vista.solicitarDato("Nuevo nombre del plato: ");
        String nuevoTipo = vista.solicitarDato("Nuevo tipo del plato: ");
        if (modelo.actualizarPedido(indice, nuevoNombre, nuevoTipo)) {
            vista.mostrarMensaje("Pedido actualizado correctamente.");
        } else {
            vista.mostrarMensaje("Índice no válido.");
        }
    }

    private void eliminarPedido() {
        int indice = Integer.parseInt(vista.solicitarDato("Índice del pedido a eliminar: "));
        if (modelo.eliminarPedido(indice)) {
            vista.mostrarMensaje("Pedido eliminado.");
        } else {
            vista.mostrarMensaje("Índice no válido.");
        }
    }
    
    private void buscarPedidos() {
        String termino = vista.solicitarDato("Buscar por nombre o tipo: ");
        List<Pedido> resultados = modelo.buscarPedidos(termino);
        vista.mostrarPedidos(resultados);
    }
    
    private void contarPedidos() {
        int total = modelo.contarTotalPedidos();
        Map<String, Long> porTipo = modelo.getPedidos().stream()
                .collect(Collectors.groupingBy(Pedido::getTipo, Collectors.counting()));
        vista.mostrarContador(total, porTipo);
    }
}