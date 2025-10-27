import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Solución para la "Experiencia de Práctica" de la Sesión 08 (Sección IV).
 * Demuestra:
 * 1. Operaciones CRUD (Insertar, Recuperar, Actualizar, Borrar)
 * 2. Uso de PreparedStatement
 * 3. Manejo de Transacciones (Commit y Rollback)
 */
public class Lab_08{

    public static void main(String[] args) {

        // Declaramos los recursos fuera del try para poder cerrarlos en el 'finally'
        Connection con = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // URL de conexión para SQLite
        String url = "jdbc:sqlite:practica08.db"; //

        try {
            // --- PASO 0: CONFIGURACIÓN INICIAL ---
            
            // 1. Registrar el driver (controlador)
            Class.forName("org.sqlite.JDBC"); //

            // 2. Crear la conexión
            con = DriverManager.getConnection(url); //
            System.out.println("✅ Conexión a 'practica08.db' establecida.");

            // 3. Crear un Statement
            stmt = con.createStatement(); //

            // Definimos la tabla de 3 campos
            String sqlCrearTabla = "CREATE TABLE IF NOT EXISTS usuarios (" +
                                   "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                   "nombre TEXT NOT NULL, " +
                                   "email TEXT);";
            stmt.execute(sqlCrearTabla); //
            
            // Limpiamos la tabla para una demostración limpia
            stmt.execute("DELETE FROM usuarios;");
            System.out.println("Tabla 'usuarios' (3 campos) creada y/o limpiada.");

            
            // --- ACTIVIDAD 1: Operaciones CRUD con Statement ---
            System.out.println("\n--- 1. Pruebas CRUD (Insertar, Recuperar, Actualizar, Borrar) ---");

            // INSERTAR
            stmt.execute("INSERT INTO usuarios (nombre, email) VALUES ('Juan Perez', 'juan@mail.com');"); 
            System.out.println("Registro 'Juan Perez' insertado.");

            // RECUPERAR (Leer)
            rs = stmt.executeQuery("SELECT * FROM usuarios;"); 
            System.out.println("Recuperando registros...");
            while (rs.next()) { //
                System.out.println(" -> ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre")); //
            }

            // ACTUALIZAR
            stmt.executeUpdate("UPDATE usuarios SET email = 'juan.perez@nuevo.com' WHERE nombre = 'Juan Perez';");
            System.out.println("Registro 'Juan Perez' actualizado.");

            // BORRAR
            stmt.executeUpdate("DELETE FROM usuarios WHERE nombre = 'Juan Perez';");
            System.out.println("Registro 'Juan Perez' borrado.");
            System.out.println("--- Fin Pruebas CRUD ---");


            // --- ACTIVIDAD 2: Interfaz PreparedStatement ---
            System.out.println("\n--- 2. Pruebas con PreparedStatement ---");
            
            // INSERTAR con PreparedStatement
            String sqlInsert = "INSERT INTO usuarios (nombre, email) VALUES (?, ?);"; //
            pstmt = con.prepareStatement(sqlInsert); //
            pstmt.setString(1, "Ana Lopez"); //
            pstmt.setString(2, "ana@mail.com");
            pstmt.executeUpdate(); //
            System.out.println("Registro 'Ana Lopez' insertado con PreparedStatement.");

            // ACTUALIZAR con PreparedStatement
            String sqlUpdate = "UPDATE usuarios SET email = ? WHERE nombre = ?;";
            pstmt.close(); // Cerramos el PreparedStatement anterior
            pstmt = con.prepareStatement(sqlUpdate);
            pstmt.setString(1, "ana.lopez@nuevo.com");
            pstmt.setString(2, "Ana Lopez");
            pstmt.executeUpdate();
            System.out.println("Registro 'Ana Lopez' actualizado con PreparedStatement.");
            System.out.println("--- Fin PreparedStatement ---");


            // --- ACTIVIDAD 3: Manejo de Transacciones ---
            System.out.println("\n--- 3. Pruebas con Transacciones (Commit/Rollback) ---");
            
            con.setAutoCommit(false); //

            // Transacción 1: COMMIT (Confirmar)
            try {
                System.out.println("Iniciando Transacción 1 (Commit)...");
                stmt.executeUpdate("INSERT INTO usuarios (nombre, email) VALUES ('Carlos', 'carlos@mail.com');"); //
                stmt.executeUpdate("INSERT INTO usuarios (nombre, email) VALUES ('Bety', 'bety@mail.com');"); 
                con.commit(); //
                System.out.println("Transacción 1 (Carlos y Bety) confirmada (Commit).");
            } catch (SQLException e) {
                con.rollback(); //
                System.out.println("Transacción 1 fallida, se hizo Rollback.");
            }

            // Transacción 2: ROLLBACK (Revertir)
            try {
                System.out.println("Iniciando Transacción 2 (Rollback)...");
                stmt.executeUpdate("INSERT INTO usuarios (nombre, email) VALUES ('David (para rollback)', 'david@mail.com');"); 
                stmt.executeUpdate("INSERT INTO usuarios (nombre, email) VALUES ('Eva (para rollback)', 'eva@mail.com');");
                con.rollback(); //
                System.out.println("Transacción 2 (David y Eva) revertida (Rollback).");
            } catch (SQLException e) {
                System.out.println("Error en Transacción 2: " + e.getMessage());
            }
            
            con.setAutoCommit(true); // Restaurar modo normal
            System.out.println("--- Fin Transacciones ---");

            // --- VERIFICACIÓN FINAL ---
            System.out.println("\n--- Verificación Final en BD ---");
            rs = stmt.executeQuery("SELECT * FROM usuarios;");
            System.out.println("Registros finales en la base de datos:");
            while (rs.next()) {
                System.out.println(" -> ID: " + rs.getInt(1) + ", Nombre: " + rs.getString(2));
            }

        } catch (Exception e) { //
            System.out.println("ERROR: " + e.getMessage()); //
            try {
                if (con != null) {
                    System.out.println("Detectado error, haciendo rollback general...");
                    con.rollback();
                }
            } catch (SQLException se) {
                System.out.println("Error al hacer rollback: " + se.getMessage());
            }
        } finally {
            // 5. Cerrar la conexión y recursos
            System.out.println("\nCerrando recursos...");
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close(); //
                System.out.println("Recursos cerrados correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}