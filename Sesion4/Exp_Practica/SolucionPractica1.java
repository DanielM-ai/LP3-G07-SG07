package experiencia1;

import java.util.ArrayList;
import java.util.List;

// --- EXCEPCIÓN PERSONALIZADA ---
// Es crucial definir esta clase para que el resto del código funcione.
// Este es el motivo más probable del error de compilación.
class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}

// --- CLASE CUENTA BANCARIA ---
class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private List<String> historialTransacciones;

    /**
     * Constructor para la CuentaBancaria.
     * @param numeroCuenta El número de la cuenta.
     * @param titular El nombre del titular.
     * @param saldoInicial El saldo inicial.
     * @throws IllegalArgumentException si el saldo inicial es negativo.
     */
    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historialTransacciones = new ArrayList<>();
        if(saldoInicial > 0) {
            historialTransacciones.add("Depósito inicial: +" + saldoInicial);
        }
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        this.saldo += monto;
        historialTransacciones.add("Depósito: +" + monto);
        System.out.println("Depósito exitoso. Nuevo saldo: " + this.saldo);
    }

    /**
     * Retira un monto de la cuenta.
     * @param monto El monto a retirar.
     * @throws SaldoInsuficienteException si el monto a retirar supera el saldo disponible.
     */
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        if (monto > this.saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro. Saldo actual: " + this.saldo);
        }
        this.saldo -= monto;
        historialTransacciones.add("Retiro: -" + monto);
        System.out.println("Retiro exitoso. Nuevo saldo: " + this.saldo);
    }

    // --- Getters ---
    public double getSaldo() { return saldo; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTitular() { return titular; }
    public List<String> getHistorialTransacciones() { return historialTransacciones; }

    @Override
    public String toString() {
        return "CuentaBancaria [Nro=" + numeroCuenta + ", Titular=" + titular + ", Saldo=" + saldo + "]";
    }
}

// --- CLASE PARA PROBAR LA EXPERIENCIA 1 ---
public class SolucionPractica1 {
    public static void main(String[] args) {
        System.out.println("--- EXPERIENCIA DE PRÁCTICA N° 01 ---");

        // C) PRUEBAS EN ESTA EXPERIENCIA
        System.out.println("\n[Prueba 1: Creación de cuentas]");
        try {
            CuentaBancaria cuentaValida = new CuentaBancaria("001-123", "Juan Perez", 500.0);
            System.out.println("Éxito: Cuenta válida creada -> " + cuentaValida);
        } catch (IllegalArgumentException e) {
            System.out.println("Error inesperado al crear cuenta válida: " + e.getMessage());
        }

        try {
            System.out.println("Intentando crear cuenta con saldo negativo...");
            new CuentaBancaria("002-456", "Maria Lopez", -100.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Éxito: Excepción capturada correctamente -> " + e.getMessage());
        }

        System.out.println("\n[Prueba 2: Depósitos y Retiros]");
        CuentaBancaria miCuenta = new CuentaBancaria("003-789", "Carlos Gomez", 200.0);
        System.out.println("Cuenta de pruebas creada: " + miCuenta);

        try {
            System.out.println("\nIntentando depositar 150.0...");
            miCuenta.depositar(150.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // Intento de retiro con saldo insuficiente
        try {
            System.out.println("\nIntentando retirar 500.0 (saldo actual: " + miCuenta.getSaldo() + ")...");
            miCuenta.retirar(500.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Éxito: Excepción capturada correctamente -> " + e.getMessage());
        }

        // Retiro válido
        try {
            System.out.println("\nIntentando retirar 100.0...");
            miCuenta.retirar(100.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        System.out.println("\n--- Fin de la Experiencia 1 ---");
    }
}

