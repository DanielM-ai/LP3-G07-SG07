package Ejercicio_03_conLSP;

// Clase abstracta para vehículos sin motor
public abstract class VehiculoNoMotorizado implements Vehiculo {
    @Override
    public void mover() {
        acelerarSinMotor();
    }

    // Método abstracto para la aceleración sin motor
    protected abstract void acelerarSinMotor();
}