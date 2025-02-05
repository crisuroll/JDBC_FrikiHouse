package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

public class Pedidos {
	
	public static void crearTabla() {
		String query = "CREATE TABLE IF NOT EXISTS pedidos ("
                + "id_pedido INT NOT NULL AUTO_INCREMENT, "
                + "id_proveedor INT NOT NULL, "
                + "fecha_pedido VARCHAR(50) NOT NULL, "
                + "cantidad INT NOT NULL, "
                + "subtotal INT NOT NULL, "
                + "PRIMARY KEY (id_pedido), "
                + "CONSTRAINT fk_pedidos_proveedores FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor)"
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
		String query = "DROP TABLE IF EXISTS pedidos";
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
		String query = "SELECT " + q  + " FROM pedidos";
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
		String query = "INSERT INTO pedidos (nombre) VALUES ('" + valor + "')";
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
	
	public static void eliminarValor(String valor) {
		String query = "DELETE FROM pedidos WHERE nombre = " + "'" + valor + "'";
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
		String query = "UPDATE pedidos SET nombre = " + "'" + valor + "'";
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
