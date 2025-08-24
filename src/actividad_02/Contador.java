package actividad_02;

public class Contador {
    static int acumulador;   // variable de clase
    final static int VALOR_INICIAL = 10; 

    // j.1: variable de clase para contar cuántos contadores se han creado
    static int nContadores = 0;  

    // j.2: variable de clase para guardar el valor inicial del último contador creado
    static int ultimoContador;  

    private int valor;   // variable de instancia 

    public static int acumulador() {
        return acumulador;
    }

    // Constructor con parámetro
    public Contador(int valor) {
        this.valor = valor;
        acumulador += valor;  
        nContadores++;              // cada vez que se crea un contador, incrementamos el número de contadores
        ultimoContador = valor;     // guardamos el valor inicial del último contador creado
    }

    // Constructor sin parámetros
    public Contador() {
        this (Contador.VALOR_INICIAL); 
    }

    public void inc() {
        valor++;
        acumulador++;
    }

    public int getValor() {
        return valor; 
    }
}


