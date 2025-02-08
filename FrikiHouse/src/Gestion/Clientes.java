package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

/**
 * Clase Clientes. Contiene todos los métodos para hacer operaciones CRUD en dicha tabla.
 */
public class Clientes {
	
	/**
	 * Método crearTabla(). Crea la tabla en la BBDD.
	 */
	public static void crearTabla() {
		String query = "CREATE TABLE IF NOT EXISTS clientes ("
                + "dni VARCHAR(9) NOT NULL, "
                + "nombre VARCHAR(50) NOT NULL, "
                + "apellido VARCHAR(50) NOT NULL, "
                + "PRIMARY KEY (dni) "
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
		String query = "DROP TABLE IF EXISTS clientes";
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
		String query = "SELECT * FROM clientes";
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(query);
            
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("dni"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Apellido: " + rs.getString("apellido"));
			}               
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * Método mostrarValor(). Muestra las filas donde el dni coincida por el pasado por parámetro.
	 * @param dni
	 */
	public static void mostrarValor(String dni) {
		String query = "SELECT * FROM clientes WHERE dni = '" + dni + "'";
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(query);
            
			if (rs.next()) {
				System.out.println("ID: " + rs.getString("dni"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Apellido: " + rs.getString("apellido"));
			}               
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * Método insertarValor(). Inserta una nueva fila a la tabla.
	 * @param dni
	 * @param nombre
	 * @param apellido
	 */
	public static void insertarValor(String dni, String nombre, String apellido) {
		String query = "INSERT INTO clientes (dni, nombre, apellido) VALUES ('" + dni + "', '" + nombre + "', '" + apellido + "')";
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
	
	/**
	 * Método eliminarValor(). Elimina la fila donde coincida el dni pasado por parámetro.
	 * @param dni
	 */
	public static void eliminarValor(String dni) {
		String query = "DELETE FROM clientes WHERE dni = " + "'" + dni + "'";
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement();
			s.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
