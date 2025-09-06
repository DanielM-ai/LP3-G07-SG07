package Ejercicio_04_sinDIP;

public class Oficina {
    // Depende directamente de clases concretas, violando DIP
    private Impresora impresora;
    private ImpresoraMultifuncional impresoraMultifuncional;

    public Oficina(Impresora impresora, ImpresoraMultifuncional impresoraMultifuncional) {
        this.impresora = impresora;
        this.impresoraMultifuncional = impresoraMultifuncional;
    }

    public void realizarTareaImpresion() {
        impresora.imprimir();
        impresoraMultifuncional.imprimir();
    }

    public void realizarTareaEscaneo() {
        // Necesita manejar excepciones debido a la violación de ISP
        try {
            impresora.escanear();
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        impresoraMultifuncional.escanear();
    }
}
