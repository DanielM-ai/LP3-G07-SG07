package controller;

import model.Carrito;
import model.HistorialManager; 
import modelData.Producto;
import view.CarritoView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarritoController {
    
    private final Carrito modeloCarrito;
    private final CarritoView vista;
    private final HistorialManager historialManager; 
    private final List<Producto> catalogo;

    public CarritoController(Carrito modeloCarrito, CarritoView vista, HistorialManager historialManager) {
        this.modeloCarrito = modeloCarrito;
        this.vista = vista;
        this.historialManager = historialManager;
        this.catalogo = inicializarCatalogo();
    }
    
    // --- Lógica de la Interacción (Bucle Principal) ---
    
    public void ejecutarAplicacion() {
        int opcion;
        boolean salir = false;

        while (!salir) {
            // 1. Pide a la View que muestre el menú
            vista.mostrarMenu();
            // 2. Pide a la View que lea la opción
            opcion = vista.leerOpcionMenu();

            // 3. Controla la lógica según la opción
            switch (opcion) {
                case 1:
                    listarProductos();
                    break;
                case 2:
                    // Pide a la View que lea los datos necesarios
                    int idAgregar = vista.leerIdProducto();
                    int cantidad = vista.leerCantidad();
                    if (idAgregar > 0 && cantidad > 0) {
                        agregarProductoAlCarrito(idAgregar, cantidad);
                    } else {
                        vista.mostrarMensaje("Entrada inválida. Asegúrese de ingresar números positivos.");
                    }
                    break;
                case 3:
                    int idEliminar = vista.leerIdProducto();
                    if (idEliminar > 0) {
                        eliminarProductoDelCarrito(idEliminar);
                    } else {
                        vista.mostrarMensaje("Entrada inválida. Ingrese un ID de producto válido.");
                    }
                    break;
                case 4:
                    double porcentaje = vista.leerPorcentajeDescuento();
                    if (porcentaje >= 0 && porcentaje <= 100) {
                        aplicarDescuento(porcentaje);
                    } else {
                         vista.mostrarMensaje("Entrada inválida. Ingrese un porcentaje entre 0 y 100.");
                    }
                    break;
                case 5:
                    verCarrito();
                    break;
                case 6:
                    realizarCompra();
                    break;
                case 7:
                    verHistorial();
                    break;
                case 0:
                    salir = true;
                    vista.mostrarMensaje("¡Gracias por usar la aplicación de compras! Hasta pronto.");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    // --- Métodos de Acción (El resto son los mismos métodos de lógica) ---

    // Simula la obtención de productos (Capas de Acceso a Datos)
    private List<Producto> inicializarCatalogo() {
        return new ArrayList<>(Arrays.asList(
            new Producto(101, "Laptop Gamer", 1200.00),
            new Producto(102, "Mouse Inalámbrico", 25.50),
            new Producto(103, "Monitor 4K", 450.00),
            new Producto(104, "Teclado Mecánico", 75.00)
        ));
    }
    
    public void listarProductos() {
        vista.mostrarProductos(catalogo);
    }
    
    public void agregarProductoAlCarrito(int idProducto, int cantidad) {
        Producto productoEncontrado = catalogo.stream()
            .filter(p -> p.getId() == idProducto)
            .findFirst()
            .orElse(null);

        if (productoEncontrado != null) {
            modeloCarrito.agregarProducto(productoEncontrado, cantidad);
            vista.mostrarMensaje(cantidad + " x " + productoEncontrado.getNombre() + " agregado(s).");
        } else {
            vista.mostrarMensaje("ERROR: Producto con ID " + idProducto + " no encontrado.");
        }
    }

    public void eliminarProductoDelCarrito(int idProducto) {
        modeloCarrito.eliminarProducto(idProducto);
        vista.mostrarMensaje("Producto con ID " + idProducto + " eliminado del carrito.");
    }
    
    public void aplicarDescuento(double porcentaje) {
        // ... (lógica de descuento es la misma) ...
        if (porcentaje < 0 || porcentaje > 100) {
            vista.mostrarMensaje("ERROR: Descuento debe ser entre 0 y 100.");
            return;
        }
        modeloCarrito.aplicarDescuento(porcentaje);
        vista.mostrarMensaje("Descuento del " + porcentaje + "% aplicado.");
    }

    public void verCarrito() {
        // ... (lógica para obtener datos del Model es la misma) ...
        double subtotal = modeloCarrito.calcularSubtotal();
        double descuento = modeloCarrito.calcularDescuento();
        double envio = modeloCarrito.getCostoEnvio();
        double total = modeloCarrito.calcularTotal();

        vista.mostrarCarrito(modeloCarrito.getItems(), subtotal, descuento, envio, total);
    }
    
    public void realizarCompra() {
        // ... (lógica de compra es la misma) ...
        if (modeloCarrito.estaVacio()) {
            vista.mostrarMensaje("ERROR: No puedes realizar la compra, el carrito está vacío.");
            return;
        }
        
        double total = modeloCarrito.calcularTotal();
        historialManager.guardarCompra(modeloCarrito.getItems(), total);
        modeloCarrito.vaciar();
        
        vista.mostrarMensaje("¡COMPRA REALIZADA CON ÉXITO! Total pagado: $" + String.format("%.2f", total));
    }
    
    public void verHistorial() {
        vista.mostrarHistorial(historialManager.getHistorial());
    }
}

