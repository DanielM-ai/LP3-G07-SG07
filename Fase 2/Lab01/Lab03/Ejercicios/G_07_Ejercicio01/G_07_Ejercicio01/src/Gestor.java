import java.io.*;
import java.util.*;

public class Gestor {
    private String archivo;
    private List<Personaje> personajes;

    public Gestor(String archivo) {
        this.archivo = archivo;
        this.personajes = new ArrayList<>();	//En nuestro constructor Gestor creamos nuestra propia lista vacía. Y luego la llena con los personajes que lee del archivo (si existen).
        cargarDesdeArchivo(); // Cargar personajes previos
    }

    // =====================	//Lee texto plano que luego debe convertir a objetos Personaje
    // CARGAR DESDE ARCHIVO:  permite leer los personajes guardados en un archivo de texto y cargarlos a la lista personajes
    // =====================
    private void cargarDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {	//Mientras el archivo tenga lineas, leelas una por una. 
                String[] partes = linea.split(",");
                if (partes.length == 6) {	//Comprueba que la línea tenga exactamente 5 datos; sino, está mal escrita o incompleta
                    String nombre = partes[0];
                    int vida = Integer.parseInt(partes[1]);
                    int ataque = Integer.parseInt(partes[2]);
                    int defensa = Integer.parseInt(partes[3]);
                    int alcance = Integer.parseInt(partes[4]);
                    int nivel = Integer.parseInt(partes[5]);
                    personajes.add(new Personaje(nombre, vida, ataque, defensa, alcance,nivel)); //Crea un nuevo personaje con los valores leídos. 
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo (puede que aún no exista).");
        }
    }

    // =====================
    // GUARDAR EN ARCHIVO
    // =====================
    public void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                bw.write(p.toString()); //Convierte al objeto en texto plano para poder escribirlo en el archivo.
                bw.newLine();	//Inserta un salto de línea (para que cada personaje quede en una línea distinta).
            }
            System.out.println("Datos guardados correctamente en " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    // =====================
    // AGREGAR PERSONAJE
    // =====================
    public void agregarPersonaje(Personaje p) {
        personajes.add(p);
    }

    // =====================
    // MOSTRAR PERSONAJES
    // =====================
    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
        } else {
            System.out.println("\n--- LISTA DE PERSONAJES ---");
            for (int i = 0; i < personajes.size(); i++) {
                System.out.println((i + 1) + ". " + personajes.get(i));
            }
        }
    }

    // =====================
    // ELIMINAR PERSONAJE
    // =====================
    public void eliminarPersonaje(String nombre) {
        boolean eliminado = false;

        for (int i = 0; i < personajes.size(); i++) {
            Personaje p = personajes.get(i);
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                personajes.remove(i);
                eliminado = true;
                System.out.println("Personaje '" + nombre + "' eliminado correctamente.");
                break; // Rompe el bucle después de eliminar (asumimos que no hay nombres repetidos)
            }
        }

        if (!eliminado) {	//El símbolo ! significa “no”, o sea “si no fue eliminado”.
            System.out.println("No se encontró el personaje con ese nombre.");
        }
    }

    // =====================
    // MODIFICAR PERSONAJE
    // =====================
    public void modificarPersonaje(String nombreBuscado, String nuevoNombre, int vida, int ataque, int defensa, int alcance, int nivel) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
            	p.setNombre(nuevoNombre);
                p.setVida(vida);
                p.setAtaque(ataque);
                p.setDefensa(defensa);
                p.setAlcance(alcance);
                p.setNivel(nivel);
                System.out.println("Personaje '" + nuevoNombre + "' modificado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró el personaje con ese nombre.");
    }

    // =====================
    // MÉTODO MAIN			-------------------------------------------------------------
    // =====================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gestor gestor = new Gestor("personajes.txt");

        System.out.println("=== GESTOR DE PERSONAJES ===");

        while (true) {
            System.out.println("\n1. Agregar personaje");
            System.out.println("2. Mostrar personajes");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Eliminar personaje");
            System.out.println("5. Guardar y salir");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Vida: ");
                    int vida = sc.nextInt();
                    System.out.print("Ataque: ");
                    int ataque = sc.nextInt();
                    System.out.print("Defensa: ");
                    int defensa = sc.nextInt();
                    System.out.print("Alcance: ");
                    int alcance = sc.nextInt();
                    System.out.print("Nivel: ");
                    int nivel = sc.nextInt();


                    try {
                        gestor.agregarPersonaje(new Personaje(nombre, vida, ataque, defensa, alcance,nivel));
                        System.out.println("Personaje agregado correctamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    gestor.mostrarPersonajes();
                    break;

                case 3:
                    System.out.print("Ingrese el nombre del personaje a modificar: ");
                    String nombreBuscado = sc.next();
                    
                    System.out.print("Nuevo nombre: ");
                    String nombreMod = sc.next();
                    
                    System.out.print("Nueva vida: ");
                    int vidaMod = sc.nextInt();
                    System.out.print("Nuevo ataque: ");
                    int ataqueMod = sc.nextInt();
                    System.out.print("Nueva defensa: ");
                    int defensaMod = sc.nextInt();
                    System.out.print("Nuevo alcance: ");
                    int alcanceMod = sc.nextInt();
                    
                    System.out.print("Nuevo nivel: ");
                    int alcanceNiv = sc.nextInt();

                    gestor.modificarPersonaje(nombreBuscado, nombreMod, vidaMod, ataqueMod, defensaMod, alcanceMod, alcanceNiv);
                    break;

                case 4:
                    System.out.print("Ingrese el nombre del personaje a eliminar: ");
                    String nombreEli = sc.nextLine();
                    gestor.eliminarPersonaje(nombreEli);
                    break;

                case 5:
                    gestor.guardarEnArchivo();
                    System.out.println("Saliendo del programa...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}


