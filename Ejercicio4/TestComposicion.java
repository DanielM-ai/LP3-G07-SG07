package LP3;

public class TestComposicion {
    public static void main(String[] args) {
        // Crear dos objetos de la clase Persona
        System.out.println("Creando clientes y sus cuentas...");
        Persona cliente1 = new Persona(1, "Carlos", "Perez");
        Persona cliente2 = new Persona(2, "Ana", "Gomez");

        // Asignar saldo a las cuentas de los clientes
        cliente1.getCuenta().setSaldo(1500.50);
        cliente2.getCuenta().setSaldo(2300.00);

        // Mostrar la información de los clientes y sus cuentas
        System.out.println("\n--- Información de los Clientes ---");
        System.out.println(cliente1);
        System.out.println(cliente2);
    }
}