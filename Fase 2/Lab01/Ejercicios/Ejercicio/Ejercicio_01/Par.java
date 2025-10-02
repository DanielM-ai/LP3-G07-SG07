package Ejercicio_01;

//Contenido del paquete ejercicio_01

public class Par<F, S> {
 
 private F primero;
 private S segundo;

 // Constructor
 public Par(F primero, S segundo) {
     this.primero = primero;
     this.segundo = segundo;
 }

 // Métodos Getter
 public F getPrimero() {
     return primero;
 }

 public S getSegundo() {
     return segundo;
 }

 // Métodos Setter
 public void setPrimero(F primero) {
     this.primero = primero;
 }

 public void setSegundo(S segundo) {
     this.segundo = segundo;
 }

 // Agregamos un Método toString
 @Override
 public String toString() {
     return "(Primero: " + primero + ", Segundo: " + segundo + ")";
 }
 
 /* NOTA: El método esIgual se agregará en el Ejercicio 2. */
}