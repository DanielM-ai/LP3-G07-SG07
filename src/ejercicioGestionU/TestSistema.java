package ejercicioGestionU;

public class TestSistema {
    public static void main(String[] args) {
        SistemaGestion sg = new SistemaGestion();

        // Profesores (agregación con Curso)
        Profesor pr1 = new Profesor("P001", "Mario Santillana", "msantillana@uni.edu", "Ciencias", 10);
        Profesor pr2 = new Profesor("P002", "Ana Velazco", "avelazco@uni.edu", "Ingeniería", 8);
        Profesor pr3 = new Profesor("P003", "Angel Montesinos", "amontesinos@uni.edu", "Matemáticas", 7);
        sg.registrarProfesor(pr1);
        sg.registrarProfesor(pr2);
        sg.registrarProfesor(pr3);

        // Estudiantes (peruanos 👨‍🎓👩‍🎓)
        Estudiante e1 = new Estudiante("E100", "José López", "jlopez@uni.edu", "Sistemas", 5);
        Estudiante e2 = new Estudiante("E101", "María Herrera", "mherrera@uni.edu", "Software", 6);
        Estudiante e3 = new Estudiante("E102", "Carlos Cutire", "ccutire@uni.edu", "Física", 4);
        Estudiante e4 = new Estudiante("E103", "Lucía Choque", "lchoque@uni.edu", "Redes", 5);
        sg.registrarEstudiante(e1);
        sg.registrarEstudiante(e2);
        sg.registrarEstudiante(e3);
        sg.registrarEstudiante(e4);

        // Cursos
        Curso c1 = new Curso("FIS-101", "Física I", CategoriaCurso.FISICA, pr1);
        Curso c2 = new Curso("PRG-303", "Programación 3", CategoriaCurso.PROGRAMACION3, pr2);
        Curso c3 = new Curso("RED-201", "Redes 1", CategoriaCurso.REDES1, pr3);
        sg.crearCurso(c1);
        sg.crearCurso(c2);
        sg.crearCurso(c3);

        // Inscripciones (composición: Curso crea Inscripcion)
        sg.inscribir("E100", "FIS-101");
        sg.inscribir("E100", "PRG-303");
        sg.inscribir("E101", "PRG-303");
        sg.inscribir("E102", "FIS-101");
        sg.inscribir("E103", "RED-201");

        // Mostrar datos
        System.out.println("\n=== PERSONAS (polimorfismo) ===");
        Persona[] personas = { e1, e2, e3, e4, pr1, pr2, pr3 };
        for (Persona p : personas) {
            // toString y notificar son polimórficos
            System.out.println(p);
            p.notificar("Recordatorio: revisar el aula virtual.");
        }

        System.out.println("\n=== CURSOS ===");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        System.out.println("\n=== CURSOS DISPONIBLES (con cupos) ===");
        for (Curso c : sg.cursosDisponibles()) System.out.println(c);

        System.out.println("\n=== MATRÍCULAS POR CURSO ===");
        sg.cantidadMatriculadosPorCurso().forEach((cod, cant) ->
                System.out.println(cod + " -> " + cant + " estudiante(s)"));

        System.out.println("\nTotales (instancia del sistema):");
        System.out.println("Estudiantes: " + sg.totalEstudiantes());
        System.out.println("Profesores: " + sg.totalProfesores());
        System.out.println("Cursos: " + sg.totalCursos());

        System.out.println("\nTotales (clase):");
        System.out.println("Personas creadas: " + Persona.getTotalPersonas());
        System.out.println("Estudiantes creados: " + Estudiante.getTotalEstudiantes());
        System.out.println("Profesores creados: " + Profesor.getTotalProfesores());
        System.out.println("Cursos creados: " + Curso.getTotalCursos());
    }
}
