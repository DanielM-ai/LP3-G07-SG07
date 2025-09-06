package Ejercicio_04_conDIP;

// Impresora multifuncional que implementa ambas interfaces
public class ImpresoraMultifuncional implements Imprimible, Escaneable {
    @Override
    public void imprimir() {
        System.out.println("La impresora multifuncional está imprimiendo.");
    }

    @Override
    public void escanear() {
        System.out.println("La impresora multifuncional está escaneando.");
    }
}