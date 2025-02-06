package Gestion;

import java.sql.Connection;
import java.sql.Date;
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
                + "subtotal DECIMAL(10,2) NOT NULL, "
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
	
	public static void mostrarTabla() {
		String query = "SELECT * FROM pedidos";
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(query);
            
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("id_pedido"));
                System.out.println("ID_proveedor: " + rs.getString("id_proveedor"));
                System.out.println("Fecha : " + rs.getString("fecha_pedido"));
                System.out.println("Cantidad: " + rs.getString("cantidad_total"));
                System.out.println("Total: " + rs.getString("subtotal"));
                
			}               
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void insertarPedidos(String id_proveedor, String fecha_pedido, String cantidad_total, String subtotal) {
		String query = "INSERT INTO pedidos (id_proveedor, fecha_pedido, cantidad_total, subtotal) VALUES (" + id_proveedor + ", " + fecha_pedido + ", " + cantidad_total + ", " + subtotal + ")";
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
