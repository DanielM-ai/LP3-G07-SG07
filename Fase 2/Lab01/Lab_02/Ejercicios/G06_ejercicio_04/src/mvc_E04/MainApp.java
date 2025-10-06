package mvc_E04;

import controller.CombatController;
import model.JuegoModel;
import view.CombateView;
import view.InventarioView; // Necesaria para el CombatController

public class MainApp {
    public static void main(String[] args) {
        
        // 1. CREAR EL MODELO CENTRAL
        JuegoModel modeloJuego = new JuegoModel();
        
        // 2. CREAR LAS VISTAS
        CombateView vistaCombate = new CombateView();
        InventarioView vistaInventario = new InventarioView(); // Reutilizamos la vista de inventario
        
        // 3. CREAR EL CONTROLADOR DE COMBATE (y CONECTAR M-V)
        // El CombatController toma la lógica de juego y ambas vistas.
        CombatController controladorCombate = new CombatController(
            modeloJuego, 
            vistaCombate, 
            vistaInventario
        );
        
        // 4. INICIAR EL JUEGO con el primer enemigo
        controladorCombate.IniciarCombate();
    }
}