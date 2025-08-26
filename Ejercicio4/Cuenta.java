package LP3;

public class Cuenta {
    private int numero;
    private double saldo;

    // Constructor que inicializa el número de cuenta y el saldo
    public Cuenta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    // Constructor que inicializa el número de cuenta con saldo cero
    public Cuenta(int numero) {
        this(numero, 0); // Llama al otro constructor con saldo 0
    }

    // Métodos Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        // Retorna la información de la cuenta formateada
        return "Cuenta [Número=" + numero + ", Saldo=S/." + String.format("%.2f", saldo) + "]";
    }
}