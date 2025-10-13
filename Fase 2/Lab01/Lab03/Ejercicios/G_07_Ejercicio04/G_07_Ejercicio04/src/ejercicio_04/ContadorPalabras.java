package ejercicio_04;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ContadorPalabras {
    private int totalLineas = 0;
    private int totalPalabras = 0;
    private int totalCaracteres = 0;
    private Map<String, Integer> conteoPalabras = new HashMap<>();

    // Método principal: procesa el archivo y calcula todo
    public void procesarArchivo(String rutaArchivo, VistaContador vista) throws IOException {
        // Reiniciar contadores
        totalLineas = 0;
        totalPalabras = 0;
        totalCaracteres = 0;
        conteoPalabras.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.length();  // Sin contar \n

                // Contar palabras en esta línea
                String palabraActual = "";
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);
                    if (Character.isLetterOrDigit(c)) {
                        palabraActual += Character.toLowerCase(c);  // Case-insensitive
                    } else {
                        if (!palabraActual.isEmpty()) {
                            // Agregar/actualizar conteo
                            conteoPalabras.put(palabraActual, conteoPalabras.getOrDefault(palabraActual, 0) + 1);
                            totalPalabras++;
                            palabraActual = "";
                        }
                    }
                }
                // Verificar palabra al final de línea
                if (!palabraActual.isEmpty()) {
                    conteoPalabras.put(palabraActual, conteoPalabras.getOrDefault(palabraActual, 0) + 1);
                    totalPalabras++;
                }
            }

            vista.mostrarMensaje("Archivo procesado correctamente.");
        } catch (FileNotFoundException e) {
            vista.mostrarError("Archivo no encontrado: " + rutaArchivo);
            throw e;  // Para que la vista maneje reintento
        } catch (IOException e) {
            vista.mostrarError("Error al leer el archivo: " + e.getMessage());
            throw e;
        }
    }

    // Calcular promedio de palabras por línea
    public double calcularPromedioPalabrasPorLinea() {
        if (totalLineas == 0) return 0;
        return (double) totalPalabras / totalLineas;
    }

    // Obtener palabras más frecuentes (todas con el conteo máximo)
    public void obtenerPalabrasFrecuentes(VistaContador vista) {
        if (conteoPalabras.isEmpty()) {
            vista.mostrarMensaje("No hay palabras para analizar.");
            return;
        }

        // Encontrar el conteo máximo con bucle
        int maxFrecuencia = 0;
        for (Map.Entry<String, Integer> entry : conteoPalabras.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
            }
        }

        // Listar todas las palabras con esa frecuencia
        vista.mostrarMensaje("\n--- PALABRAS MÁS FRECUENTES (conteo: " + maxFrecuencia + ") ---");
        for (Map.Entry<String, Integer> entry : conteoPalabras.entrySet()) {
            if (entry.getValue() == maxFrecuencia) {
                vista.mostrarMensaje(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    // Getters para resultados
    public int getTotalLineas() { return totalLineas; }
    public int getTotalPalabras() { return totalPalabras; }
    public int getTotalCaracteres() { return totalCaracteres; }
    public double getPromedioPalabrasPorLinea() { return calcularPromedioPalabrasPorLinea(); }
}
