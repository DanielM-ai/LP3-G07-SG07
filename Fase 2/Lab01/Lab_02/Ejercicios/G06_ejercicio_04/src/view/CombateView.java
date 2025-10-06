package view;

import combat.Jugador;
import combat.Enemigo;
import java.util.Scanner;

public class CombateView {
    
    private final Scanner scanner = new Scanner(System.in);

    // --- Output ---

    public void mostrarEstadoCombate(Jugador jugador, Enemigo enemigo) {
        System.out.println("\n==================================");
        System.out.println("          ESTADO DE COMBATE       ");
        System.out.println("==================================");
        System.out.printf("%-10s %-5s %-5s %s%n", "ENTIDAD", "SALUD", "NIVEL", "ARMA");
        System.out.println("----------------------------------");
        System.out.printf("%-10s %-5d %-5d %s%n", 
            jugador.getNombre(), 
            jugador.getSalud(), 
            jugador.getNivel(), 
            jugador.getArmaEquipada().getNombre());
        System.out.printf("%-10s %-5d %-5d %s%n", 
            enemigo.getNombre(), 
            enemigo.getSalud(), 
            enemigo.getNivel(), 
            enemigo.getTipo());
        System.out.println("----------------------------------");
    }
    
    public void mostrarMenuCombate() {
        System.out.println("\n--- ACCIONES ---");
        System.out.println("1. Atacar");
        System.out.println("2. Usar Objeto (Inventario)");
        System.out.println("3. Huir (Terminar Combate)");
        System.out.print("Seleccione su acción: ");
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println("[INFO] " + mensaje);
    }
    
    // --- Input ---
    
    public int leerOpcionMenu() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public String leerNombreObjeto() {
        System.out.print("Nombre del objeto a usar (ej: Poción Menor): ");
        return scanner.nextLine().trim();
    }
}