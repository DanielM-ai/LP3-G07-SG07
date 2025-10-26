package dao;

import conexion.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpleadoDAO {
    
    // --- 1. CONFIGURACIÓN INICIAL (Crear Tabla) ---
    
    public void crearTabla() {
        // Usamos una tabla simple con 4 campos: id, nombre, cargo, sueldo
        String sql = "CREATE TABLE IF NOT EXISTS empleado ("
                + "id INTEGER PRIMARY KEY,"
                + "nombre TEXT NOT NULL,"
                + "cargo TEXT,"
                + "sueldo REAL);";
        
        // El try-with-resources se encarga de cerrar 'con' y 'stmt' automáticamente
        try (Connection con = DBManager.getConnection();
             Statement stmt = con.createStatement()) {
            
            stmt.execute(sql);
            System.out.println("✅ Estructura de la tabla verificada/creada.");
            
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("❌ Error al crear/verificar la tabla: " + e.getMessage());
        }
    }

    // --- 2. OPERACIÓN LEER (Mostrar) ---
    
    public void mostrarTodos() {
        String sql = "SELECT id, nombre, cargo, sueldo FROM empleado ORDER BY id";

        try (Connection con = DBManager.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            boolean hayDatos = false;
            System.out.println("\n--- LISTADO DE EMPLEADOS ---");
            // Iteramos sobre los resultados usando el bucle while
            while (rs.next()) {
                hayDatos = true;
                // Obtenemos los datos por el nombre de la columna para mayor claridad
                System.out.printf("ID: %d | Nombre: %s | Cargo: %s | Sueldo: %.2f\n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cargo"),
                        rs.getDouble("sueldo"));
            }
            if (!hayDatos) {
                System.out.println("No hay datos registrados en la tabla.");
            }
            System.out.println("---------------------------\n");
            
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("❌ Error al consultar la base de datos: " + e.getMessage());
        }
    }
    
    // --- 3. OPERACIÓN INSERTAR (con Transacción) ---
    
    /**
     * Inserta un nuevo empleado y gestiona la transacción con la clave.
     * @return true si la operación se confirmó, false si se revirtió o falló.
     */
    public boolean insertar(int id, String nombre, String cargo, double sueldo, String clave) {
        String sql = "INSERT INTO empleado (id, nombre, cargo, sueldo) VALUES (?, ?, ?, ?)";
        Connection con = null;
        boolean operacionConfirmada = false;

        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false); // 🔑 INICIO de Transacción

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setString(2, nombre);
                ps.setString(3, cargo);
                ps.setDouble(4, sueldo);
                
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0 && clave.equals(DBManager.getClaveSeguridad())) {
                    con.commit(); // ✅ Confirmación
                    operacionConfirmada = true;
                    System.out.println("\n✅ Inserción confirmada con éxito.");
                } else {
                    con.rollback(); // ❌ Reversión
                    System.out.println("\n❌ Inserción revertida.");
                    if (!clave.equals(DBManager.getClaveSeguridad())) {
                         System.out.println("    Motivo: Clave de seguridad incorrecta.");
                    } else {
                         System.out.println("    Motivo: No se insertaron filas.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("❌ Error de BD al insertar: " + e.getMessage());
            // Si hay un error, intentamos hacer rollback por si acaso
            if (con != null) {
                try {
                    con.rollback(); 
                } catch (SQLException ex) {
                    System.err.println("❌ Error al intentar rollback: " + ex.getMessage());
                }
            }
        } finally {
            // Siempre aseguramos el cierre de la conexión
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // Ignoramos el error de cierre
                }
            }
        }
        return operacionConfirmada;
    }

    // --- 4. OPERACIÓN ACTUALIZAR (con Transacción) ---
    
    /**
     * Actualiza el cargo y sueldo de un empleado y gestiona la transacción.
     * @return true si la operación se confirmó, false si se revirtió o falló.
     */
    public boolean actualizar(int id, String nuevoCargo, double nuevoSueldo, String clave) {
        String sql = "UPDATE empleado SET cargo = ?, sueldo = ? WHERE id = ?";
        Connection con = null;
        boolean operacionConfirmada = false;

        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false); // 🔑 INICIO de Transacción

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nuevoCargo);
                ps.setDouble(2, nuevoSueldo);
                ps.setInt(3, id);
                
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0 && clave.equals(DBManager.getClaveSeguridad())) {
                    con.commit(); // ✅ Confirmación
                    operacionConfirmada = true;
                    System.out.println("\n✅ Actualización confirmada con éxito.");
                } else {
                    con.rollback(); // ❌ Reversión
                    System.out.println("\n❌ Actualización revertida.");
                    if (!clave.equals(DBManager.getClaveSeguridad())) {
                        System.out.println("    Motivo: Clave de seguridad incorrecta.");
                    } else {
                        System.out.println("    Motivo: ID de empleado no encontrado o no hubo cambios.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("❌ Error de BD al actualizar: " + e.getMessage());
            if (con != null) {
                try {
                    con.rollback(); 
                } catch (SQLException ex) {
                    // Ignoramos el error de rollback
                }
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // Ignoramos el error de cierre
                }
            }
        }
        return operacionConfirmada;
    }
    
    // --- 5. OPERACIÓN BORRAR (con Transacción) ---

    /**
     * Borra un empleado por ID y gestiona la transacción.
     * @return true si la operación se confirmó, false si se revirtió o falló.
     */
    public boolean borrar(int id, String clave) {
        String sql = "DELETE FROM empleado WHERE id = ?";
        Connection con = null;
        boolean operacionConfirmada = false;

        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false); // 🔑 INICIO de Transacción

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0 && clave.equals(DBManager.getClaveSeguridad())) {
                    con.commit(); // ✅ Confirmación
                    operacionConfirmada = true;
                    System.out.println("\n✅ Borrado de ID " + id + " confirmado con éxito.");
                } else {
                    con.rollback(); // ❌ Reversión
                    System.out.println("\n❌ Borrado revertido.");
                    if (!clave.equals(DBManager.getClaveSeguridad())) {
                        System.out.println("    Motivo: Clave de seguridad incorrecta.");
                    } else {
                        System.out.println("    Motivo: ID de empleado " + id + " no encontrado.");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("❌ Error de BD al borrar: " + e.getMessage());
            if (con != null) {
                try {
                    con.rollback(); 
                } catch (SQLException ex) {
                    // Ignoramos el error de rollback
                }
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // Ignoramos el error de cierre
                }
            }
        }
        return operacionConfirmada;
    }
    
}