package actividad3;

public class IgualGenerico {

    // Método genérico para comparar dos argumentos
    public static <T> boolean esIgualA(T obj1, T obj2) {
        // Si ambos son null, se consideran iguales
        if (obj1 == null && obj2 == null) {
            return true;
        }
        // Si solo uno es null, no son iguales
        if (obj1 == null || obj2 == null) {
            return false;
        }
        // Usar el método equals para comparar los objetos
        return obj1.equals(obj2);
    }

    public static void main(String[] args) {
        // --- Pruebas con diferentes tipos ---
        System.out.println("--- Comparando Tipos Integer ---");
        Integer i1 = 10;
        Integer i2 = 10;
        Integer i3 = 20;
        System.out.printf("¿%d es igual a %d? -> %b\n", i1, i2, esIgualA(i1, i2)); // true
        System.out.printf("¿%d es igual a %d? -> %b\n", i1, i3, esIgualA(i1, i3)); // false

        System.out.println("\n--- Comparando Tipos String ---");
        String s1 = "Java";
        String s2 = new String("Java");
        String s3 = "Python";
        System.out.printf("¿\"%s\" es igual a \"%s\"? -> %b\n", s1, s2, esIgualA(s1, s2)); // true
        System.out.printf("¿\"%s\" es igual a \"%s\"? -> %b\n", s1, s3, esIgualA(s1, s3)); // false

        System.out.println("\n--- Comparando con null ---");
        String s4 = null;
        System.out.printf("¿\"%s\" es igual a null? -> %b\n", s1, esIgualA(s1, s4)); // false
        System.out.printf("¿null es igual a null? -> %b\n", esIgualA(s4, null)); // true

        System.out.println("\n--- Comparando Tipos Object ---");
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = o1;
        System.out.println("¿Dos objetos nuevos son iguales? -> " + esIgualA(o1, o2)); // false (diferente referencia)
        System.out.println("¿Un objeto es igual a sí mismo? -> " + esIgualA(o1, o3)); // true (misma referencia)
    }
}