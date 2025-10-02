package ejercicio_02;

/**
 * Clase auxiliar para demostrar la comparación de objetos complejos.
 * Sobrescribe equals() para comparar contenido.
 */
public class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" + nombre + ", " + edad + "}";
    }

    // Método crucial para comparar contenido en Par.esIgual()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona persona = (Persona) obj;
        
        // Comparamos el contenido de los atributos
        return edad == persona.edad && nombre.equals(persona.nombre);
    }
    
    // Es buena práctica sobrescribir hashCode cuando se sobrescribe equals
    @Override
    public int hashCode() {
        return nombre.hashCode() + edad; 
    }
}