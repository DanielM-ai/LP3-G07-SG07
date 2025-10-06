package Actividades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoModelo {
    private List<Pedido> pedidos;

    public PedidoModelo() {
        pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    // Nuevas funcionalidades
    public boolean eliminarPedido(int indice) {
        if (indice >= 0 && indice < pedidos.size()) {
            pedidos.remove(indice);
            return true;
        }
        return false;
    }

    public boolean actualizarPedido(int indice, String nuevoNombre, String nuevoTipo) {
        if (indice >= 0 && indice < pedidos.size()) {
            Pedido pedido = pedidos.get(indice);
            pedido.setNombrePlato(nuevoNombre);
            pedido.setTipo(nuevoTipo);
            return true;
        }
        return false;
    }

    public List<Pedido> buscarPedidos(String termino) {
        return pedidos.stream()
                .filter(p -> p.getNombrePlato().toLowerCase().contains(termino.toLowerCase()) || 
                             p.getTipo().toLowerCase().contains(termino.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public int contarTotalPedidos() {
        return pedidos.size();
    }
}