package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    // Constantes de la Base de Datos
    // Nombre del archivo de la BD
    private static final String NOMBRE_DB = "ejercicio_crud.db";
    
    // URL de conexión para SQLite
    private static final String URL = "jdbc:sqlite:" + NOMBRE_DB;
    
    // Driver que usaremos (opcional en JDBC moderno, pero incluido por la guía)
    private static final String DRIVER = "org.sqlite.JDBC";
    
    // Clave para confirmar/revertir transacciones (la que pide el ejercicio)
    private static final String CLAVE_SEGURIDAD = "1234"; 

    /**
     * Establece y devuelve una conexión a la base de datos.
     * @return La conexión a la BD.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra el driver.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Cargar el driver
        Class.forName(DRIVER); 
        // Establecer la conexión
        return DriverManager.getConnection(URL);
    }

    /**
     * Devuelve la clave de seguridad requerida para las transacciones.
     * @return La clave de seguridad.
     */
    public static String getClaveSeguridad() {
        return CLAVE_SEGURIDAD;
    }
}