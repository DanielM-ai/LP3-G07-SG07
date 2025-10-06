package combat;

import item.Arma;
import item.Item;
import item.Pocion;
import model.InventarioModel;

public class Jugador {
    private String nombre;
    private int salud;
    private final int nivel;
    private final InventarioModel inventario;
    private Arma armaEquipada;
    
    private final int SALUD_MAXIMA;
    private static final int ATAQUE_BASE = 5;

    public Jugador(String nombre, int saludInicial, int nivel, InventarioModel inventario) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.inventario = inventario;
        // Asignamos el valor inicial de salud como el valor máximo (para simplificar)
        this.SALUD_MAXIMA = saludInicial; 
        this.salud = saludInicial;
        
        this.armaEquipada = new Arma("Puño Desnudo", 99, "No hay arma equipada.", ATAQUE_BASE);
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getNivel() { return nivel; }
    public Arma getArmaEquipada() { return armaEquipada; }
    public InventarioModel getInventario() { return inventario; }
    public int getSaludMaxima() { return SALUD_MAXIMA; }

    // Atacar (sin cambios)
    public int Atacar() {
        int danoTotal = ATAQUE_BASE + (nivel * 2) + armaEquipada.getDano();
        return danoTotal;
    }

    // --- MÉTODO CORREGIDO: UsarObjeto ---
    public String UsarObjeto(String nombreObjeto) {
        Item item = inventario.BuscarItem(nombreObjeto);
        if (item == null) {
            return "ERROR: El objeto '" + nombreObjeto + "' no está en el inventario.";
        }
        
        // 1. Llamar al método UsarItem() del objeto (en Arma, solo devuelve mensaje)
        String resultadoUso = item.UsarItem(); 
        
        if (item instanceof Arma) {
            this.armaEquipada = (Arma) item;
            
            // ¡LÓGICA DE CONSUMO APLICADA!
            // Si el arma es equipada, la eliminamos del inventario para que 
            // no pueda ser usada/equipada de nuevo.
            inventario.EliminarItem(nombreObjeto); 
            
            return "ARMA EQUIPADA: " + item.getNombre();

        } else if (item instanceof Pocion) {
            Pocion pocion = (Pocion) item;
            
            // Curar al jugador
            Curarse(pocion.getCuracion()); 
            
            // La poción sí se consume (reduce su cantidad en el InventarioModel)
            inventario.usarItemPorNombre(nombreObjeto); 
            
            return "SALUD RECUPERADA. Salud actual: " + salud;
        }
        
        return resultadoUso;
    }

    // Recibir Daño (sin cambios)
    public int RecibirDanio(int danio) {
        this.salud -= danio;
        if (this.salud < 0) this.salud = 0;
        return this.salud;
    }
    
    // Curarse con límite (sin cambios)
    public void Curarse(int cantidad) {
        this.salud += cantidad;
        if (this.salud > SALUD_MAXIMA) {
            this.salud = SALUD_MAXIMA;
        }
    }
    
    public boolean estaVivo() {
        return salud > 0;
    }
}