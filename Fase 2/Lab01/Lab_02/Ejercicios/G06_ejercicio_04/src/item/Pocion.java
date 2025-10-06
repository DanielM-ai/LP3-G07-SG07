package item;

// Extiende de la clase base Item
public class Pocion extends Item {
    private int curacion;

    public Pocion(String nombre, int cantidad, String descripcion, int curacion) {
        super(nombre, cantidad, descripcion);
        this.curacion = curacion;
    }

    // Getter necesario para que el Jugador sepa cuánto curarse
    public int getCuracion() {
        return curacion;
    }

    @Override
    public String getTipo() {
        return "Poción";
    }

    @Override
    public String UsarItem() {
        // 1. Llama al método del padre para reducir la cantidad
        String resultado = super.UsarItem(); 
        
        // 2. Agrega el efecto de curación al mensaje
        if (getCantidad() >= 0) {
            // El mensaje de curación se añade aquí, pero la salud real la cura el Jugador
            return resultado + " | ¡Efecto de Curación aplicado!";
        } else {
             return resultado; 
        }
    }
}