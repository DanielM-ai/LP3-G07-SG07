package logica;

import modelo.Empleado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GestorEmpleados {
    
    // --- Lógica de Consulta Compleja en Java ---
    
    /**
     * Simula una consulta SELECT... WHERE... ORDER BY... LIMIT... 
     * sobre una lista de objetos Empleado.
     * * @param listaBase La lista completa de empleados obtenida de la BD (DAO).
     * @param camposMostrar String con los campos deseados (ej: "ID,NOMBRE,SUELDO").
     * @param campoFiltro Nombre del campo para la condición (ej: "CARGO").
     * @param valorFiltro Valor que debe cumplir la condición (ej: "Gerente").
     * @param campoOrdenar Campo por el que se ordenará (ej: "SUELDO").
     * @param orden "ASC" para ascendente, "DESC" para descendente.
     * @param limite Cantidad máxima de registros a mostrar (0 o negativo para sin límite).
     * @return Una lista de Empleado que cumple con los criterios.
     */
    public List<Empleado> consultarArreglo(
            List<Empleado> listaBase, 
            String camposMostrar, // No se usa para filtrar, solo para impresión
            String campoFiltro, 
            String valorFiltro, 
            String campoOrdenar, 
            String orden, 
            int limite) 
    {
        // 1. FILTRADO (Simulación de WHERE)
        List<Empleado> listaFiltrada = new ArrayList<>();
        
        // Convertimos a mayúsculas para hacer la comparación de campos y órdenes robusta
        String campoFiltroUpper = campoFiltro != null ? campoFiltro.toUpperCase() : "";
        String valorFiltroLower = valorFiltro != null ? valorFiltro.toLowerCase() : "";
        
        // Recorremos la lista completa para aplicar el filtro
        for (int i = 0; i < listaBase.size(); i++) {
            Empleado e = listaBase.get(i);
            boolean cumpleCondicion = true;

            if (!campoFiltroUpper.isEmpty() && !valorFiltroLower.isEmpty()) {
                cumpleCondicion = false;
                
                // Usamos un switch simple para chequear qué campo filtrar
                switch (campoFiltroUpper) {
                    case "ID":
                        try {
                            // Si el valor es numérico, intentamos la comparación de ID
                            int idFiltro = Integer.parseInt(valorFiltro);
                            if (e.getId() == idFiltro) {
                                cumpleCondicion = true;
                            }
                        } catch (NumberFormatException ex) {
                            // Ignoramos si no es un número válido
                        }
                        break;
                    case "NOMBRE":
                        // Usamos contains para simular LIKE, y toLowerCase para no distinguir mayúsculas
                        if (e.getNombre().toLowerCase().contains(valorFiltroLower)) {
                            cumpleCondicion = true;
                        }
                        break;
                    case "CARGO":
                        if (e.getCargo().toLowerCase().contains(valorFiltroLower)) {
                            cumpleCondicion = true;
                        }
                        break;
                    case "SUELDO":
                        // Solo filtra si es el valor exacto del sueldo (comparación simple)
                        try {
                            double sueldoFiltro = Double.parseDouble(valorFiltro);
                            if (e.getSueldo() == sueldoFiltro) {
                                cumpleCondicion = true;
                            }
                        } catch (NumberFormatException ex) {
                             // Ignoramos si no es un número válido
                        }
                        break;
                    default:
                        // Si el campo de filtro es inválido, asumimos que no se cumple la condición
                        cumpleCondicion = false;
                        break;
                }
            }
            
            if (cumpleCondicion) {
                listaFiltrada.add(e);
            }
        }
        
        // 2. ORDENAMIENTO (Simulación de ORDER BY)
        ordenarLista(listaFiltrada, campoOrdenar, orden);
        
        // 3. LÍMITE (Simulación de LIMIT)
        List<Empleado> listaFinal = new ArrayList<>();
        int maximo = listaFiltrada.size();
        
        // Si hay límite y el límite es menor que el tamaño de la lista filtrada
        if (limite > 0 && limite < maximo) {
            maximo = limite;
        }
        
        // Recorremos hasta el límite (o hasta el final si no hay límite)
        for (int i = 0; i < maximo; i++) {
            listaFinal.add(listaFiltrada.get(i));
        }

        return listaFinal;
    }
    
    // --- MÉTODOS AUXILIARES ---
    
    /**
     * Aplica el ordenamiento usando la clase Collections.sort y un Comparator simple.
     * Nota: Este es el código más "avanzado" pero es la forma básica de ordenar listas en Java.
     */
    private void ordenarLista(List<Empleado> lista, String campoOrdenar, String orden) {
        if (campoOrdenar == null || campoOrdenar.isEmpty()) {
            return; // No hay ordenamiento especificado
        }
        
        String campoUpper = campoOrdenar.toUpperCase();
        String ordenUpper = orden != null ? orden.toUpperCase() : "ASC";
        
        // Usamos un Comparator para comparar los objetos según el campo
        Comparator<Empleado> comparador;
        
        switch (campoUpper) {
            case "ID":
                comparador = Comparator.comparing(Empleado::getId);
                break;
            case "NOMBRE":
                comparador = Comparator.comparing(Empleado::getNombre);
                break;
            case "CARGO":
                comparador = Comparator.comparing(Empleado::getCargo);
                break;
            case "SUELDO":
                comparador = Comparator.comparing(Empleado::getSueldo);
                break;
            default:
                // Si el campo es inválido, no ordenamos
                return; 
        }
        
        // Aplicamos el ordenamiento (Collections.sort)
        if (ordenUpper.equals("DESC")) {
            Collections.sort(lista, comparador.reversed());
        } else {
            Collections.sort(lista, comparador); // Por defecto ASC
        }
    }
    
    /**
     * Muestra la lista de empleados, imprimiendo solo los campos solicitados.
     */
    public void mostrarResultados(List<Empleado> lista, String camposMostrar) {
        if (lista.isEmpty()) {
            System.out.println("No se encontraron registros que cumplan con los criterios de búsqueda.");
            return;
        }

        String[] campos = camposMostrar.toUpperCase().split(",");
        
        System.out.println("\n--- RESULTADOS DE LA CONSULTA PERSONALIZADA ---");
        
        // Imprimir encabezado
        String encabezado = "";
        for (String campo : campos) {
            encabezado += String.format("%-15s", campo.trim());
        }
        System.out.println(encabezado);
        
        // Imprimir separador
        String separador = "";
        for (int i = 0; i < encabezado.length(); i++) {
            separador += "-";
        }
        System.out.println(separador);

        // Imprimir registros
        for (Empleado e : lista) {
            String fila = "";
            for (String campo : campos) {
                switch (campo.trim()) {
                    case "ID":
                        fila += String.format("%-15d", e.getId());
                        break;
                    case "NOMBRE":
                        fila += String.format("%-15s", e.getNombre());
                        break;
                    case "CARGO":
                        fila += String.format("%-15s", e.getCargo());
                        break;
                    case "SUELDO":
                        fila += String.format("%-15.2f", e.getSueldo());
                        break;
                    default:
                        fila += String.format("%-15s", "");
                        break;
                }
            }
            System.out.println(fila);
        }
        System.out.println("-----------------------------------------------\n");
    }
}