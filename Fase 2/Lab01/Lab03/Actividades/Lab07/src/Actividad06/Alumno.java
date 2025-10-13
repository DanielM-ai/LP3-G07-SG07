package Actividad06;

public class Alumno extends Persona {
    private Fecha fechaMatricula;

    public Alumno() {
        super();
        this.fechaMatricula = new Fecha();
    }

    public Alumno(String nif, String nombre, int edad, Fecha fechaMatricula) {
        super(nif, nombre, edad);
        this.fechaMatricula = fechaMatricula;
    }

    public Fecha getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Fecha fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }
}