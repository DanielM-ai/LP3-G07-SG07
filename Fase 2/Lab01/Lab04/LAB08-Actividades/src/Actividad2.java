import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// --- CLASE 1: MAIN - EJECUTOR DEL EJERCICIO ---
/**
 * [cite_start]Solución para la Sección V, Ejercicio Propuesto 2. [cite: 434]
 * Esta clase 'main' utiliza el GestorProductos para simular consultas SQL
 * en memoria, usando la base de datos del ejercicio anterior.
 */
public class Actividad2 {

    public static void main(String[] args) {
        
        // Asumimos que la BD 'practica01.db' ya existe y tiene datos del Ejercicio 1
        GestorProductos gestor = new GestorProductos("jdbc:sqlite:practica01.db");
        
        System.out.println("--- Demostración del Gestor de Productos ---");

        // --- Consulta 1: Simula "SELECT * FROM producto WHERE stock < 50" ---
        System.out.println("\nConsulta 1: Productos con stock < 50 (WHERE)");
        [cite_start]// b) Condición WHERE [cite: 441]
        Predicate<Producto> stockBajo = p -> p.getStock() < 50;
        List<Producto> resultado1 = gestor.consultar(stockBajo, null, true, 0);
        imprimirResultados(resultado1);


        // --- Consulta 2: Simula "SELECT * FROM producto WHERE precio > 100 ORDER BY precio DESC LIMIT 2" ---
        System.out.println("\nConsulta 2: 2 Productos más caros (precio > 100) (WHERE, ORDER BY, LIMIT)");
        [cite_start]// b) Condición WHERE [cite: 441]
        Predicate<Producto> caros = p -> p.getPrecio() > 100;
        [cite_start]// c) Ordenar por "precio" de forma descendente [cite: 442]
        [cite_start]// d) Limitar a 2 registros [cite: 443]
        List<Producto> resultado2 = gestor.consultar(caros, "precio", false, 2);
        imprimirResultados(resultado2);
        
        
        // --- Consulta 3: Simula "SELECT * FROM producto ORDER BY nombre ASC" ---
        System.out.println("\nConsulta 3: Todos los productos ordenados por nombre (ORDER BY)");
        [cite_start]// c) Ordenar por "nombre" ascendente [cite: 442]
        List<Producto> resultado3 = gestor.consultar(null, "nombre", true, 0);
        imprimirResultados(resultado3);
    }
    
    [cite_start]// a) "Qué campos de la tabla deseo mostrar" [cite: 437]
    // Lo implementamos como un método de impresión simple.
    private static void imprimirResultados(List<Producto> lista) {
        if (lista.isEmpty()) {
            System.out.println("(No se encontraron resultados para esta consulta)");
            return;
        }
        System.out.printf("%-5s %-20s %-10s %-10s\n", "ID", "Nombre", "Precio", "Stock");
        System.out.println("-----------------------------------------------------");
        // Imprimimos los campos seleccionados (en este caso, todos)
        for (Producto p : lista) {
            System.out.printf("%-5d %-20s %-10.2f %-10d\n",
                    p.getId(), p.getNombre(), p.getPrecio(), p.getStock());
        }
    }
}


// --- CLASE 2: EL GESTOR (LÓGICA PRINCIPAL) ---
/**
 * Clase gestora que carga los registros en un arreglo de objetos (ArrayList)
 * [cite_start]e implementa un método de consulta sobre ese arreglo. [cite: 435]
 */
class GestorProductos {

    private List<Producto> inventario; // Datos cargados en memoria
    private String dbUrl;

    public GestorProductos(String dbUrl) {
        this.dbUrl = dbUrl;
        this.inventario = new ArrayList<>();
        cargarDatosDesdeDB();
    }

    /**
     * Carga todos los datos de la tabla 'producto' en el 'inventario' (ArrayList).
     */
    private void cargarDatosDesdeDB() {
        System.out.println("[Gestor] Cargando datos desde " + dbUrl + "...");
        String sql = "SELECT * FROM producto;";
        
        try (Connection con = DriverManager.getConnection(dbUrl);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                this.inventario.add(p);
            }
            System.out.println("[Gestor] Se cargaron " + inventario.size() + " productos en memoria.");

        } catch (SQLException e) {
            System.out.println("[Gestor] Error fatal al cargar la base de datos: " + e.getMessage());
        }
    }

    /**
     * Método de consulta genérico que simula SQL usando Java Streams.
     *
     * @param condicionWhere (b) Condición de filtrado (ej. p -> p.getPrecio() > 100). [cite_start]Nulo si no se filtra. [cite: 441]
     * @param campoOrdenar   (c) Nombre del campo para ordenar ("id", "nombre", "precio", "stock"). [cite_start]Nulo si no se ordena. [cite: 442]
     * [cite_start]@param ascendente     (c) true para ASC, false para DESC. [cite: 442]
     * [cite_start]@param limite         (d) Número de registros a limitar (0 si no hay límite). [cite: 443]
     * @return Lista de productos filtrada y ordenada.
     */
    public List<Producto> consultar(
            Predicate<Producto> condicionWhere,
            String campoOrdenar,
            boolean ascendente,
            int limite) {
        
        // Obtenemos el Stream de nuestro inventario en memoria
        Stream<Producto> stream = this.inventario.stream();

        [cite_start]// (b) Aplicar filtro WHERE [cite: 441]
        if (condicionWhere != null) {
            stream = stream.filter(condicionWhere);
        }

        [cite_start]// (c) Aplicar ORDER BY [cite: 442]
        if (campoOrdenar != null) {
            Comparator<Producto> comparador = null;

            switch (campoOrdenar.toLowerCase()) {
                case "id":
                    comparador = Comparator.comparingInt(Producto::getId);
                    break;
                case "nombre":
                    comparador = Comparator.comparing(Producto::getNombre);
                    break;
                case "precio":
                    comparador = Comparator.comparingDouble(Producto::getPrecio);
                    break;
                case "stock":
                    comparador = Comparator.comparingInt(Producto::getStock);
                    break;
                default:
                    System.out.println("[Gestor] Advertencia: Campo para ordenar '" + campoOrdenar + "' no reconocido.");
            }

            if (comparador != null) {
                if (!ascendente) {
                    comparador = comparador.reversed(); // Invertir para DESC
                }
                stream = stream.sorted(comparador);
            }
        }

        [cite_start]// (d) Aplicar LIMIT [cite: 443]
        if (limite > 0) {
            stream = stream.limit(limite);
        }

        // Devolver la lista final
        return stream.collect(Collectors.toList());
    }
}


// --- CLASE 3: POJO (ENTIDAD) ---
/**
 * Representa un registro de la tabla 'producto'.
 * [cite_start]Esta es la clase para el "arreglo de objetos" mencionado. [cite: 435]
 */
class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // --- Getters (necesarios para los comparadores) ---
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}