package ejercicio_03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorEmpleado {
    private List<Empleado> empleados;
    private String archivo;
    private VistaEmpleado vista;

    public ControladorEmpleado(VistaEmpleado vista, String archivo) {
        this.vista = vista;
        this.archivo = archivo;
        this.empleados = new ArrayList<>();
    }

    // Cargar empleados desde archivo al inicio
    public void leerEmpleados() {
        File f = new File(archivo);
        if (!f.exists()) {
            vista.mostrarMensaje("Archivo no encontrado. Se creará uno nuevo al agregar empleados.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length >= 3) {
                    try {
                        int numero = Integer.parseInt(partes[0].trim());
                        String nombre = partes[1].trim();
                        double sueldo = Double.parseDouble(partes[2].trim());

                        // Validación básica
                        if (numero > 0 && !nombre.isEmpty() && sueldo > 0) {
                            empleados.add(new Empleado(numero, nombre, sueldo));
                        }
                    } catch (NumberFormatException e) {
                        vista.mostrarMensaje("Línea inválida ignorada: " + linea);
                    }
                }
            }
            vista.mostrarMensaje("Empleados cargados: " + empleados.size());
        } catch (IOException e) {
            vista.mostrarMensaje("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Mostrar todos los empleados (de memoria)
    public void listarEmpleados() {
        vista.mostrarEmpleados(empleados);
    }

    // Agregar nuevo empleado
    public void agregarEmpleado() {
        int numero = vista.pedirNumero();
        if (numero <= 0) {
            vista.mostrarMensaje("Error: El número debe ser mayor a 0.");
            return;
        }

        String nombre = vista.pedirNombre();
        if (nombre.isEmpty()) {
            vista.mostrarMensaje("Error: El nombre no puede estar vacío.");
            return;
        }

        double sueldo = vista.pedirSueldo();
        if (sueldo <= 0) {
            vista.mostrarMensaje("Error: El sueldo debe ser mayor a 0.");
            return;
        }

        // Verificar si ya existe por número (asumiendo únicos)
        for (Empleado e : empleados) {
            if (e.getNumero() == numero) {
                vista.mostrarMensaje("Error: Ya existe un empleado con ese número.");
                return;
            }
        }

        empleados.add(new Empleado(numero, nombre, sueldo));
        guardarEmpleados();
        vista.mostrarMensaje("Empleado agregado correctamente.");
    }

    // Buscar empleado por número
    public void buscarEmpleado(int numero) {
        for (Empleado e : empleados) {
            if (e.getNumero() == numero) {
                vista.mostrarMensaje("Empleado encontrado: " + e);
                return;
            }
        }
        vista.mostrarMensaje("No se encontró el empleado con número " + numero + ".");
    }

    // Eliminar empleado por número
    public void eliminarEmpleado(int numero) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getNumero() == numero) {
                empleados.remove(i);
                guardarEmpleados();
                vista.mostrarMensaje("Empleado eliminado correctamente.");
                return;
            }
        }
        vista.mostrarMensaje("No se encontró el empleado con número " + numero + ".");
    }

    // Guardar lista al archivo (persistir cambios)
    public void guardarEmpleados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Empleado e : empleados) {
                bw.write(e.toCsv());
                bw.newLine();
            }
            // No mostramos mensaje aquí; se maneja en el método que llama
        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
