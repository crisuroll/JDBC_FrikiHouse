package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

/**
 * Clase Productos. Contiene todos los métodos para hacer operaciones CRUD en dicha tabla.
 */
public class Productos {
	
	/**
	 * Método crearTabla(). Crea la tabla en la BBDD.
	 */
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
	
	/**
	 * Método mostrarTabla(). Muestra la tabla completa.
	 */
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
                System.out.println("-----------------------------\n");
                
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
                System.out.println("-----------------------------\n");
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * Método insertarValor(). Inserta una nueva fila a la tabla.
	 * @param serie
	 * @param nombre
	 * @param marca
	 * @param material
	 * @param precio
	 */
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
	
	/**
	 * Método eliminarTabla(). Elimina la tabla de la BBDD.
	 */
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
	
    /**
     * Metodo actualizarValor(). Actualiza el parametro dado al valor dado. Si es compra (true) suma stock, si es
     * venta (false) lo resta.
     * @param id
     * @param n
     * @param valor
     * @param esCompra
     */
	public static void actualizarValor(String id, int n, String valor, boolean esCompra) {
	    String valorBuscado = "";
	    if (n == 1) {
	        valorBuscado = "precio";
	    } else if (n == 2) {
	        valorBuscado = "stock";
	    } else {
	        System.out.println("Campo no encontrado");
	        return;
	    }

	    try {
	        Connection c = ConexionBBDD.getConnection();
	        String selectQuery = "SELECT " + valorBuscado + " FROM productos WHERE id_producto = " + id;
	        Statement selectStatement = c.createStatement();
	        ResultSet resultSet = selectStatement.executeQuery(selectQuery);
	        
	        if (resultSet.next()) {
	            int valorActual = resultSet.getInt(valorBuscado);
	            int nuevoValor;

	            if (n == 1) {
	                nuevoValor = valorActual * Integer.parseInt(valor);
	                System.out.println("Precio actual: " + valorActual + ", Nuevo precio: " + nuevoValor);
	            } else if (n == 2) {
	                if (esCompra) {
	                    nuevoValor = valorActual + Integer.parseInt(valor); // Sumar stock si es compra
	                    System.out.println("Stock actual: " + valorActual + ", Nuevo stock tras compra: " + nuevoValor);
	                } else {
	                    nuevoValor = valorActual - Integer.parseInt(valor); // Restar stock si es venta
	                    if (nuevoValor < 0) {
	                        System.out.println("No hay suficiente stock para la venta.");
	                        return;
	                    }
	                    System.out.println("Stock actual: " + valorActual + ", Nuevo stock tras venta: " + nuevoValor);
	                }
	            } else {
	                System.out.println("Operación no válida");
	                return;
	            }

	            String updateQuery = "UPDATE productos SET " + valorBuscado + " = " + nuevoValor + " WHERE id_producto = " + id;
	            Statement updateStatement = c.createStatement();
	            updateStatement.executeUpdate(updateQuery);
	            
	            System.out.println("Valor actualizado correctamente");
	        } else {
	            System.out.println("Producto no encontrado");
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Error en la base de datos: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace(System.err);
	    }
	}

}
