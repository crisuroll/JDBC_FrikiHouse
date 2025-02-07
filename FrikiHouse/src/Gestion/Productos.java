package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

public class Productos {
	
	public static void crearTabla() {
		String query = "CREATE TABLE IF NOT EXISTS productos ("
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
				System.out.println("Tabla creada correctamente.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
	}
	
	public static void eliminarTabla() {
		String query = "DROP TABLE IF EXISTS productos";
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
		String query = "SELECT * FROM productos";
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(query);
            
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("id_producto"));
                System.out.println("Serie: " + rs.getString("id_serie"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Marca: " + rs.getString("marca"));
                System.out.println("Material: " + rs.getString("material"));
                System.out.println("Precio: " + rs.getString("precio"));
                System.out.println("Stock: " + rs.getString("stock"));
                
			}               
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void mostrarValor(String id) {
		String query = "SELECT * FROM productos WHERE id_producto = " + id;
		try {
			Connection c = ConexionBBDD.getConnection();
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(query);
			
			if(rs.next()) {
				System.out.println("ID: " + rs.getString("id_producto"));
                System.out.println("Serie: " + rs.getString("id_serie"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Marca: " + rs.getString("marca"));
                System.out.println("Material: " + rs.getString("material"));
                System.out.println("Precio: " + rs.getString("precio"));
                System.out.println("Stock: " + rs.getString("stock"));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void insertarValor(String serie, String nombre, String marca, String material, String precio,
			String stock) {
		String query = "INSERT INTO productos (id_serie, nombre, marca, material, precio, stock) VALUES "
				+ "(" + serie + ", '" + nombre + "', '" + marca + "', '" + material + "', " + precio + "," + stock + ")";		
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
	
	public static void eliminarValores(String id) {
        String query = "DELETE FROM productos WHERE id_producto = " + "'" + id + "'";
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
	
	public static void actualizarValor(String id, int n, String valor) {
		String valorBuscado = "";
		if (n == 1){
			valorBuscado = "precio";
		}else if (n == 2) {
			valorBuscado = "stock";
		}else {
			System.out.println("Campo no encontrado");
		}
		
		String query = "UPDATE productos SET " + valorBuscado  +   " = " + valor + " WHERE id_producto ="
				+ id;
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
