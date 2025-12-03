// Archivo: ejercicio_01/Usuario.java
package ejercicio_01;

// Usuario actúa como el ConcreteObserver
public class Usuario implements Observer {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
        System.out.println("✅ Usuario " + nombre + " creado.");
    }

    public String getNombre() {
        return nombre;
    }

    // Implementación del método update para recibir la notificación
    @Override
    public void update(String notificacion) {
        System.out.println("🔔 " + nombre + " ha recibido una notificación: " + notificacion);
    }
}