package ejercicioGestionU;

import java.time.LocalDate;

//Composición: la Inscripción "vive dentro" de un Curso
public class Inscripcion {
 // Atributos de instancia
	private final Estudiante estudiante;
	private final LocalDate fecha;
	private Double nota; // puede ser null si aún no hay nota

 public Inscripcion(Estudiante estudiante) {
     this.estudiante = estudiante;
     this.fecha = LocalDate.now();
 }

 public Estudiante getEstudiante() { return estudiante; }
 public LocalDate getFecha() { return fecha; }
 public Double getNota() { return nota; }
 public void setNota(Double nota) { this.nota = nota; }

 @Override
 public String toString() {
     return "Inscripcion{" +
             "estudiante=" + estudiante.getNombre() +
             ", fecha=" + fecha +
             (nota != null ? ", nota=" + nota : "") +
             '}';
 }
}
