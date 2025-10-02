package ejercicio_04;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase genérica Contenedor: almacena múltiples objetos de tipo Par<F, S>.
 */
public class Contenedor<F, S> {
    
    // Almacena objetos Par, manteniendo la coherencia de tipos (F y S).
    private List<Par<F, S>> listaDePares;

    public Contenedor() {
        this.listaDePares = new ArrayList<>();
    }

    // 1. agregarPar(F primero, S segundo)
    /**
     * Permite añadir un nuevo par al contenedor creando un objeto Par<F, S>.
     */
    public void agregarPar(F primero, S segundo) {
        Par<F, S> nuevoPar = new Par<>(primero, segundo);
        this.listaDePares.add(nuevoPar);
    }

    // 2. obtenerPar(int indice)
    /**
     * Devuelve el par en la posición especificada, con seguridad de tipos.
     */
    public Par<F, S> obtenerPar(int indice) {
        if (indice >= 0 && indice < listaDePares.size()) {
            return listaDePares.get(indice);
        }
        throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
    }

    // 3. obtenerTodosLosPares()
    /**
     * Devuelve la lista completa de pares.
     */
    public List<Par<F, S>> obtenerTodosLosPares() {
        return this.listaDePares;
    }

    // 4. mostrarPares()
    /**
     * Imprime todos los pares almacenados en el contenedor.
     */
    public void mostrarPares() {
        System.out.println("\n--- Contenido del Contenedor (" + listaDePares.size() + " pares) ---");
        if (listaDePares.isEmpty()) {
            System.out.println("El contenedor está vacío.");
            return;
        }
        for (int i = 0; i < listaDePares.size(); i++) {
            // Usamos el toString() de Par
            System.out.println("Par [" + i + "]: " + listaDePares.get(i));
        }
        System.out.println("-----------------------------------------------------");
    }
}