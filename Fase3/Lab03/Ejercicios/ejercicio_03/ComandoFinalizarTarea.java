// Archivo: ejercicio_03_espanol/ComandoFinalizarTarea.java
package ejercicio_03;

// 3b. Comando para finalizar una tarea
public class ComandoFinalizarTarea implements ComandoTarea {
    private ReceptorTareas receptor;
    private String nombreTarea;

    public ComandoFinalizarTarea(ReceptorTareas receptor, String nombreTarea) {
        this.receptor = receptor;
        this.nombreTarea = nombreTarea;
    }

    // Al ejecutar, delega la acción al Receptor
    @Override
    public void ejecutar() {
        receptor.finalizarTarea(nombreTarea);
    }
}