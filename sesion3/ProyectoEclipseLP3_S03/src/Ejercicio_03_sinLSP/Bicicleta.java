package Ejercicio_03_sinLSP;

public class Bicicleta extends Vehiculo {
    @Override
    public void acelerar() {
        throw new UnsupportedOperationException("Las bicicletas no aceleran como vehículos motorizados.");
    }
}