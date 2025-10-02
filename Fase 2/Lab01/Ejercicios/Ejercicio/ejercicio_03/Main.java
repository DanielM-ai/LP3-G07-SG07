package ejercicio_03;

/**
 * Clase principal (Main) que demuestra el método genérico estático imprimirPar.
 */
public class Main {

    /**
     * MÉTODO GENÉRICO ESTÁTICO (Ejercicio 3).
     * El <F, S> antes de 'void' define los parámetros de tipo para el método.
     */
    public static <F, S> void imprimirPar(Par<F, S> par) {
        // Usa el toString() de la clase Par para la impresión.
        System.out.println("Par impreso: " + par);
    }
    
    public static void main(String[] args) {
        
        System.out.println("--- EJERCICIO 3: MÉTODO GENÉRICO ESTÁTICO ---");
        
        // 1. Llama con Par<String, Integer>
        Par<String, Integer> par1 = new Par<>("Línea", 5);
        System.out.print("Tipo String, Integer: ");
        Main.imprimirPar(par1); 

        // 2. Llama con Par<Double, Boolean>
        Par<Double, Boolean> par2 = new Par<>(3.14, true);
        System.out.print("Tipo Double, Boolean: ");
        Main.imprimirPar(par2);

        // 3. Llama con Par<Persona, Integer>
        Persona p = new Persona("Julia", 28);
        Par<Persona, Integer> par3 = new Par<>(p, 100);
        System.out.print("Tipo Persona, Integer: ");
        Main.imprimirPar(par3);
    }
}