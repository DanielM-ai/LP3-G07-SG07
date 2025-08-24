package prueba;

public class pruebaClase {//Márquez-Mansilla
	static int acumulador;	//variable de clase (atributo estático); las variables estáticas de tipo int se inicializan automáticamente en 0,
	private int valor; 
	
	public static int acumulador() {	//método estático, simplemente devuelve el valor actual de la variable acumulador.
		return acumulador;
	}
	//Este es nuestro constructor
	public pruebaClase (int valor) {
		this.valor=valor;	//inicializa la variable de instancia
		acumulador+=valor;	//modifica la variable de clase. //Aquí está actualizando la variable de clase
	}				//La línea 13 significa: "Cada vez que se cree un nuevo objeto, voy a sumar su valor al total acumulado de la clase".
					//La línea 13 está en el constructor porque se quiere que cada vez que se construya un objeto, ese objeto contribuya automáticamente al total compartido (acumulador).	
	public void inc() { //Marquez-Mansilla
		this.valor++;
		acumulador++;
	}
	
	public int getValor() {
		return valor; 
	}
}
