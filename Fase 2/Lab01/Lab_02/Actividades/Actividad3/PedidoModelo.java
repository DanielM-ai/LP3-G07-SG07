

package Actividades;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoModelo {
    private List<Pedido> pedidos;

    public PedidoModelo() {
        this.pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // Devuelve solo los pedidos activos (pendientes y completados)
    public List<Pedido> getPedidosActivos() {
        return pedidos.stream()
                .filter(p -> p.getEstado() != Pedido.Estado.ELIMINADO)
                .collect(Collectors.toList());
    }

    // Marca un pedido como completado usando el índice de la lista de activos
    public boolean marcarComoCompletado(int indice) {
        List<Pedido> activos = getPedidosActivos();
        if (indice >= 0 && indice < activos.size()) {
            activos.get(indice).setEstado(Pedido.Estado.COMPLETADO);
            return true;
        }
        return false;
    }

    // Marca un pedido como eliminado (eliminación lógica)
    public boolean eliminarPedido(int indice) {
        List<Pedido> activos = getPedidosActivos();
        if (indice >= 0 && indice < activos.size()) {
            activos.get(indice).setEstado(Pedido.Estado.ELIMINADO);
            return true;
        }
        return false;
    }

    // Filtra y devuelve pedidos por un estado específico
    public List<Pedido> getPedidosPorEstado(Pedido.Estado estado) {
        return this.pedidos.stream()
                .filter(p -> p.getEstado() == estado)
                .collect(Collectors.toList());
    }

    // Cuenta los pedidos que aún están pendientes
    public long contarPedidosPendientes() {
        return this.pedidos.stream()
                .filter(p -> p.getEstado() == Pedido.Estado.PENDIENTE)
                .count();
    }

    // Devuelve el historial de pedidos completados y eliminados
    public List<Pedido> getHistorialPedidos() {
        return this.pedidos.stream()
                .filter(p -> p.getEstado() == Pedido.Estado.COMPLETADO || p.getEstado() == Pedido.Estado.ELIMINADO)
                .collect(Collectors.toList());
    }
}