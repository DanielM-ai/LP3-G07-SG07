package actividad_02;

public class ContadorTest {

    public static void main(String[] args) {
        Contador c1, c2;
        System.out.println("Acumulador inicial: " + Contador.acumulador());

        c1 = new Contador();   // crea un contador con VALOR_INICIAL (10)
        c2 = new Contador(5);  // crea un contador con valor inicial 5

        c1.inc();
        c1.inc();
        c2.inc();

        System.out.println("Valor de c1: " + c1.getValor());
        System.out.println("Valor de c2: " + c2.getValor());
        System.out.println("Acumulador final: " + Contador.acumulador);

        // j.3 Mostrar el funcionamiento de nContadores y ultimoContador
        System.out.println("Número de contadores creados: " + Contador.nContadores);
        System.out.println("Valor inicial del último contador creado: " + Contador.ultimoContador);
    }
}

