package ejercicioGestionU;

import java.util.*;
import java.util.stream.Collectors;

// "Fachada" del sistema: orquesta estudiantes, profesores y cursos
public class SistemaGestion {
    // Variables de instancia (estado del sistema)
    private final Map<String, Estudiante> estudiantes = new HashMap<>();
    private final Map<String, Profesor> profesores = new HashMap<>();
    private final Map<String, Curso> cursos = new HashMap<>();

    // Métodos de instancia
    public void registrarEstudiante(Estudiante e) { estudiantes.put(e.getId(), e); }
    public void registrarProfesor(Profesor p) { profesores.put(p.getId(), p); }

    public void crearCurso(Curso c) { cursos.put(c.getCodigo(), c); }

    public boolean inscribir(String idEstudiante, String codigoCurso) {
        Estudiante e = estudiantes.get(idEstudiante);
        Curso c = cursos.get(codigoCurso);
        if (e == null || c == null) return false;
        boolean ok = c.inscribir(e);
        if (ok) {
            c.getProfesor().notificar("Nuevo estudiante inscrito en " + c.getCodigo() + ": " + e.getNombre());
        }
        return ok;
    }

    public List<Curso> cursosDisponibles() {
        return cursos.values().stream()
                .filter(c -> c.getCuposDisponibles() > 0)
                .sorted(Comparator.comparing(Curso::getCodigo))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> cantidadMatriculadosPorCurso() {
        return cursos.values().stream()
                .collect(Collectors.toMap(Curso::getCodigo, c -> c.getInscripciones().size()));
    }

    // Métodos de clase/instancia demostrativos
    public int totalEstudiantes() { return estudiantes.size(); }
    public int totalProfesores() { return profesores.size(); }
    public int totalCursos() { return cursos.size(); }

    @Override
    public String toString() {
        return "SistemaGestion{estudiantes=" + estudiantes.size() +
                ", profesores=" + profesores.size() +
                ", cursos=" + cursos.size() + "}";
    }
}