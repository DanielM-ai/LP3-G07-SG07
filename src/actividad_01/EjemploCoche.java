package actividad_01;

//Clase de prueba
public class EjemploCoche {
 public static void main(String[] args) {
     // Crear objetos coche
     Coche coche1 = new Coche("Toyota", "Corolla", 2020, 15000);
     Coche coche2 = new Coche("Ford", "Mustang", 2022, 30000);

     // Encender los coches
     coche1.encender();
     coche2.encender();

     // Acelerar y frenar los coches
     coche1.acelerar(50);
     coche1.frenar(20);

     coche2.acelerar(100);
     coche2.frenar(50);

     // Aplicar descuento
     coche1.aplicarDescuento(1000);
     coche2.aplicarDescuento(2000);

     // Apagar los coches
     coche1.apagar();
     coche2.apagar();
 }
}

