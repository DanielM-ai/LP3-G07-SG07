package item;

public class Pocion extends Item {
    private int curacion;

    public Pocion(String nombre, int cantidad, String descripcion, int curacion) {
        super(nombre, cantidad, descripcion);
        this.curacion = curacion;
    }

    @Override
    public String getTipo() {
        return "Poción";
    }

    @Override
    public String UsarItem() {
        String resultado = super.UsarItem(); 
        if (getCantidad() >= 0) {
            return resultado + " | ¡Vida restaurada en " + curacion + " puntos!";
        } else {
             return resultado; 
        }
    }
}