package ejercicio_04;

public class Main {
    public static void main(String[] args) {
        VistaContador vista = new VistaContador();
        ContadorPalabras contador = new ContadorPalabras();

        // Loop para seleccionar archivo válido
        String rutaArchivo = null;
        while (rutaArchivo == null) {
            rutaArchivo = vista.seleccionarArchivo();
            if (rutaArchivo == null) {
                // Si cancela o error, termina
                vista.mostrarMensaje("No se seleccionó un archivo válido. Saliendo...");
                return;
            }
        }

        // Procesar el archivo
        try {
            contador.procesarArchivo(rutaArchivo, vista);
            vista.mostrarResultados(contador);
            contador.obtenerPalabrasFrecuentes(vista);
        } catch (Exception e) {
            vista.mostrarError("No se pudo procesar el archivo. Intente con otro.");
            // El loop de selección ya maneja reintentos, pero aquí terminamos si falla después de seleccionar
        }

        vista.mostrarMensaje("\nAnálisis completado. Presione Enter para salir.");
        new java.util.Scanner(System.in).nextLine();  // Pausa simple
    }
}
