package ejercicio_02;

import java.io.*;
import java.util.*;

public class GestorPersonajes {
    private String archivoPrincipal;
    private List<Personaje> personajes;

    public GestorPersonajes(String archivoPrincipal) {
        this.archivoPrincipal = archivoPrincipal;
        this.personajes = new ArrayList<>();
        cargarDesdeArchivo();
    }

    // ============================
    // Cargar desde archivo principal /////AVERIGUA PARA QUE ES EL .TRIM
    // ============================
    private void cargarDesdeArchivo() {
        File f = new File(archivoPrincipal);
        if (!f.exists()) {
            System.out.println("Archivo principal no encontrado. Se creará uno nuevo al guardar.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length >= 5) {
                    try {
                        String nombre = partes[0].trim();
                        int vida = Integer.parseInt(partes[1].trim());
                        int ataque = Integer.parseInt(partes[2].trim());
                        int defensa = Integer.parseInt(partes[3].trim());
                        int alcance = Integer.parseInt(partes[4].trim());
                        int nivel = (partes.length == 6) ? Integer.parseInt(partes[5].trim()) : 1;

                        personajes.add(new Personaje(nombre, vida, ataque, defensa, alcance, nivel));
                    } catch (NumberFormatException e) {
                        System.out.println("Línea inválida ignorada: " + linea);
                    }
                }
            }
            System.out.println("Personajes cargados: " + personajes.size());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo principal: " + e.getMessage());
        }
    }

    // ============================
    // Guardar en archivo principal
    // ============================
    public void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPrincipal))) {
            for (Personaje p : personajes) {
                bw.write(p.toString());
                bw.newLine();
            }
            System.out.println("Datos guardados correctamente en " + archivoPrincipal);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // ============================
    // Agregar personaje
    // ============================
    public void agregarPersonaje(Personaje p) {
        boolean existe = false;

        // Recorremos la lista de personajes para verificar si ya existe uno con el mismo nombre
        for (Personaje personaje : personajes) {
            if (personaje.getNombre().equalsIgnoreCase(p.getNombre())) {
                existe = true;
                break; // salimos del bucle al encontrar coincidencia
            }
        }

        if (existe) {
            System.out.println("Ya existe un personaje con ese nombre.");
        } else {
            personajes.add(p);
            System.out.println("Personaje agregado correctamente.");
        }
    }


    // ============================
    // Mostrar personajes
    // ============================
    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE PERSONAJES ---");
        for (Personaje p : personajes) System.out.println(p);
    }

    // ============================
    // Modificar personaje
    // ============================
    public void modificarPersonaje(String nombreBuscado, String nuevoNombre, int vida, int ataque, int defensa, int alcance, int nivel) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                p.setNombre(nuevoNombre);
                p.setVida(vida);
                p.setAtaque(ataque);
                p.setDefensa(defensa);
                p.setAlcance(alcance);
                p.setNivel(nivel);
                System.out.println("Personaje modificado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró el personaje.");
    }

    // ============================
    // Eliminar personaje
    // ============================
    public void eliminarPersonaje(String nombre) {
        for (int i = 0; i < personajes.size(); i++) {
            if (personajes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                personajes.remove(i);
                System.out.println("Personaje eliminado correctamente.");
                return;  // Salimos del método una vez eliminado (solo uno por nombre)
            }
        }
        System.out.println("No se encontró el personaje.");
    }
    // ============================
    // Actualizar atributo individual
    // ============================
    public void actualizarAtributo(String nombre, String atributo, int nuevoValor) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                switch (atributo.toLowerCase()) {
                    case "vida" -> p.setVida(nuevoValor);
                    case "ataque" -> p.setAtaque(nuevoValor);
                    case "defensa" -> p.setDefensa(nuevoValor);
                    case "alcance" -> p.setAlcance(nuevoValor);
                    default -> {
                        System.out.println("Atributo no válido.");
                        return;
                    }
                }
                System.out.println("Atributo actualizado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró el personaje.");
    }

    // ============================
    // Filtrar por atributo
    // ============================
    public void filtrarPorAtributo(String atributo) {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para ordenar.");
            return;
        }

        List<Personaje> copia = new ArrayList<>(personajes);
        copia.sort((a, b) -> switch (atributo.toLowerCase()) {
            case "vida" -> Integer.compare(b.getVida(), a.getVida());
            case "ataque" -> Integer.compare(b.getAtaque(), a.getAtaque());
            case "defensa" -> Integer.compare(b.getDefensa(), a.getDefensa());
            case "alcance" -> Integer.compare(b.getAlcance(), a.getAlcance());
            case "nivel" -> Integer.compare(b.getNivel(), a.getNivel());
            default -> 0;
        });

        System.out.println("\n--- PERSONAJES ORDENADOS POR " + atributo.toUpperCase() + " ---");
        for (Personaje p : copia) System.out.println(p);
    }

    // ============================
    // Subir nivel
    // ============================
    public void subirNivel(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.subirNivel();
                return;
            }
        }
        System.out.println("No se encontró el personaje.");
    }

    // ============================
    // Mostrar estadísticas
    // ============================
    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para calcular estadísticas.");
            return;
        }
        // Inicializamos sumas para cada atributo
        int sumaVida = 0;
        int sumaAtaque = 0;
        int sumaDefensa = 0;
        int sumaAlcance = 0;
        int totalPersonajes = personajes.size();  // Para el conteo y división
    // Bucle tradicional para sumar los valores de cada atributo
	    for (Personaje p : personajes) {
	        sumaVida += p.getVida();
	        sumaAtaque += p.getAtaque();
	        sumaDefensa += p.getDefensa();
	        sumaAlcance += p.getAlcance();
	    }
    // Calculamos los promedios (división entera convertida a double)
	    double promVida = (double) sumaVida / totalPersonajes;
	    double promAtaque = (double) sumaAtaque / totalPersonajes;
	    double promDefensa = (double) sumaDefensa / totalPersonajes;
	    double promAlcance = (double) sumaAlcance / totalPersonajes;
	    // Mostramos los resultados
	    System.out.println("\n--- ESTADÍSTICAS GENERALES ---");
	    System.out.println("Total personajes: " + totalPersonajes);
	    System.out.printf("Promedio Vida: %.2f | Ataque: %.2f | Defensa: %.2f | Alcance: %.2f%n",
	            promVida, promAtaque, promDefensa, promAlcance);
	}

    // ============================
    // Importar desde archivo externo
    // ============================
    public void importarDesdeArchivo(String rutaImportar) {
        File archivo = new File(rutaImportar);
        if (!archivo.exists()) {
            System.out.println("⚠️ El archivo no existe: " + rutaImportar);
            return;
        }
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length >= 5) {
                    try {
                        String nombre = partes[0].trim();
                        int vida = Integer.parseInt(partes[1].trim());
                        int ataque = Integer.parseInt(partes[2].trim());
                        int defensa = Integer.parseInt(partes[3].trim());
                        int alcance = Integer.parseInt(partes[4].trim());
                        int nivel = (partes.length == 6) ? Integer.parseInt(partes[5].trim()) : 1;
                        // Verificación tradicional si ya existe (sin stream/lambda)
                        boolean existe = false;
                        for (Personaje p : personajes) {
                            if (p.getNombre().equalsIgnoreCase(nombre)) {
                                existe = true;
                                break;  // Salimos del bucle al encontrar coincidencia
                            }
                        }
                        if (!existe) {
                            personajes.add(new Personaje(nombre, vida, ataque, defensa, alcance, nivel));
                            contador++;
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("⚠️ Línea inválida ignorada: " + linea);
                    }
                } else {
                    System.out.println("⚠️ Línea incompleta ignorada: " + linea);
                }
            }
            System.out.println("✅ Se importaron " + contador + " personajes desde " + rutaImportar);
            if (contador > 0) {
                System.out.println("\n--- PERSONAJES IMPORTADOS ---");
                for (Personaje p : personajes) System.out.println(p);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error al importar: " + e.getMessage());
        }
    }
    // ============================
    // Getter
    // ============================
    public List<Personaje> getPersonajes() {
        return personajes;
    }
}


