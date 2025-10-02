package Ejercicio_01;

public class PruebaE1 {

    public static void main(String[] args) {
        
        System.out.println("--- EJERCICIO 1: CLASE GENÉRICA PAR ---");
        
        // 1. Prueba con String y Integer
        Par<String, Integer> producto = new Par<>("Monitor", 101);
        System.out.println("Par inicial (String, Integer): " + producto.toString());
        
        // Uso de Getters
        System.out.println("  -> Valor del Primero: " + producto.getPrimero());
        
        // Uso de Setters
        producto.setSegundo(102);
        System.out.println("  -> Par modificado: " + producto.toString());
        
        System.out.println("----------------------------------------");
        
        // 2. Prueba con Double y Boolean
        Par<Double, Boolean> configuracion = new Par<>(3.14159, true);
        System.out.println("Par 2 (Double, Boolean): " + configuracion.toString());
        
        configuracion.setPrimero(9.8);
        configuracion.setSegundo(false);
        System.out.println("  -> Par modificado: " + configuracion.toString());
    }
}