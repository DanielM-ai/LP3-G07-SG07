package Ejercicio_03_conLSP;

// Clase abstracta para vehículos con motor
public abstract class VehiculoMotorizado implements Vehiculo {
    @Override
    public void mover() {
        acelerarConMotor();
    }

    // Método abstracto para la aceleración con motor
    protected abstract void acelerarConMotor();
}