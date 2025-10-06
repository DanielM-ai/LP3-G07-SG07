package Actividades;

public class Pedido {
    private String nombrePlato;
    private String tipo; // Nuevo atributo

    public Pedido(String nombrePlato, String tipo) {
        this.nombrePlato = nombrePlato;
        this.tipo = tipo;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public String getTipo() {
        return tipo;
    }
    
    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}