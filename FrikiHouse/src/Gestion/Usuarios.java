package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

public class Usuarios {
	
	public enum Rol {
		ADMIN, EMPLEADO;
	}
	
	public static void crearTabla() {
		String query = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id_usuario INT NOT NULL AUTO_INCREMENT, "
                + "nombre VARCHAR(50) NOT NULL, "
                + "contraseña VARCHAR(50) NOT NULL, "
                + "rol ENUM('admin', 'empleado') NOT NULL, "
                + "PRIMARY KEY (id_usuario) "
                + ");";
		
		try (Connection c = ConexionBBDD.getConnection();
				Statement s = c.createStatement()) {
				s.execute(query);
				System.out.println("Tabla creada correctamente.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
	}
	
	public static void eliminarTabla() {
		String query = "DROP TABLE IF EXISTS usuarios";
		try (Connection c = ConexionBBDD.getConnection();
				Statement s = c.createStatement()) {
				s.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void mostrarTabla() {
		String query = "SELECT * FROM usuarios";
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(query);
            
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("id_usuario"));
                System.out.println("Usuario: " + rs.getString("nombre"));
                System.out.println("Contraseña: " + rs.getString("contraseña"));
                System.out.println("Rol: " + rs.getString("rol"));
			}               
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void mostrarValor(String id) {
		String query = "SELECT * FROM usuarios WHERE id_usuario = " + id;
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(query);
            
			if (rs.next()) {
				System.out.println("ID: " + rs.getString("id_usuario"));
                System.out.println("Usuario: " + rs.getString("nombre"));
                System.out.println("Contraseña: " + rs.getString("contraseña"));
                System.out.println("Rol: " + rs.getString("rol"));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void insertar(String nombre, String contraseña, String rol) {
	    String query = "INSERT INTO usuarios (nombre, contraseña, rol) VALUES ('" + nombre + "', '" + contraseña + "', '" + rol + "')";
	    
	    try (Connection c = ConexionBBDD.getConnection();
	         Statement s = c.createStatement()) {
	        
	        s.executeUpdate(query);
	        System.out.println("Usuario insertado correctamente.");
	        
	    } catch (SQLException e) {
	        System.out.println("Error al insertar usuario: " + e.getMessage());
	    }
	}
	
	public static void eliminarUsuario(String id) {
		String query = "DELETE FROM usuarios WHERE id_usuario = " + "'" + id + "'";
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
	
	public static void actualizarValor(String id, String campo, String valor) {
		String query = "UPDATE usuarios SET " + campo + " = " + "'" + valor + "' WHERE id_usuario = " + id;
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
