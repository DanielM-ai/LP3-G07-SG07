package ejercicio_04;

public class Par<F, S> {
    
    private F primero;
    private S segundo;

    public Par(F primero, S segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    public F getPrimero() {
        return primero;
    }

    public S getSegundo() {
        return segundo;
    }

    public void setPrimero(F primero) {
        this.primero = primero;
    }

    public void setSegundo(S segundo) {
        this.segundo = segundo;
    }

    @Override
    public String toString() {
        return "(Primero: " + primero + ", Segundo: " + segundo + ")";
    }

    // Incluido del Ejercicio 2 para independencia
    public boolean esIgual(Par<F, S> otro) {
        if (otro == null) {
            return false;
        }
        return this.primero.equals(otro.getPrimero()) && 
               this.segundo.equals(otro.getSegundo());
    }
}