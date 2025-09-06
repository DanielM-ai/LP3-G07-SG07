package Ejercicio_03_sinLSP;

public class Main {
    public static void main(String[] args) {
        Vehiculo vehiculo1 = new Coche();
        Vehiculo vehiculo2 = new Bicicleta();
        
        hacerAcelerar(vehiculo1); // Output: El coche acelera usando el motor.
        hacerAcelerar(vehiculo2); // Lanza una excepción, violando LSP
    }
    
    public static void hacerAcelerar(Vehiculo vehiculo) {
        vehiculo.acelerar();
    }
}