package Ejercicio_03_conLSP;

public class Bicicleta extends VehiculoNoMotorizado {
    @Override
    protected void acelerarSinMotor() {
        System.out.println("La bicicleta acelera pedaleando.");
    }
}