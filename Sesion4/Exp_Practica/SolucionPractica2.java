    import java.util.HashMap;
    import java.util.Map;
    import java.util.List;
    import java.util.ArrayList;

    // --- EXCEPCIONES PERSONALIZADAS ---

    class SaldoInsuficienteException extends Exception {
        public SaldoInsuficienteException(String message) {
            super(message);
        }
    }

    class CuentaNoEncontradaException extends Exception {
        public CuentaNoEncontradaException(String message) {
            super(message);
        }
    }

    class SaldoNoCeroException extends Exception {
        public SaldoNoCeroException(String message) {
            super(message);
        }
    }

    // --- CLASE CUENTA BANCARIA (MODIFICADA PARA LA EXPERIENCIA 2) ---

    class CuentaBancaria {
        private String numeroCuenta;
        private String titular;
        private double saldo;
        private boolean activa;
        private List<String> historialTransacciones;

        public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
            if (saldoInicial < 0) {
                throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
            }
            this.numeroCuenta = numeroCuenta;
            this.titular = titular;
            this.saldo = saldoInicial;
            this.activa = true; // La cuenta inicia activa
            this.historialTransacciones = new ArrayList<>();
            if(saldoInicial > 0) {
                historialTransacciones.add("Depósito inicial: +" + saldoInicial);
            }
        }
        
        // Métodos depositar y retirar (simplificados para enfocarnos en lo nuevo)
        public void depositar(double monto) {
            if (monto <= 0) throw new IllegalArgumentException("Monto a depositar debe ser positivo.");
            this.saldo += monto;
            historialTransacciones.add("Depósito: +" + monto);
        }

        public void retirar(double monto) throws SaldoInsuficienteException {
            if (monto <= 0) throw new IllegalArgumentException("Monto a retirar debe ser positivo.");
            if (monto > this.saldo) throw new SaldoInsuficienteException("Saldo insuficiente.");
            this.saldo -= monto;
            historialTransacciones.add("Retiro: -" + monto);
        }
        
        // --- NUEVOS MÉTODOS PARA LA EXPERIENCIA 2 ---
        
        /**
        * Transfiere un monto desde esta cuenta a una cuenta de destino.
        * @param destino La cuenta que recibirá el dinero.
        * @param monto El monto a transferir.
        * @throws SaldoInsuficienteException si no hay saldo suficiente en la cuenta origen.
        */
        public void transferir(CuentaBancaria destino, double monto) throws SaldoInsuficienteException {
            // La validación de cuenta destino no encontrada se hace en la clase Banco.
            // Aquí solo validamos el saldo y realizamos la operación.
            this.retirar(monto); // Reutilizamos el método retirar que ya lanza SaldoInsuficienteException
            destino.depositar(monto);
            historialTransacciones.add("Transferencia enviada a " + destino.getNumeroCuenta() + ": -" + monto);
            destino.historialTransacciones.add("Transferencia recibida de " + this.getNumeroCuenta() + ": +" + monto);
            System.out.println("Transferencia de " + monto + " a la cuenta " + destino.getNumeroCuenta() + " realizada con éxito.");
        }
        
        /**
        * Cierra la cuenta si su saldo es cero.
        * @throws SaldoNoCeroException si la cuenta tiene un saldo distinto de cero.
        */
        public void cerrarCuenta() throws SaldoNoCeroException {
            if (this.saldo != 0) {
                throw new SaldoNoCeroException("No se puede cerrar la cuenta. Saldo actual: " + this.saldo);
            }
            this.activa = false;
            System.out.println("Cuenta " + this.numeroCuenta + " cerrada exitosamente.");
        }
        
        // Getters
        public double getSaldo() { return saldo; }
        public String getNumeroCuenta() { return numeroCuenta; }
        public boolean isActiva() { return activa; }
        public String getTitular() { return titular; }
        public List<String> getHistorialTransacciones() { return historialTransacciones; }

        @Override
        public String toString() {
            return "CuentaBancaria [Nro=" + numeroCuenta + ", Titular=" + titular + ", Saldo=" + saldo + ", Activa=" + activa + "]";
        }
    }


    // --- CLASE BANCO PARA GESTIONAR CUENTAS ---

    class Banco {
        private Map<String, CuentaBancaria> cuentas = new HashMap<>();

        public void agregarCuenta(CuentaBancaria cuenta) {
            cuentas.put(cuenta.getNumeroCuenta(), cuenta);
        }

        public CuentaBancaria buscarCuenta(String numeroCuenta) throws CuentaNoEncontradaException {
            CuentaBancaria cuenta = cuentas.get(numeroCuenta);
            if (cuenta == null) {
                throw new CuentaNoEncontradaException("La cuenta con número " + numeroCuenta + " no fue encontrada.");
            }
            return cuenta;
        }
    }


    // --- CLASE PARA PROBAR LA EXPERIENCIA 2 ---

    public class SolucionPractica2 {
        public static void main(String[] args) {
            System.out.println("--- EXPERIENCIA DE PRÁCTICA N° 02 ---");
            
            Banco miBanco = new Banco();
            CuentaBancaria cuentaOrigen = new CuentaBancaria("CTA-001", "Ana Solis", 1000.0);
            CuentaBancaria cuentaDestino = new CuentaBancaria("CTA-002", "Luis Castro", 500.0);
            CuentaBancaria cuentaParaCerrar = new CuentaBancaria("CTA-003", "Pedro Pascal", 0.0);
            CuentaBancaria cuentaConSaldo = new CuentaBancaria("CTA-004", "Juana de Arco", 1.0);

            miBanco.agregarCuenta(cuentaOrigen);
            miBanco.agregarCuenta(cuentaDestino);
            miBanco.agregarCuenta(cuentaParaCerrar);
            miBanco.agregarCuenta(cuentaConSaldo);

            // C) PRUEBAS EN ESTA EXPERIENCIA
            System.out.println("\n[Prueba 1: Transferencias]");

            // Transferencia válida
            try {
                System.out.println("\nIntentando transferencia válida de 200.0 de CTA-001 a CTA-002...");
                CuentaBancaria origen = miBanco.buscarCuenta("CTA-001");
                CuentaBancaria destino = miBanco.buscarCuenta("CTA-002");
                origen.transferir(destino, 200.0);
                System.out.println("Saldo final origen: " + origen.getSaldo());
                System.out.println("Saldo final destino: " + destino.getSaldo());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
            
            // Transferencia a cuenta no existente
            try {
                System.out.println("\nIntentando transferir a cuenta 'CTA-999' (no existe)...");
                CuentaBancaria origen = miBanco.buscarCuenta("CTA-001");
                CuentaBancaria destino = miBanco.buscarCuenta("CTA-999"); // Esto lanzará la excepción
                origen.transferir(destino, 50.0);
            } catch (CuentaNoEncontradaException e) {
                System.out.println("Éxito: Excepción capturada correctamente -> " + e.getMessage());
            } catch (SaldoInsuficienteException e) {
                System.out.println("Error inesperado de saldo: " + e.getMessage());
            }

            // Transferencia con saldo insuficiente
            try {
                System.out.println("\nIntentando transferir 1000.0 (saldo origen: " + cuentaOrigen.getSaldo() + ")...");
                CuentaBancaria origen = miBanco.buscarCuenta("CTA-001");
                CuentaBancaria destino = miBanco.buscarCuenta("CTA-002");
                origen.transferir(destino, 1000.0);
            } catch (Exception e) {
                System.out.println("Éxito: Excepción capturada correctamente -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }

            System.out.println("\n[Prueba 2: Cierre de Cuentas]");

            // Intento de cerrar cuenta con saldo
            try {
                System.out.println("\nIntentando cerrar cuenta CTA-004 con saldo " + cuentaConSaldo.getSaldo() + "...");
                cuentaConSaldo.cerrarCuenta();
            } catch (SaldoNoCeroException e) {
                System.out.println("Éxito: Excepción capturada correctamente -> " + e.getMessage());
            }
            
            // Cierre de cuenta válido (saldo cero)
            try {
                System.out.println("\nIntentando cerrar cuenta CTA-003 con saldo " + cuentaParaCerrar.getSaldo() + "...");
                cuentaParaCerrar.cerrarCuenta();
                System.out.println("Estado final de la cuenta CTA-003: " + cuentaParaCerrar);
            } catch (SaldoNoCeroException e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }

            System.out.println("\n--- Fin de la Experiencia 2 ---");
        }
    }
