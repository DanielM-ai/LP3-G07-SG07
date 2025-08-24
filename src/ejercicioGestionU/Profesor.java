package ejercicioGestionU;

public class Profesor extends Persona {
    // Atributos de instancia
    private String departamento;
    private int cargaSemanalHoras;

    // Atributo de clase
    private static int totalProfesores = 0;

    public Profesor(String id, String nombre, String email, String departamento, int cargaSemanalHoras) {
        super(id, nombre, email);
        this.departamento = departamento;
        this.cargaSemanalHoras = cargaSemanalHoras;
        totalProfesores++;
    }

    @Override
    public String getRol() { return "Profesor"; }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[Profesor " + getNombre() + "] " + mensaje);
    }

    // Getters/Setters
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public int getCargaSemanalHoras() { return cargaSemanalHoras; }
    public void setCargaSemanalHoras(int cargaSemanalHoras) { this.cargaSemanalHoras = cargaSemanalHoras; }

    public static int getTotalProfesores() { return totalProfesores; }

    @Override
    public String toString() {
        return super.toString() + " (dept='" + departamento + "', horas=" + cargaSemanalHoras + ")";
    }
}