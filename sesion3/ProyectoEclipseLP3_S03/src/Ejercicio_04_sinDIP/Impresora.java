package Ejercicio_04_sinDIP;

public class Impresora implements Imprimible {
	  @Override
	    public void imprimir() {
	        System.out.println("La impresora está imprimiendo.");
	    }

	    @Override
	    public void escanear() {
	        // Las impresoras básicas no pueden escanear, violando ISP
	        throw new UnsupportedOperationException("La impresora no puede escanear.");
	    }
}
