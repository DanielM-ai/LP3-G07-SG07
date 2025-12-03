// Archivo: ejercicio_03_espanol/ReceptorTareas.java
package ejercicio_03;

// 2. El Receptor, el objeto que realiza el trabajo real
public class ReceptorTareas {
    private String nombreTrabajador;

    public ReceptorTareas(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    // Operación real: Iniciar una tarea
    public void iniciarTarea(String nombreTarea) {
        System.out.println("✅ " + nombreTrabajador + " ha INICIADO la tarea: '" + nombreTarea + "'.");
    }

    // Operación real: Finalizar una tarea
    public void finalizarTarea(String nombreTarea) {
        System.out.println("🛑 " + nombreTrabajador + " ha FINALIZADO la tarea: '" + nombreTarea + "'.");
    }
}