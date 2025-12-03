// Archivo: ejercicio_01/SistemaNotificaciones.java
package ejercicio_01;

import java.util.ArrayList;
import java.util.List;

// SistemaNotificaciones actúa como el Sujeto (Subject) o Observable
public class SistemaNotificaciones {
    
    // Mantiene una lista de los observadores suscritos.
    private List<Observer> observadores = new ArrayList<>();
    
    // Método para suscribir (attach) a un observador dinámicamente.
    public void suscribir(Observer observer) {
        if (!observadores.contains(observer)) {
            observadores.add(observer);
            System.out.println("➕ " + ((Usuario)observer).getNombre() + " se ha suscrito al sistema.");
        }
    }
    
    // Método para desuscribir (detach) a un observador dinámicamente.
    public void desuscribir(Observer observer) {
        observadores.remove(observer);
        System.out.println("➖ " + ((Usuario)observer).getNombre() + " se ha desuscrito del sistema.");
    }
    
    // Método clave: notifica a TODOS los observadores suscritos.
    public void notificar(Notificacion notificacion) {
        String mensaje = notificacion.toString();
        System.out.println("\n📣 NUEVO EVENTO PUBLICADO: " + mensaje);
        
        // Itera sobre la lista y llama al método update de cada observador.
        for (Observer observer : observadores) {
            observer.update(mensaje);
        }
    }
}