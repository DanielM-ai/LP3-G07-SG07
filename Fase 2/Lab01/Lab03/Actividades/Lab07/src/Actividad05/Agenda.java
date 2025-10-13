package Actividad05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Agenda {
    ArrayPersona lista;

    public Agenda() {
        this.lista = cargaContactos();
    }

    public void bucle() {
        Scanner sc = new Scanner(System.in);
        String nombre = "";
        
        do {
            System.out.print("Introduzca un nombre para buscar (o presione Enter para salir): ");
            nombre = sc.nextLine();
            if (!nombre.isEmpty()) {
                lista.printArray(nombre);
            }
        } while (!nombre.isEmpty());
        
        sc.close();
        System.out.println("Programa terminado.");
    }

    private ArrayPersona cargaContactos() {
        ArrayPersona directorio = new ArrayPersona();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("agenda.txt")))) {
            String nombre;
            while ((nombre = reader.readLine()) != null) {
                String telefono = reader.readLine();
                String direccion = reader.readLine();
                if (telefono != null && direccion != null) {
                    directorio.addArray(new Persona(nombre, telefono, direccion));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo 'agenda.txt'. Asegúrate de crearlo.");
        } catch (IOException e) {
            System.out.println("Error en la carga de los contactos: " + e.getMessage());
        }
        return directorio;
    }
}