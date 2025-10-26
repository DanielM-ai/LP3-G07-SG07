package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String NOMBRE_DB = "ejercicio_crud.db";
    private static final String URL = "jdbc:sqlite:" + NOMBRE_DB;
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String CLAVE_SEGURIDAD = "1234"; 

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER); 
        return DriverManager.getConnection(URL);
    }

    public static String getClaveSeguridad() {
        return CLAVE_SEGURIDAD;
    }
}