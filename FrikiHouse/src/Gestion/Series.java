package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

/**
 * Clase Series. Contiene todos los métodos para hacer operaciones CRUD en dicha tabla.
 */
public class Series {
	
	/**
	 * Método crearTabla(). Crea la tabla en la BBDD.
	 */
	public static void crearTabla() {
		String query = "CREATE TABLE IF NOT EXISTS Series ("
                + "id_serie INT NOT NULL AUTO_INCREMENT , "
                + "nombre VARCHAR(100) NOT NULL, "
                + "UNIQUE (nombre), "
                + "PRIMARY KEY (id_serie) "
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
	
	/**
	 * Método eliminarTabla(). Elimina la tabla de la BBDD.
	 */
	public static void eliminarTabla() {
		String query = "DROP TABLE IF EXISTS Series";
		try (Connection c = ConexionBBDD.getConnection();
				Statement s = c.createStatement()) {
				s.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * Método mostrarTabla(). Muestra la tabla completa.
	 */
	public static void mostrarTabla() {
		String query = "SELECT * FROM Series ORDER BY id_serie";
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(query);
            
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("id_serie"));
                System.out.println("Nombre: " + rs.getString("nombre"));
			}               
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * Método mostrarValor(). Muestra las filas donde el id coincida por el pasado por parámetro.
	 * @param id
	 */
	public static void mostrarValor(String id) {
		String query = "SELECT * FROM series WHERE id_producto = " + id;
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(query);
			
			if(rs.next()) {
				System.out.println("ID: " + rs.getString("id_serie"));
                System.out.println("Nombre: " + rs.getString("nombre"));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * Método eliminarValores(). Elimina la fila donde coincida el id pasado por parámetro.
	 * @param id
	 */
	public static void eliminarValores(String id) {
	    String deleteQuery = "DELETE FROM series WHERE id_serie = " + "'" + id + "'";
	    String resetAutoIncrementQuery = "ALTER TABLE series AUTO_INCREMENT = 1";

	    try (Connection c = ConexionBBDD.getConnection();
	         Statement s = c.createStatement()) {
	        s.executeUpdate(deleteQuery);
	        s.executeUpdate(resetAutoIncrementQuery);
	    } catch (SQLException e) {
	        System.out.println("Error de SQL: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace(System.err);
	    }
	}
	
	/**
	 * Método insertarValor(). Inserta una nueva fila a la tabla.
	 * @param valor
	 */
	public static void insertarValor(String valor) {
		String query = "INSERT INTO series (nombre) VALUES ('" + valor + "')";
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement();
			s.executeUpdate(query);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
	}	
}