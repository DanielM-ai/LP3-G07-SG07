package ejercicioGestionU;

//Clase abstracta: base para Estudiante y Profesor
public abstract class Persona implements Notificable {
 // Atributos de instancia
	private final String id;
	private String nombre;
	private String email;

 // Atributo de clase (contador global de personas)
 protected static int totalPersonas = 0;

 public Persona(String id, String nombre, String email) {
     this.id = id;
     this.nombre = nombre;
     this.email = email;
     totalPersonas++;
 }

 // Métodos abstractos / polimórficos
 public abstract String getRol();

 // Getters/Setters (instancia)
 public String getId() { return id; }
 public String getNombre() { return nombre; }
 public void setNombre(String nombre) { this.nombre = nombre; }
 public String getEmail() { return email; }
 public void setEmail(String email) { this.email = email; }

 // Método de clase (estático)
 public static int getTotalPersonas() { return totalPersonas; }

 @Override
 public String toString() {
     return getRol() + " {id='" + id + "', nombre='" + nombre + "', email='" + email + "'}";
 }
}
