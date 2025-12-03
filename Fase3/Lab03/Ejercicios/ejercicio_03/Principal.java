// Archivo: ejercicio_03_espanol/Principal.java
package ejercicio_03;

public class Principal {

    public static void main(String[] args) {
        // 1. Crear los Receptores (los trabajadores)
        ReceptorTareas mario = new ReceptorTareas("Mario");
        ReceptorTareas daniel = new ReceptorTareas("Daniel");
        ReceptorTareas mauriceu = new ReceptorTareas("Mauriceu");

        // 2. Crear los Comandos (las solicitudes encapsuladas)
        ComandoTarea cmd1_mario = new ComandoIniciarTarea(mario, "Diseño de la Interfaz");
        ComandoTarea cmd2_daniel = new ComandoIniciarTarea(daniel, "Implementación del Backend");
        ComandoTarea cmd3_mario = new ComandoFinalizarTarea(mario, "Diseño de la Interfaz");
        ComandoTarea cmd4_mauriceu = new ComandoFinalizarTarea(mauriceu, "Testing Final");


        // 3. Crear el Invocador (el programador)
        ProgramadorTareas programador = new ProgramadorTareas();

        // 4. Cargar los comandos al Invocador (Command en acción: almacenamiento en cola)
        programador.agregarComando(cmd1_mario);
        programador.agregarComando(cmd2_daniel);
        programador.agregarComando(cmd3_mario);
        programador.agregarComando(cmd4_mauriceu);
        
        // 5. El Invocador ejecuta las tareas secuencialmente
        programador.ejecutarTodosLosComandos();
    }
}