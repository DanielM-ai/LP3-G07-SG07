package actividad2_y_4;

public class Pila<E> {
    private final int tamanio;
    private int superior;
    private E[] elementos;

    public Pila() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public Pila(int s) {
        tamanio = s > 0 ? s : 10;
        superior = -1;
        elementos = (E[]) new Object[tamanio];
    }

    public void push(E valorAMeter) {
        if (superior >= tamanio - 1) {
            throw new ExcepcionPilaLlena(String.format("La Pila esta llena, no se puede meter %s", valorAMeter));
        }
        elementos[++superior] = valorAMeter;
    }

    public E pop() {
        if (superior == -1) {
            throw new ExcepcionPilaVacia("Pila vacia, no se puede sacar");
        }
        return elementos[superior--];
    }

    // --- ACTIVIDAD 2: Método contains ---
    public boolean contains(E elemento) {
        if (superior == -1) { // Si la pila está vacía
            return false;
        }
        // Búsqueda desde el tope hacia el fondo sin modificar la pila
        for (int i = superior; i >= 0; i--) {
            if (elementos[i].equals(elemento)) {
                return true; // Elemento encontrado
            }
        }
        return false; // Elemento no encontrado
    }

    // --- ACTIVIDAD 4: Método esIgual ---
    public boolean esIgual(Pila<E> otraPila) {
        // 1. Verificar si tienen el mismo tamaño (cantidad de elementos)
        if (this.superior != otraPila.superior) {
            return false;
        }

        // 2. Comparar cada elemento en el mismo orden
        // Se itera sin modificar ninguna de las dos pilas
        for (int i = 0; i <= this.superior; i++) {
            if (!this.elementos[i].equals(otraPila.elementos[i])) {
                return false; // Si un elemento es diferente, las pilas no son iguales
            }
        }
        return true; // Si todos los elementos coincidieron
    }
}