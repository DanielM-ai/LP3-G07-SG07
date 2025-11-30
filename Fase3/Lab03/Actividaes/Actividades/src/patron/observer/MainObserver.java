package patron.observer;

// 1. LOS IMPORTS VAN AQUÍ (Siempre arriba)
import java.util.ArrayList;
import java.util.List;

// 2. INTERFACES Y CLASES (Fuera de la clase principal)

interface Observer {
    void actualizar(String mensaje);
}

interface Subject {
    void suscribir(Observer observer);
    void desuscribir(Observer observer);
    void notificarObservadores(String mensaje);
}

class Usuario implements Observer {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Usuario [" + nombre + "] ha recibido la notificación: " + mensaje);
    }
}

class SistemaNotificaciones implements Subject {
    private List<Observer> usuarios;

    public SistemaNotificaciones() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void suscribir(Observer observer) {
        usuarios.add(observer);
        System.out.println("Nuevo usuario suscrito.");
    }

    @Override
    public void desuscribir(Observer observer) {
        usuarios.remove(observer);
        System.out.println("Usuario dado de baja.");
    }

    @Override
    public void notificarObservadores(String mensaje) {
        for (Observer usuario : usuarios) {
            usuario.actualizar(mensaje);
        }
    }
    
    public void lanzarPromocion(String promocion) {
        System.out.println("\n--- Lanzando nueva promoción ---");
        notificarObservadores(promocion);
    }
}

// 3. CLASE PRINCIPAL (Debe coincidir con el nombre del archivo MainObserver.java)
public class MainObserver {
    public static void main(String[] args) {
        SistemaNotificaciones sistema = new SistemaNotificaciones();

        Usuario u1 = new Usuario("Juan Perez");
        Usuario u2 = new Usuario("Maria Lopez");
        Usuario u3 = new Usuario("Carlos Ruiz");

        // Suscripciones
        sistema.suscribir(u1);
        sistema.suscribir(u2);

        // Evento 1
        sistema.lanzarPromocion("¡50% de descuento en Laptops!");

        // Modificar suscriptores dinámicamente
        sistema.suscribir(u3);
        sistema.desuscribir(u1);

        // Evento 2
        sistema.lanzarPromocion("Nueva colección de invierno disponible.");
    }
}