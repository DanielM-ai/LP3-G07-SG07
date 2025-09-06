package Ejercicio_04_conDIP;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear instancias
        Imprimible impresora = new Impresora();
        Imprimible impresoraMultiImpresion = new ImpresoraMultifuncional();
        Escaneable impresoraMultiEscaneo = new ImpresoraMultifuncional();

        // Listas de dispositivos
        List<Imprimible> dispositivosImpresion = Arrays.asList(impresora, impresoraMultiImpresion);
        List<Escaneable> dispositivosEscaneo = Arrays.asList(impresoraMultiEscaneo);

        // Crear oficina
        Oficina oficina = new Oficina(dispositivosImpresion, dispositivosEscaneo);

        // Ejecutar tareas
        System.out.println("Tareas de impresión:");
        oficina.realizarTareaImpresion();
        System.out.println("\nTareas de escaneo:");
        oficina.realizarTareaEscaneo();
    }
}