package Actividad05;

public class ArrayPersona {
    public Persona arreglo[];
    final int max = 128;
    int tamano = 0;

    public ArrayPersona() {
        this.arreglo = new Persona[this.max];
    }

    public void addArray(Persona persona) {
        if (this.tamano == max) {
            System.err.println("Agenda llena. No se puede añadir más contactos.");
            return;
        }
        this.arreglo[this.tamano++] = persona;
    }

    public void printArray(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < this.tamano; i++) {
            if (this.arreglo[i].getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(this.arreglo[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún contacto con ese nombre.");
        }
    }
}