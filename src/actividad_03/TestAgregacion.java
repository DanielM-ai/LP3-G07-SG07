package actividad_03;

public class TestAgregacion {
    public static void main(String[] args) {
        // Crear motores
        Motor m1 = new Motor(101, 6000);
        Motor m2 = new Motor(202, 7000);

        // Crear automóviles
        Automovil auto1 = new Automovil("ABC-123", 4, "Toyota", "Corolla");
        Automovil auto2 = new Automovil("XYZ-789", 2, "Ford", "Mustang");

        // Asignar motores a automóviles
        auto1.setMotor(m1);
        auto2.setMotor(m2);

        // Mostrar info de los automóviles
        System.out.println(auto1);
        System.out.println(auto2);
    }
}