package LP3;

public class CuentaCorriente extends Cuenta {
    private int retiros;
    private final int LIBRE_RETIROS = 3;
    private final double TARIFA_TRANSACCION = 3.0;

    public CuentaCorriente() {
        super();
        this.retiros = 0;
    }

    @Override
    public void retirar(double monto) {
        super.retirar(monto);
        retiros++;
        // Aplica tarifa si se excede el límite de retiros gratuitos
        if (retiros > LIBRE_RETIROS) {
            super.retirar(TARIFA_TRANSACCION);
        }
    }

    @Override
    public void consultar() {
        // Resetea el contador de retiros
        retiros = 0;
    }
}