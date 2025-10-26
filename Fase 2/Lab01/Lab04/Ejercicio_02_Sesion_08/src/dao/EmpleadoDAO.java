package dao;

import conexion.DBManager;
import modelo.Empleado; // Importamos la clase Empleado
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    
    // --- 1. CONFIGURACIÓN INICIAL (Crear Tabla) ---
    // (Mismo código que el Ejercicio 1)
    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS empleado ("
                + "id INTEGER PRIMARY KEY,"
                + "nombre TEXT NOT NULL,"
                + "cargo TEXT,"
                + "sueldo REAL);";
        
        try (Connection con = DBManager.getConnection();
             Statement stmt = con.createStatement()) {
            
            stmt.execute(sql);
            System.out.println("✅ Estructura de la tabla verificada/creada.");
            
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("❌ Error al crear/verificar la tabla: " + e.getMessage());
        }
    }

    // --- 2. OPERACIÓN LEER MODIFICADA (Devuelve la lista de objetos) ---
    
    /**
     * Obtiene TODOS los registros de la BD y los encapsula en una lista de objetos Empleado.
     * Esta lista se usará en la clase Gestora para aplicar filtros y ordenamientos en Java.
     * @return Una lista de objetos Empleado.
     */
    public List<Empleado> obtenerTodos() {
        String sql = "SELECT id, nombre, cargo, sueldo FROM empleado";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection con = DBManager.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                // Creamos un nuevo objeto Empleado con los datos del ResultSet
                Empleado emp = new Empleado(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("cargo"),
                    rs.getDouble("sueldo")
                );
                empleados.add(emp);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("❌ Error al consultar la base de datos: " + e.getMessage());
        }
        return empleados; // Devuelve la lista, que puede estar vacía
    }
    
    // --- 3. OPERACIÓN INSERTAR (Mismo código que el Ejercicio 1) ---
    
    public boolean insertar(int id, String nombre, String cargo, double sueldo, String clave) {
        String sql = "INSERT INTO empleado (id, nombre, cargo, sueldo) VALUES (?, ?, ?, ?)";
        Connection con = null;
        boolean operacionConfirmada = false;

        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false); 

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setString(2, nombre);
                ps.setString(3, cargo);
                ps.setDouble(4, sueldo);
                
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0 && clave.equals(DBManager.getClaveSeguridad())) {
                    con.commit(); 
                    operacionConfirmada = true;
                    System.out.println("\n✅ Inserción confirmada con éxito.");
                } else {
                    con.rollback(); 
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
            if (con != null) {
                try {
                    con.rollback(); 
                } catch (SQLException ex) {
                    System.err.println("❌ Error al intentar rollback: " + ex.getMessage());
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

    // --- 4. OPERACIÓN ACTUALIZAR (Mismo código que el Ejercicio 1) ---
    
    public boolean actualizar(int id, String nuevoCargo, double nuevoSueldo, String clave) {
        String sql = "UPDATE empleado SET cargo = ?, sueldo = ? WHERE id = ?";
        Connection con = null;
        boolean operacionConfirmada = false;

        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false); 

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nuevoCargo);
                ps.setDouble(2, nuevoSueldo);
                ps.setInt(3, id);
                
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0 && clave.equals(DBManager.getClaveSeguridad())) {
                    con.commit(); 
                    operacionConfirmada = true;
                    System.out.println("\n✅ Actualización confirmada con éxito.");
                } else {
                    con.rollback(); 
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
    
    // --- 5. OPERACIÓN BORRAR (Mismo código que el Ejercicio 1) ---

    public boolean borrar(int id, String clave) {
        String sql = "DELETE FROM empleado WHERE id = ?";
        Connection con = null;
        boolean operacionConfirmada = false;

        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false); 

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0 && clave.equals(DBManager.getClaveSeguridad())) {
                    con.commit(); 
                    operacionConfirmada = true;
                    System.out.println("\n✅ Borrado de ID " + id + " confirmado con éxito.");
                } else {
                    con.rollback(); 
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