package Actividades;
public class Pedido {
    // Enum para un manejo de estados más seguro y claro
    public enum Estado {
        PENDIENTE,
        COMPLETADO,
        ELIMINADO
    }

    private String nombrePlato;
    private String tipo;
    private Estado estado;

    public Pedido(String nombrePlato, String tipo) {
        this.nombrePlato = nombrePlato;
        this.tipo = tipo;
        this.estado = Estado.PENDIENTE; // Estado por defecto al crear un pedido
    }

    // Getters y Setters
    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}