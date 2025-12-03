// Archivo: ejercicio_03_espanol/ProgramadorTareas.java
package ejercicio_03;

import java.util.ArrayList;
import java.util.List;

// 4. El Invocador, que gestiona y ejecuta los comandos
public class ProgramadorTareas {
    // Para demostrar el almacenamiento o la cola de comandos
    private List<ComandoTarea> colaComandos = new ArrayList<>();
    
    public void agregarComando(ComandoTarea comando) {
        colaComandos.add(comando);
    }
    
    // El invocador no sabe lo que hace el comando, solo sabe que debe ejecutarse
    public void ejecutarSiguienteComando() {
        if (!colaComandos.isEmpty()) {
            ComandoTarea comando = colaComandos.remove(0); // Ejecuta el primero en la cola
            System.out.println("\n[Programador] Ejecutando comando de la cola...");
            comando.ejecutar(); 
        } else {
            System.out.println("\n[Programador] La cola de comandos está vacía.");
        }
    }

    public void ejecutarTodosLosComandos() {
        System.out.println("\n--- Ejecutando todas las tareas en la cola ---");
        while (!colaComandos.isEmpty()) {
            ejecutarSiguienteComando();
        }
        System.out.println("----------------------------------------------");
    }
}