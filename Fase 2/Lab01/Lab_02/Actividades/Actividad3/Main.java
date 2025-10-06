
package Actividades;

public class Main {
    public static void main(String[] args) {
        // Crear las instancias de Modelo y Vista
        PedidoModelo modelo = new PedidoModelo();
        PedidoVista vista = new PedidoVista();

        // Crear el Controlador y pasarle el Modelo y la Vista
        PedidoControlador controlador = new PedidoControlador(modelo, vista);

        // Iniciar la aplicación
        controlador.iniciar();
    }
}