package ejercicioGestionU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Curso agrega un Profesor (agregación) y compone Inscripciones (composición)
public class Curso {
    // Constantes de clase
    public static final int CAPACIDAD_MAXIMA = 40;

    // Atributos de clase
    private static int totalCursos = 0;

    // Atributos de instancia
    private final String codigo;
    private final String nombre;
    private final CategoriaCurso categoria;

    // Agregación: el Curso "tiene un" Profesor, pero ambos pueden existir por separado
    private Profesor profesor;

    // Composición: Inscripciones pertenecen al Curso
    private final List<Inscripcion> inscripciones = new ArrayList<>();

    public Curso(String codigo, String nombre, CategoriaCurso categoria, Profesor profesor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.profesor = profesor;
        totalCursos++;
    }

    // Métodos de instancia
    public boolean inscribir(Estudiante e) {
        if (e == null) return false;
        if (inscripciones.size() >= CAPACIDAD_MAXIMA) return false;
        // (regla simple) evitar duplicados
        boolean yaInscrito = inscripciones.stream().anyMatch(i -> i.getEstudiante().getId().equals(e.getId()));
        if (yaInscrito) return false;

        inscripciones.add(new Inscripcion(e));
        e.notificar("Has sido inscrito en el curso " + codigo + " - " + nombre);
        return true;
    }

    public int getCuposDisponibles() {
        return CAPACIDAD_MAXIMA - inscripciones.size();
    }

    public List<Inscripcion> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }

    // Getters/Setters (instancia)
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public CategoriaCurso getCategoria() { return categoria; }
    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }

    // Métodos de clase (estáticos)
    public static int getTotalCursos() { return totalCursos; }

    @Override
    public String toString() {
        return "Curso{codigo='" + codigo + "', nombre='" + nombre + "', categoria=" + categoria +
                ", profesor=" + (profesor != null ? profesor.getNombre() : "N/D") +
                ", matriculados=" + inscripciones.size() +
                ", cuposDisp=" + getCuposDisponibles() + "}";
    }
}