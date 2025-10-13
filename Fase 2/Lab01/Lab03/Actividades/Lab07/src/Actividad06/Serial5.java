package Actividad06;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Serial5 {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("alumnos.dat");
             ObjectOutputStream salida = new ObjectOutputStream(fos)) {
            
            Fecha f1 = new Fecha(5, 9, 2011);
            Alumno a1 = new Alumno("12345678A", "Lucas González", 20, f1);
            salida.writeObject(a1);

            Fecha f2 = new Fecha(7, 9, 2011);
            Alumno a2 = new Alumno("98765432B", "Anacleto Jiménez", 19, f2);
            salida.writeObject(a2);

            Fecha f3 = new Fecha(8, 9, 2011);
            Alumno a3 = new Alumno("78234212Z", "María Zapata", 21, f3);
            salida.writeObject(a3);
            
            System.out.println("Objetos de Alumno guardados correctamente en alumnos.dat");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}