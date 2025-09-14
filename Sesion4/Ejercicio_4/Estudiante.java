package registro_estudiantes;

/**
 * Representa a un estudiante con un nombre y un ID.
 * Lanza una excepción si se intenta crear con un nombre inválido.
 */
public class Estudiante {
    private String nombre;
    private String id;

    /**
     * Constructor para la clase Estudiante.
     * @param nombre El nombre del estudiante. No puede ser nulo o vacío.
     * @param id El identificador único del estudiante.
     * @throws IllegalArgumentException si el nombre es nulo o está en blanco.
     */
    public Estudiante(String nombre, String id) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Estudiante [ID=" + id + ", Nombre=" + nombre + "]";
    }
}