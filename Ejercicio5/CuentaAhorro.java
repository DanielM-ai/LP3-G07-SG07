package LP3;

public class CuentaAhorro extends Cuenta {
    private double tasaInteres;
    private double minSaldo;

    public CuentaAhorro(double tasaInteres) {
        super();
        this.tasaInteres = tasaInteres;
        this.minSaldo = getSaldo();
    }
    
    @Override
    public void retirar(double monto) {
        super.retirar(monto);
        double saldoActual = getSaldo();
        // Actualiza el saldo mínimo del mes
        if (saldoActual < minSaldo) {
            minSaldo = saldoActual;
        }
    }

    @Override
    public void consultar() {
        // Calcula y deposita el interés basado en el saldo mínimo
        double interes = minSaldo * tasaInteres / 100;
        depositar(interes);
        // Resetea el saldo mínimo para el siguiente periodo
        minSaldo = getSaldo();
    }
}