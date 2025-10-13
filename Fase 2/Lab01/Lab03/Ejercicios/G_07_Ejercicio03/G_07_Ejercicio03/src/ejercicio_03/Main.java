package ejercicio_03;

public class Main {
    public static void main(String[] args) {
        VistaEmpleado vista = new VistaEmpleado();
        ControladorEmpleado controlador = new ControladorEmpleado(vista, "empleados.txt");

        // Carga inicial y mostrar automáticamente al inicio
        controlador.leerEmpleados();
        controlador.listarEmpleados();

        // Loop del menú
        while (true) {
            vista.mostrarMenu();
            int opcion = vista.obtenerOpcion();
            vista.mostrarMensaje("");  // Línea en blanco para claridad

            switch (opcion) {
                case 1:
                    controlador.listarEmpleados();
                    break;
                case 2:
                    controlador.agregarEmpleado();
                    break;
                case 3:
                    int numBuscar = vista.pedirNumeroBusqueda();
                    controlador.buscarEmpleado(numBuscar);
                    break;
                case 4:
                    int numEliminar = vista.pedirNumeroBusqueda();
                    controlador.eliminarEmpleado(numEliminar);
                    break;
                case 5:
                    controlador.guardarEmpleados();  // Guardar final antes de salir
                    vista.mostrarMensaje("Programa finalizado. Datos guardados.");
                    vista.cerrar();
                    return;  // Salir
                default:
                    vista.mostrarMensaje("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
