package Ejercicio_04_conDIP;

// Impresora básica que solo implementa Imprimible
public class Impresora implements Imprimible {
    @Override
    public void imprimir() {
        System.out.println("La impresora está imprimiendo.");
    }
}