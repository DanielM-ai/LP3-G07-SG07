package item;

public class Arma extends Item {
    private int dano;

    public Arma(String nombre, int cantidad, String descripcion, int dano) {
        super(nombre, cantidad, descripcion);
        this.dano = dano;
    }

    @Override
    public String getTipo() {
        return "Arma";
    }

    @Override
    public String UsarItem() {
        return "Arma " + getNombre() + " equipada. Daño: " + dano;
    }
}