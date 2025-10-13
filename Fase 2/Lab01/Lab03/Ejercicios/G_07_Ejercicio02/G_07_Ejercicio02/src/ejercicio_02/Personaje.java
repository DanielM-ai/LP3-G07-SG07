package ejercicio_02;

import java.util.Random;

public class Personaje {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    private int nivel;

    // Constructor
    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance, int nivel) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.alcance = alcance;
        this.nivel = nivel;
    }

    // Constructor para generar personaje aleatorio
    public static Personaje generarAleatorio() {
        Random rand = new Random();
        String[] nombres = {"Arthas", "Thrall", "Jaina", "Illidan", "Sylvanas", "Anduin", "Valeera"};
        String nombre = nombres[rand.nextInt(nombres.length)];
        int vida = rand.nextInt(51) + 50;     // 50–100
        int ataque = rand.nextInt(21) + 20;   // 20–40
        int defensa = rand.nextInt(21) + 10;  // 10–30
        int alcance = rand.nextInt(6) + 5;    // 5–10
        int nivel = rand.nextInt(3) + 1;      // 1–3
        return new Personaje(nombre, vida, ataque, defensa, alcance, nivel);
    }

    // Métodos get/set
    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAlcance() { return alcance; }
    public int getNivel() { return nivel; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setVida(int vida) { this.vida = vida; }
    public void setAtaque(int ataque) { this.ataque = ataque; }
    public void setDefensa(int defensa) { this.defensa = defensa; }
    public void setAlcance(int alcance) { this.alcance = alcance; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    // Subir nivel
    public void subirNivel() {
        nivel++;
        vida += 10;
        ataque += 5;
        defensa += 3;
        alcance += 1;
        System.out.println(nombre + " ha subido al nivel " + nivel + "!");
    }

    @Override
    public String toString() {
        return nombre + "," + vida + "," + ataque + "," + defensa + "," + alcance + "," + nivel;
    }
}

