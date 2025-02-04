package FrikiHouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Gestion.Productos;
import Gestion.Series;

public class Main {

	
	public static void main(String[] args) {
		try (Connection c = ConexionBBDD.getConnection();
				Statement s = c.createStatement()) {
			/*
			 * CREACION DE TABLAS
			 */
			Series.eliminarTabla();
			Series.crearTabla();
			//Series.eliminarTabla();
			
			Series.eliminarValor("GT");
			Series.insertar("Z");
			Series.eliminarValor("Jacinto");
			Series.mostrar("*");
			
			//System.out.println("Tabla creada correctamente.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
	}

}
