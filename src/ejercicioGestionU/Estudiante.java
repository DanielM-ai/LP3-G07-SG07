package ejercicioGestionU;

public class Estudiante extends Persona {
    // Constante de clase
    public static final int MAX_CURSOS_POR_ESTUDIANTE = 6;

    // Atributos de instancia
    private String carrera;
    private int ciclo;

    // Atributo de clase
    private static int totalEstudiantes = 0;

    public Estudiante(String id, String nombre, String email, String carrera, int ciclo) {
        super(id, nombre, email);
        this.carrera = carrera;
        this.ciclo = ciclo;
        totalEstudiantes++;
    }

    // Polimorfismo (sobre Persona)
    @Override
    public String getRol() { return "Estudiante"; }

    // Implementación interfaz Notificable (polimórfica)
    @Override
    public void notificar(String mensaje) {
        System.out.println("[Estudiante " + getNombre() + "] " + mensaje);
    }

    // Getters/Setters
    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
    public int getCiclo() { return ciclo; }
    public void setCiclo(int ciclo) { this.ciclo = ciclo; }

    public static int getTotalEstudiantes() { return totalEstudiantes; }

    @Override
    public String toString() {
        return super.toString() + " (carrera='" + carrera + "', ciclo=" + ciclo + ")";
    }
}