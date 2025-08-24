package prueba;

public class pruebaMain {

	public static void main(String[] args) {
		pruebaClase c1, c2;
		System.out.println(pruebaClase.acumulador()); //acumulador() llamas al método
 		c1=new pruebaClase(3);
		c2=new pruebaClase(10);
		c1.inc();
		c1.inc();
		c2.inc();
		System.out.println(c1.getValor());
		System.out.println(c2.getValor());
		System.out.println(pruebaClase.acumulador);	//accedes directamente a la variable estática

	}

}
