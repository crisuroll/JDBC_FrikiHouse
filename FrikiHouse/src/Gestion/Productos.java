package Gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

public class Productos {
 
	private static final String URL = "jdbc:mysql://localhost:3306/frikihouse?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "admin";
	
	public static void crearTabla() {
		String query = "CREATE TABLE IF NOT EXISTS Productos ("
                + "id_producto INT NOT NULL AUTO_INCREMENT, "
                + "id_serie INT NOT NULL, "
                + "nombre VARCHAR(50) NOT NULL, "
                + "marca VARCHAR(60) NOT NULL, "
                + "material VARCHAR(50) NOT NULL, "
                + "precio DECIMAL(10,2) NOT NULL, "
                + "stock INT NOT NULL, "
                + "PRIMARY KEY (id_producto), "
                + "CONSTRAINT fk_productos_serie FOREIGN KEY (id_serie) REFERENCES Series(id_serie)"
                + ");";
		
		try (Connection c = ConexionBBDD.getConnection();
				Statement s = c.createStatement()) {
				s.execute(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
	}
	
	public static void eliminarTabla() {
		String query = "DROP TABLE IF EXISTS Productos";
		try (Connection c = ConexionBBDD.getConnection();
				Statement s = c.createStatement()) {
				s.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
}
