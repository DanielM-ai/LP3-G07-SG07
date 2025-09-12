package experiencia3;

import java.util.List;
import java.util.ArrayList;

// --- EXCEPCIONES NECESARIAS ---

class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}

// Nueva excepción personalizada para esta experiencia
class LimiteCreditoExcedidoException extends Exception {
    public LimiteCreditoExcedidoException(String message) {
        super(message);
    }
}


// --- CLASE BASE (SIMPLIFICADA) ---
class CuentaBancaria {
    protected String numeroCuenta;
    protected String titular;
    protected double saldo;
    protected List<String> historialTransacciones;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historialTransacciones = new ArrayList<>();
    }
    
    public void depositar(double monto) {
        if (monto > 0) this.saldo += monto;
    }

    // --- CORRECCIÓN AQUÍ ---
    // Se cambia la cláusula 'throws' a una más genérica (Exception) para
    // permitir que los métodos sobreescritos en las clases hijas puedan
    // lanzar sus propias excepciones personalizadas.
    public void retirar(double monto) throws Exception {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        if (monto > this.saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente. Saldo: " + this.saldo);
        }
        this.saldo -= monto;
        System.out.println("Retiro de " + monto + " realizado en cuenta normal. Saldo restante: " + this.saldo);
    }
    
    public double getSaldo() {
        return this.saldo;
    }

    @Override
    public String toString() {
        return "CuentaBancaria [Nro=" + numeroCuenta + ", Saldo=" + saldo + "]";
    }
}


// --- CLASE HIJA CUENTA DE CRÉDITO ---

class CuentaCredito extends CuentaBancaria {
    private double limiteCredito;

    public CuentaCredito(String numeroCuenta, String titular, double saldoInicial, double limiteCredito) {
        super(numeroCuenta, titular, saldoInicial);
        if (limiteCredito < 0) {
            throw new IllegalArgumentException("El límite de crédito no puede ser negativo.");
        }
        this.limiteCredito = limiteCredito;
    }

    /**
     * Sobreescribe el método retirar para usar el límite de crédito.
     * @param monto El monto a retirar.
     * @throws LimiteCreditoExcedidoException si el monto supera el saldo más el límite de crédito.
     * Esta excepción es PROPAGADA al método que la invoca.
     */
    @Override
    public void retirar(double monto) throws LimiteCreditoExcedidoException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        
        double saldoDisponibleTotal = this.saldo + this.limiteCredito;

        if (monto > saldoDisponibleTotal) {
            throw new LimiteCreditoExcedidoException(
                "La operación supera el límite de crédito. " +
                "Monto solicitado: " + monto + 
                ", Saldo disponible total (saldo + crédito): " + saldoDisponibleTotal
            );
        }
        
        this.saldo -= monto; // El saldo puede volverse negativo
        System.out.println("Retiro de " + monto + " realizado en cuenta de crédito. Nuevo saldo: " + this.saldo);
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    @Override
    public String toString() {
        return "CuentaCredito [Nro=" + numeroCuenta + ", Saldo=" + saldo + ", Limite=" + limiteCredito + "]";
    }
}


// --- CLASE PARA PROBAR LA EXPERIENCIA 3 ---
public class SolucionPractica3 {

    // Método que invoca a 'retirar' y MANEJA la excepción propagada
    public static void procesarRetiro(CuentaCredito cuenta, double monto) {
        System.out.println("\nProcesando retiro de " + monto + " para la cuenta " + cuenta.numeroCuenta + "...");
        try {
            // Se invoca el método que puede propagar la excepción
            cuenta.retirar(monto);
            System.out.println("Retiro procesado exitosamente.");
        } catch (LimiteCreditoExcedidoException e) {
            // Aquí se maneja la excepción que fue propagada desde el método retirar
            System.out.println("Error al procesar retiro: Excepción capturada -> " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error de argumento: " + e.getMessage());
        }
        System.out.println("Estado final de la cuenta: " + cuenta);
    }

    public static void main(String[] args) {
        System.out.println("--- EXPERIENCIA DE PRÁCTICA N° 03 ---");

        CuentaCredito cuentaConCredito = new CuentaCredito("CC-001", "Rosa Flores", 100.0, 500.0);
        System.out.println("Cuenta de crédito creada: " + cuentaConCredito);

        // B) PRUEBAS EN ESTA EXPERIENCIA
        
        // Prueba 1: Retiro que usa parte del saldo
        procesarRetiro(cuentaConCredito, 50.0); // Saldo: 50

        // Prueba 2: Retiro que usa todo el saldo y parte del crédito
        procesarRetiro(cuentaConCredito, 250.0); // Saldo: -200

        // Prueba 3: Retiro que usa más crédito
        procesarRetiro(cuentaConCredito, 300.0); // Saldo: -500 (usa todo el crédito)

        // Prueba 4: Retiro que excede el límite de crédito
        // Saldo actual: -500, Límite: 500. Saldo disponible real: 0.
        // Se intenta retirar 100, lo cual excede el límite.
        procesarRetiro(cuentaConCredito, 100.0); 

        System.out.println("\n--- Fin de la Experiencia 3 ---");
    }
}

