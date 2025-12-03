package ejercicio_01;

// Clase simple para representar un mensaje de notificación
public class Notificacion {
    private String tipo;
    private String contenido;

    public Notificacion(String tipo, String contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "[" + tipo + "] " + contenido;
    }
}
 