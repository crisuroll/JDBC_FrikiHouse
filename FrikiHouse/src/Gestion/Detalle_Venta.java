package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

public class Detalle_Venta {
	
	public static void crearTabla() {
		String query = "CREATE TABLE IF NOT EXISTS detalle_venta ("
                + "id_detalle_venta INT NOT NULL AUTO_INCREMENT, "
                + "id_venta INT NOT NULL, "
                + "id_producto INT NOT NULL, "
                + "cantidad INT NOT NULL, "
                + "subtotal DECIMAL(10,2) NOT NULL, "
                + "PRIMARY KEY (id_detalle_venta), "
                + "CONSTRAINT fk_detalleventa_ventas FOREIGN KEY (id_venta) REFERENCES Ventas(id_venta), "
                + "CONSTRAINT fk_detalleventa_productos FOREIGN KEY (id_producto) REFERENCES Productos(id_producto) "
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
		String query = "DROP TABLE IF EXISTS detalle_venta";
		try (Connection c = ConexionBBDD.getConnection();
				Statement s = c.createStatement()) {
				s.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void mostrar(String q) {
		String query = "SELECT " + q  + " FROM detalle_venta";
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(query);
            
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("id_serie"));
                System.out.println("Serie: " + rs.getString("nombre"));
			}               
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void insertar(String valor) {
		String query = "INSERT INTO detalle_venta (nombre) VALUES ('" + valor + "')";
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
	
	/*
	public static void eliminarValor(String valor) {
		String query = "DELETE FROM detalle_venta WHERE nombre = " + "'" + valor + "'";
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
	
	public static void actualizarValor(String valor) {
		String query = "UPDATE detalle_venta SET nombre = " + "'" + valor + "'";
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
	*/
}
