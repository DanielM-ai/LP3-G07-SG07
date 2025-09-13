package Ejercicio_02;

public class DivisionPorCeroException extends ArithmeticException {

    public DivisionPorCeroException(String mensaje) {
        super(mensaje);
    }
}