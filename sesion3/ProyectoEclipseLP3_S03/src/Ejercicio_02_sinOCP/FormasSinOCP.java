package Ejercicio_02_sinOCP;

public class FormasSinOCP {
    public static void main(String[] args) {
        Forma circulo = new Forma("circulo");
        Forma rectangulo = new Forma("rectangulo");
        Forma triangulo = new Forma("triangulo"); // añadimos un nuevo tipo

        Dibujo.dibujar(circulo);
        Dibujo.dibujar(rectangulo);
        Dibujo.dibujar(triangulo); // este rompe OCP
    }
}

// Clase base "Forma" solo guarda un tipo
class Forma {
    private String tipo;

    public Forma(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}

// Clase Dibujo que dibuja según el tipo de forma
class Dibujo {
    public static void dibujar(Forma forma) {
        if (forma.getTipo().equalsIgnoreCase("circulo")) {
            System.out.println("Dibujando un círculo");
        } else if (forma.getTipo().equalsIgnoreCase("rectangulo")) {
            System.out.println("Dibujando un rectángulo");
        } else if (forma.getTipo().equalsIgnoreCase("triangulo")) {
            // Para agregar el triángulo tuvimos que MODIFICAR este código
            System.out.println("Dibujando un triángulo");
        } else {
            System.out.println("Forma no reconocida");
        }
    }
}