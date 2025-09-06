package Ejercicio_03_conLSP;

public class Main {
    public static void main(String[] args) {
        Vehiculo vehiculo1 = new Coche();
        Vehiculo vehiculo2 = new Bicicleta();

        hacerMover(vehiculo1); // Output: El coche acelera usando el motor.
        hacerMover(vehiculo2); // Output: La bicicleta acelera pedaleando.
    }

    public static void hacerMover(Vehiculo vehiculo) {
        vehiculo.mover();
    }
}
