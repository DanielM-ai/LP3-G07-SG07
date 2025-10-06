package controller;

import combat.Enemigo;
import combat.Jugador;
import model.JuegoModel;
import view.CombateView;
import view.InventarioView;
import java.util.Random;

public class CombatController {
    
    private final JuegoModel modeloJuego;
    private final CombateView vistaCombate;
    private final InventarioView vistaInventario; 
    private final Random random = new Random();

    public CombatController(JuegoModel modeloJuego, CombateView vistaCombate, InventarioView vistaInventario) {
        this.modeloJuego = modeloJuego;
        this.vistaCombate = vistaCombate;
        this.vistaInventario = vistaInventario;
    }

    // --- Lógica del Combate (Controlador) ---

    public void IniciarCombate() {
        Jugador jugador = modeloJuego.getJugador();
        Enemigo enemigo = modeloJuego.getEnemigoActual();
        boolean combateActivo = true;

        vistaCombate.mostrarMensaje("¡Ha aparecido un " + enemigo.getNombre() + "! Inicia el combate.");

        while (combateActivo && jugador.estaVivo() && enemigo.estaVivo()) {
            
            // 1. Mostrar Estado
            vistaCombate.mostrarEstadoCombate(jugador, enemigo);
            
            // 2. Turno del Jugador
            // La variable 'combateActivo' se actualiza si el jugador huye (Opción 3)
            combateActivo = turnoJugador(jugador, enemigo);

            // DETENEMOS EL FLUJO: Si el jugador huyó o el enemigo murió, salimos del bucle.
            if (!combateActivo || !enemigo.estaVivo()) break;
            
            // 3. Turno del Enemigo: SOLO si el jugador sigue en combate y el enemigo sigue vivo.
            turnoEnemigo(jugador, enemigo);
        }

        // Fin del Combate
        if (!jugador.estaVivo()) {
            vistaCombate.mostrarMensaje("¡Has sido derrotado! Fin del juego.");
        } else if (!enemigo.estaVivo()) {
            vistaCombate.mostrarMensaje("¡Has derrotado a " + enemigo.getNombre() + "!");
        } else {
            vistaCombate.mostrarMensaje("El combate ha terminado porque huiste.");
        }
    }
    
    private boolean turnoJugador(Jugador jugador, Enemigo enemigo) {
        vistaCombate.mostrarMenuCombate();
        int opcion = vistaCombate.leerOpcionMenu();
        
        switch (opcion) {
            case 1: // Atacar
                int dano = jugador.Atacar();
                enemigo.RecibirDanio(dano);
                vistaCombate.mostrarMensaje(jugador.getNombre() + " ataca con " + jugador.getArmaEquipada().getNombre() + " e inflige " + dano + " de daño.");
                return true;
            case 2: // Usar Objeto
                vistaInventario.MostrarInventario(jugador.getInventario().ObtenerItems());
                String nombreObjeto = vistaCombate.leerNombreObjeto();
                String resultadoUso = jugador.UsarObjeto(nombreObjeto);
                vistaCombate.mostrarMensaje(resultadoUso);
                
                // El jugador usa un turno para esto, ahora es el turno del enemigo
                return true; 
            case 3: // Huir
                vistaCombate.mostrarMensaje(jugador.getNombre() + " huye del combate.");
                return false; 
            default:
                vistaCombate.mostrarMensaje("Opción no válida. Pierdes el turno.");
                return true;
        }
    }
    
    private void turnoEnemigo(Jugador jugador, Enemigo enemigo) {
        if (!enemigo.estaVivo()) return;
        
        // Simulación de IA básica del enemigo
        int accion = random.nextInt(3); 

        if (accion == 0 || accion == 1) { // 2/3 de probabilidad de atacar
            int dano = enemigo.Atacar();
            jugador.RecibirDanio(dano);
            vistaCombate.mostrarMensaje(enemigo.getNombre() + " ataca e inflige " + dano + " de daño a " + jugador.getNombre() + ".");
        } else { // 1/3 de probabilidad de defender/no hacer nada
            vistaCombate.mostrarMensaje(enemigo.getNombre() + " realiza una acción pasiva.");
        }
    }
}