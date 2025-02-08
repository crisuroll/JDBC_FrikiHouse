package FrikiHouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase ConexionBBDD. Encargada de establecer los parámetros necesarios para conectar con la BBDD de MySQL.
 */
public class ConexionBBDD {
    private static final String URL = "jdbc:mysql://localhost:3306/frikihouse";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    /**
     * Método getConnection(). Encargado de establecer y devolver una conexión a la base de datos MySQL.
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

