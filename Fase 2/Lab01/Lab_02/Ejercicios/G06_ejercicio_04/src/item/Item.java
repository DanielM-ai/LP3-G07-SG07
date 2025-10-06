package item;

public class Item {
    private String nombre;
    private int cantidad;
    private String descripcion;
    
    // Constructor
    public Item(String nombre, int cantidad, String descripcion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters: ¡DEBEN SER PUBLIC!
    public String getNombre() { 
        return nombre; 
    }
    
    public int getCantidad() { 
        return cantidad; 
    }
    
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
    }
    
    //Creamos un método. Este será sobreescrito para devolver su tipo real (Arma o Pocion)
    public String getTipo() { 	//Devuelve un valor genérico por defecto
        return "Genérico"; 
    }
    
    
    public String getDescripcion() { 	 
        return descripcion; 
    }
    
    // Método UsarItem(): ¡DEBE SER PUBLIC!
    public String UsarItem() {
        if (this.cantidad > 0) {
            this.cantidad--;
            return "Usaste 1 " + this.nombre + ". Cantidad restante: " + this.cantidad;
        } else {
            return "No quedan " + this.nombre + "s para usar.";
        }
    }
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Cantidad: " + cantidad + " | Tipo: " + getTipo();
    }
}

//El Override de aquí arriba tiene su utilidad en InventarioView.java que lo utiliza cuando llama a:
//for (Item item : items) {
	// System.out.println(item); // Esto llama a item.toString()
// {