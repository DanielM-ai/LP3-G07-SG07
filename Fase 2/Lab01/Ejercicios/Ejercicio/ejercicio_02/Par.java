package ejercicio_02;

/**
 * Clase genérica Par, con dos parámetros de tipo: F (Primero) y S (Segundo).
 * Incluye el método esIgual para comparar contenido.
 */
public class Par<F, S> {
    
    // Declaración de los miembros genéricos
    private F primero;
    private S segundo;

    // Constructor
    public Par(F primero, S segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    // Métodos Getter
    public F getPrimero() {
        return primero;
    }

    public S getSegundo() {
        return segundo;
    }

    // Métodos Setter
    public void setPrimero(F primero) {
        this.primero = primero;
    }

    public void setSegundo(S segundo) {
        this.segundo = segundo;
    }

    // Método toString (Solicitado en el Ejercicio 1)
    @Override
    public String toString() {
        return "(Primero: " + primero + ", Segundo: " + segundo + ")";
    }

    // MÉTODO esIgual (Ejercicio 2)
    /**
     * Compara este par con otro par. 
     * Devuelve true si ambos tienen los mismos valores en el mismo orden.
     * @param otro El otro Par con el que se compara.
     * @return true si son iguales en contenido, false en caso contrario.
     */
    public boolean esIgual(Par<F, S> otro) {
        if (otro == null) {
            return false;
        }
        
        // La clave es usar el método equals() de los objetos F y S.
        // Esto asegura que la comparación sea por CONTENIDO y no por referencia.
        boolean primeroIgual = this.primero.equals(otro.getPrimero());
        boolean segundoIgual = this.segundo.equals(otro.getSegundo());

        return primeroIgual && segundoIgual;
    }
}
