package Ejercicio_04_sinDIP;

public class ImpresoraMultifuncional implements Imprimible {
	  @Override
	    public void imprimir() {
	        System.out.println("La impresora multifuncional está imprimiendo.");
	    }

	    @Override
	    public void escanear() {
	        System.out.println("La impresora multifuncional está escaneando.");
	    }
}
