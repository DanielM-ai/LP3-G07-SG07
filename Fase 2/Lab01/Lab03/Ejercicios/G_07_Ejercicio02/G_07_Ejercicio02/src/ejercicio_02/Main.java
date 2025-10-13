package ejercicio_02;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String archivo = "personajes.txt";
        GestorPersonajes gestor = new GestorPersonajes(archivo);

        System.out.println("=== GESTOR DE PERSONAJES (Ejercicio 2) ===");

        while (true) {
            System.out.println("\n1. Agregar personaje");
            System.out.println("2. Mostrar personajes");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Eliminar personaje");
            System.out.println("5. Actualizar atributo individual");
            System.out.println("6. Filtrar por atributo");
            System.out.println("7. Subir de nivel");
            System.out.println("8. Mostrar estadísticas");
            System.out.println("9. Guardar y salir");
            System.out.println("10. Importar personajes desde archivo externo");
            System.out.print("Opción: ");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
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
                    gestor.agregarPersonaje(new Personaje(nombre, vida, ataque, defensa, alcance, nivel));
                }
                case 2 -> gestor.mostrarPersonajes();
                case 3 -> {
                    System.out.print("Nombre a modificar: ");
                    String nBuscado = sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nNuevo = sc.nextLine();
                    System.out.print("Nueva vida: ");
                    int v = sc.nextInt();
                    System.out.print("Nuevo ataque: ");
                    int a = sc.nextInt();
                    System.out.print("Nueva defensa: ");
                    int d = sc.nextInt();
                    System.out.print("Nuevo alcance: ");
                    int alc = sc.nextInt();
                    System.out.print("Nuevo nivel: ");
                    int niv = sc.nextInt();
                    gestor.modificarPersonaje(nBuscado, nNuevo, v, a, d, alc, niv);
                }
                case 4 -> {
                    System.out.print("Nombre a eliminar: ");
                    String nEli = sc.nextLine();
                    gestor.eliminarPersonaje(nEli);
                }
                case 5 -> {
                    System.out.print("Nombre: ");
                    String n = sc.nextLine();
                    System.out.print("Atributo a modificar (vida/ataque/defensa/alcance): ");
                    String atr = sc.nextLine();
                    System.out.print("Nuevo valor: ");
                    int val = sc.nextInt();
                    gestor.actualizarAtributo(n, atr, val);
                }
                case 6 -> {
                    System.out.print("Atributo por el cual filtrar: ");
                    String atr = sc.nextLine();
                    gestor.filtrarPorAtributo(atr);
                }
                case 7 -> {
                    System.out.print("Nombre del personaje: ");
                    String n = sc.nextLine();
                    gestor.subirNivel(n);
                }
                case 8 -> gestor.mostrarEstadisticas();
                case 9 -> {
                    gestor.guardarEnArchivo();
                    System.out.println("Saliendo...");
                    return;
                }
                case 10 -> {
                    System.out.print("Ingrese el nombre del archivo a importar: ");
                    String archivoImportar = sc.nextLine();
                    gestor.importarDesdeArchivo(archivoImportar);
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}

