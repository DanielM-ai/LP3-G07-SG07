package experiencia4;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// --- EXCEPCIONES PERSONALIZADAS ---

class HistorialVacioException extends Exception {
    public HistorialVacioException(String message) {
        super(message);
    }
}

// --- CLASE CUENTA BANCARIA (con historial) ---
// Usaremos una versión simplificada de la clase de experiencias anteriores
class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private List<String> historialTransacciones;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historialTransacciones = new ArrayList<>();
        if (saldoInicial > 0) {
            historialTransacciones.add("Creación de cuenta con saldo: " + saldoInicial);
        }
    }

    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            historialTransacciones.add("Depósito: +" + monto);
        }
    }
    
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }
    public List<String> getHistorialTransacciones() { return historialTransacciones; }
}


// --- CLASE PARA GENERAR REPORTES ---

class ReporteTransacciones {
    /**
     * Genera un reporte en un archivo de texto para una cuenta bancaria.
     * @param cuenta La cuenta de la que se generará el reporte.
     * @param nombreArchivo El nombre del archivo de salida.
     * @throws HistorialVacioException si la cuenta no tiene transacciones.
     * @throws IOException si ocurre un error durante la escritura del archivo.
     */
    public void generarReporte(CuentaBancaria cuenta, String nombreArchivo) throws HistorialVacioException, IOException {
        if (cuenta.getHistorialTransacciones().isEmpty()) {
            throw new HistorialVacioException("No se puede generar reporte: la cuenta no tiene transacciones.");
        }

        // A) y B) Uso de try-with-resources para garantizar el cierre del recurso PrintWriter
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("--- Reporte de Transacciones ---");
            writer.println("Número de Cuenta: " + cuenta.getNumeroCuenta());
            writer.println("Titular: " + cuenta.getTitular());
            writer.println("Saldo Final: " + cuenta.getSaldo());
            writer.println("---------------------------------");
            writer.println("Historial:");
            for (String transaccion : cuenta.getHistorialTransacciones()) {
                writer.println("- " + transaccion);
            }
            System.out.println("Reporte '" + nombreArchivo + "' generado exitosamente.");
        }
        // No se necesita bloque finally, el recurso se cierra automáticamente.
    }

    /**
     * Lee y muestra el contenido de un reporte de transacciones.
     * @param nombreArchivo El nombre del archivo a leer.
     * @throws FileNotFoundException si el archivo no existe.
     */
    public void leerReporte(String nombreArchivo) throws FileNotFoundException {
        System.out.println("\n--- Leyendo reporte '" + nombreArchivo + "' ---");
        File archivo = new File(nombreArchivo);

        // B) Uso de try-with-resources para garantizar el cierre del recurso Scanner
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
        // No se necesita bloque finally, el recurso se cierra automáticamente.
    }
}


// --- CLASE PARA PROBAR LA EXPERIENCIA 4 ---
public class SolucionPractica4 {
    public static void main(String[] args) {
        System.out.println("--- EXPERIENCIA DE PRÁCTICA N° 04 ---");
        
        ReporteTransacciones generador = new ReporteTransacciones();
        
        // Creamos dos cuentas: una con transacciones y otra sin ellas
        CuentaBancaria cuentaConMovimientos = new CuentaBancaria("CTA-RPT-01", "Elena Rios", 100.0);
        cuentaConMovimientos.depositar(250.0);
        
        CuentaBancaria cuentaSinMovimientos = new CuentaBancaria("CTA-RPT-02", "Mario Bros", 0.0);
        
        String nombreArchivoValido = "reporte_CTA-RPT-01.txt";
        String nombreArchivoInexistente = "reporte_que_no_existe.txt";

        // D) PRUEBAS EN ESTA EXPERIENCIA

        // Prueba 1: Generar reporte para cuenta sin transacciones
        try {
            System.out.println("\n[Prueba 1: Intentando generar reporte para cuenta sin historial...]");
            generador.generarReporte(cuentaSinMovimientos, "reporte_vacio.txt");
        } catch (HistorialVacioException e) {
            System.out.println("Éxito: Excepción capturada correctamente -> " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de I/O inesperado: " + e.getMessage());
        }

        // Prueba 2: Generar reporte válido
        try {
            System.out.println("\n[Prueba 2: Generando reporte para cuenta con historial...]");
            generador.generarReporte(cuentaConMovimientos, nombreArchivoValido);
        } catch (Exception e) {
            System.out.println("Error inesperado al generar reporte válido: " + e.getMessage());
        }

        // Prueba 3: Leer el reporte recién creado
        try {
            System.out.println("\n[Prueba 3: Leyendo el reporte generado...]");
            generador.leerReporte(nombreArchivoValido);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo que se acaba de crear.");
        }
        
        // Prueba 4: Intentar leer un archivo que no existe
        try {
            System.out.println("\n[Prueba 4: Intentando leer un archivo inexistente...]");
            generador.leerReporte(nombreArchivoInexistente);
        } catch (FileNotFoundException e) {
            System.out.println("Éxito: Excepción capturada correctamente -> Archivo no encontrado: " + e.getMessage());
        }

        System.out.println("\n--- Fin de la Experiencia 4 ---");
    }
}
