package model;

import combat.Enemigo;
import combat.Jugador;
import item.Arma;
import item.Pocion;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es el corazón del Model, gestionando el estado global del juego (Jugador y Enemigos).
 */
public class JuegoModel {	//Es un gestor. Su responsabilidad es conocer al jugador y a la lista de Enemigo para decirnos cuál está activo.
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private Enemigo enemigoActual;

    public JuegoModel() {
        this.enemigos = new ArrayList<>();
        // 1. Inicializar Inventario: Se crea aquí para pasárselo al Jugador
        InventarioModel inventarioInicial = new InventarioModel();
        inventarioInicial.AgregarItem(new Arma("Espada Corta", 1, "Arma Inicial", 15));
        inventarioInicial.AgregarItem(new Pocion("Poción Menor", 3, "Restaura salud", 20));
        
        // 2. Inicializar Jugador
        this.jugador = new Jugador("Heroe", 100, 1, inventarioInicial);
        
        // 3. Inicializar Enemigos
        enemigos.add(new Enemigo("Goblin", 100, 1, "Bestia", 8));
        enemigos.add(new Enemigo("Esqueleto", 75, 2, "Muerto Viviente", 12));
        
        this.enemigoActual = enemigos.get(0); // Empezar con el primer enemigo
    }

    // Getters
    public Jugador getJugador() { return jugador; }
    public Enemigo getEnemigoActual() { return enemigoActual; }

    // Métodos del Model
    public void cambiarEnemigo(int indice) {
        if (indice >= 0 && indice < enemigos.size()) {
            this.enemigoActual = enemigos.get(indice);
        }
    }
    
    // Método para obtener el Inventario del Jugador
    public InventarioModel getInventario() {
        return jugador.getInventario();
    }
}