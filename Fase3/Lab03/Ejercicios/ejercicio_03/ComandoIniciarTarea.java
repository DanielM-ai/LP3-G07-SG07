// Archivo: ejercicio_03_espanol/ComandoIniciarTarea.java
package ejercicio_03;

// 3a. Comando para iniciar una tarea
public class ComandoIniciarTarea implements ComandoTarea {
    private ReceptorTareas receptor;
    private String nombreTarea;

    public ComandoIniciarTarea(ReceptorTareas receptor, String nombreTarea) {
        this.receptor = receptor;
        this.nombreTarea = nombreTarea;
    }

    // Al ejecutar, delega la acción al Receptor
    @Override
    public void ejecutar() {
        receptor.iniciarTarea(nombreTarea);
    }
}