package combat;

import java.util.Random;

public class Enemigo {
    private String nombre;
    private int salud;
    private int nivel;
    private String tipo;
    private int danoBase;
    private final Random random = new Random();

    public Enemigo(String nombre, int salud, int nivel, String tipo, int danoBase) {
        this.nombre = nombre;
        this.salud = salud;
        this.nivel = nivel;
        this.tipo = tipo;
        this.danoBase = danoBase;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getNivel() { return nivel; }
    public String getTipo() { return tipo; }

    // --- Métodos de Acción ---

    // Atacar (Daño aleatorio basado en el nivel y daño base)
    public int Atacar() {
        // 1. Calcula la variación base (10% del daño potencial).
        int variacionBase = (int) (danoBase * nivel * 0.1);
        
        // 2. Asegura que el rango para el método nextInt() sea al menos 1. 
        // Si variacionBase es 0, el rango será 1, y nextInt(1) devolverá 0.
        int rangoRandom = Math.max(1, 2 * variacionBase + 1); // +1 asegura que 2 * var + 1 sea >= 1

        int danio;

        // Calcula el desplazamiento aleatorio
        // random.nextInt(rangoRandom) genera un número entre 0 y rangoRandom-1
        int desplazamiento = random.nextInt(rangoRandom) - variacionBase;
        
        // El daño base es (danoBase * nivel)
        danio = danoBase * nivel + desplazamiento;
        
        // Asegura que el daño sea al menos 1
        return Math.max(1, danio); 
    }

    // Recibir Daño
    public int RecibirDanio(int danio) {
        this.salud -= danio;
        if (this.salud < 0) this.salud = 0;
        return this.salud;
    }
    
    public boolean estaVivo() {
        return salud > 0;
    }
}