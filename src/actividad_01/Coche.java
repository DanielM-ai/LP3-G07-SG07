package actividad_01;
	
//Esta es nuestra clase Coche:
public class Coche {
 // Los atributos de la clase:
 private String marca;
 private String modelo;
 private int anioFabricacion;
 private double precio;
 private boolean encendido;
 private int velocidad;

 // Constructor vacío
 public Coche() {
     this.marca = "Desconocida";
     this.modelo = "Desconocido";
     this.anioFabricacion = 0;
     this.precio = 0.0;
     this.encendido = false;
     this.velocidad = 0;
 }

 // Constructor con parámetros
 public Coche(String marca, String modelo, int anioFabricacion, double precio) {
     this.marca = marca;
     this.modelo = modelo;
     this.anioFabricacion = anioFabricacion;
     this.precio = precio;
     this.encendido = false;
     this.velocidad = 0;
 }

 // Primer método para aplicar descuentos:
 public boolean aplicarDescuento(double descuento) {
     if (descuento > 0 && descuento <= precio) {
         this.precio -= descuento;
         return true;
     }
     return false;
 }

 // Método para encender
 public void encender() {
     if (!encendido) {
         encendido = true;
         System.out.println(marca + " " + modelo + " está encendido.");
     } else {
         System.out.println(marca + " " + modelo + " ya estaba encendido.");
     }
 }
//Método para apagar:
 public void apagar() {
     if (encendido) {
         encendido = false;
         velocidad = 0;
         System.out.println(marca + " " + modelo + " se apagó.");
     } else {
         System.out.println(marca + " " + modelo + " ya estaba apagado.");
     }
 }
//Método para acelerar: 
 public void acelerar(int incremento) {
     if (encendido) {
         velocidad += incremento;
         System.out.println(marca + " " + modelo + " aceleró a " + velocidad + " km/h.");
     } else {
         System.out.println("No puedes acelerar, el coche está apagado.");
     }
 }
//Método para frenar: 
 public void frenar(int decremento) {
     if (encendido && velocidad > 0) {
         velocidad -= decremento;
         if (velocidad < 0) velocidad = 0;
         System.out.println(marca + " " + modelo + " frenó a " + velocidad + " km/h.");
     } else {
         System.out.println("No puedes frenar, el coche está apagado o ya está detenido.");
     }
 }
}

