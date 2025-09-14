package registro_estudiantes;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Gestiona una colección de objetos Estudiante.
 * Permite agregar y buscar estudiantes, lanzando excepciones para casos de error.
 */
public class RegistroEstudiantes {
    private ArrayList<Estudiante> listaEstudiantes;

    public RegistroEstudiantes() {
        this.listaEstudiantes = new ArrayList<>();
    }

    /**
     * Agrega un estudiante al registro.
     * @param estudiante El objeto estudiante a agregar.
     * @throws IllegalArgumentException si el objeto estudiante proporcionado es nulo.
     */
    public void agregarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El objeto estudiante no puede ser nulo.");
        }
        listaEstudiantes.add(estudiante);
        System.out.println("Estudiante agregado: " + estudiante.getNombre());
    }

    /**
     * Busca un estudiante por su nombre (ignorando mayúsculas y minúsculas).
     * @param nombre El nombre del estudiante a buscar.
     * @return El objeto Estudiante si se encuentra.
     * @throws IllegalArgumentException si el nombre de búsqueda es nulo o vacío.
     * @throws NoSuchElementException si el estudiante no se encuentra en el registro.
     */
    public Estudiante buscarEstudiante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede ser nulo o vacío.");
        }
        for (Estudiante est : listaEstudiantes) {
            if (est.getNombre().equalsIgnoreCase(nombre)) {
                return est;
            }
        }
        // Si el bucle termina y no se encontró, se lanza la excepción.
        throw new NoSuchElementException("No se encontró al estudiante con el nombre: " + nombre);
    }
    
    /**
     * Muestra todos los estudiantes registrados en la consola.
     */
    public void mostrarEstudiantes() {
        System.out.println("\n--- Lista de Estudiantes Registrados ---");
        if (listaEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (Estudiante est : listaEstudiantes) {
            System.out.println(est);
        }
        System.out.println("----------------------------------------");
    }
}