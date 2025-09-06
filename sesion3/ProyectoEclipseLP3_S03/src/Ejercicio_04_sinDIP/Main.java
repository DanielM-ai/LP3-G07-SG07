package Ejercicio_04_sinDIP;

public class Main {
    public static void main(String[] args) {
        Impresora impresora = new Impresora();
        ImpresoraMultifuncional impresoraMulti = new ImpresoraMultifuncional();

        Oficina oficina = new Oficina(impresora, impresoraMulti);

        oficina.realizarTareaImpresion();
        oficina.realizarTareaEscaneo();
    }
}