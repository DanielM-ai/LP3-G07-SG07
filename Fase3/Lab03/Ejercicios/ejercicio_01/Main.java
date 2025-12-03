	// Archivo: ejercicio_01/Main.java
package ejercicio_01;

public class Main {

    public static void main(String[] args) {
        // 1. Crear el Sujeto (Sistema de Notificaciones)
        SistemaNotificaciones plataforma = new SistemaNotificaciones();

        // 2. Crear los Observadores (Usuarios)
        Usuario juan = new Usuario("Daniel");
        Usuario ana = new Usuario("Mauriceu");
        Usuario pedro = new Usuario("Mario");

        // 3. Suscribir usuarios dinámicamente
        System.out.println("\n--- FASE 1: Suscripción Inicial ---");
        plataforma.suscribir(juan);
        plataforma.suscribir(ana);

        // 4. Publicar la primera notificación (solo Juan y Ana la reciben)
        plataforma.notificar(new Notificacion("PROMOCIÓN", "10% de descuento en todos los productos!"));
        
        // 5. Cambio dinámico: Pedro se suscribe y Ana se desuscribe
        System.out.println("\n--- FASE 2: Cambios de Suscripción ---");
        plataforma.suscribir(pedro);
        plataforma.desuscribir(ana);

        // 6. Publicar la segunda notificación (Juan y Pedro la reciben)
        plataforma.notificar(new Notificacion("ACTUALIZACIÓN", "La política de envío ha cambiado."));
    }
}