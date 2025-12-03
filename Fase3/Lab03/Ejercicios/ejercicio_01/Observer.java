// Archivo: ejercicio_01/Observer.java
package ejercicio_01;

// La interfaz Observer define un método para que los observadores
// reciban la actualización del Sujeto.
public interface Observer {
    // Método llamado por el Sujeto para notificar un evento/mensaje
    void update(String notificacion);
}




