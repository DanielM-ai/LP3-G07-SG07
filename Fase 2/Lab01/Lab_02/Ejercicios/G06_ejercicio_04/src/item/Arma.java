package item;

// Extiende de la clase base Item
public class Arma extends Item {
    private int dano;

    public Arma(String nombre, int cantidad, String descripcion, int dano) {
        // Un arma se inicializa con su cantidad (ej. 1)
        super(nombre, cantidad, descripcion); 
        this.dano = dano;
    }

    // Getter para el Jugador
    public int getDano() {
        return dano;
    }

    @Override
    public String getTipo() {
        return "Arma";
    }

    // SOBREESCRITURA CRÍTICA: 
    // Un arma NO debe reducir su cantidad al ser equipada. 
    // El método solo confirma la acción.
    @Override
    public String UsarItem() {
        // No llamamos a super.UsarItem() ni reducimos la cantidad.
        return "El " + getNombre() + " fue equipado."; 
    }
}