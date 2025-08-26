package LP3;

public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private Cuenta cuenta; // Atributo que representa la parte de la composición

    // Constructor de Persona
    public Persona(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        // Se crea la instancia de Cuenta dentro de Persona, estableciendo la composición.
        // El número de cuenta se genera a partir del ID de la persona.
        this.cuenta = new Cuenta(id * 100);
    }

    // Métodos Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        // Retorna la información completa de la persona, incluyendo los datos de su cuenta
        return "Persona [ID=" + id + ", Nombre=" + nombre + ", Apellido=" + apellido + "]\n  -> " + cuenta.toString();
    }
}