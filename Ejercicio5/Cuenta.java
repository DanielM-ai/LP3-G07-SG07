package LP3;

public class Cuenta {
    private double saldo;
    private int numeroCuenta;
    private static int ultimoNumero = 0;

    public Cuenta() {
        this.saldo = 0;
        this.numeroCuenta = ++ultimoNumero;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    public void retirar(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    // Método que será redefinido por las subclases
    public void consultar() {
        // Comportamiento base, no hace nada
    }
}